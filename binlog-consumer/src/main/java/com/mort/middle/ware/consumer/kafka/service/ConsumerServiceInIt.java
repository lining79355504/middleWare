package com.mort.middle.ware.consumer.kafka.service;

import com.dianping.cat.message.Message;
import com.mort.middle.ware.consumer.kafka.BatchConsumerService;
import com.mort.middle.ware.consumer.kafka.SingleConsumerService;
import com.mort.middle.ware.consumer.kafka.config.BibleConfigureCenterService;
import com.dianping.cat.Cat;
import com.dianping.cat.message.Transaction;
import com.sun.tools.javac.util.List;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.consumer.OffsetAndMetadata;
import org.apache.kafka.common.TopicPartition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import javax.annotation.Resource;
import java.time.Duration;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicBoolean;

public class ConsumerServiceInIt implements InitializingBean, DisposableBean {

    private static final Logger logger = LoggerFactory.getLogger(ConsumerServiceInIt.class);

    private int PARTITION_ID = 0;

    private long MILLIS = 10000;

    private volatile boolean flag = true;

    private AtomicBoolean closed = new AtomicBoolean(false);

    private AtomicBoolean block = new AtomicBoolean(false);

    @Resource
    private List<SingleConsumerService> singleConsumerServiceList;

    @Resource
    private List<BatchConsumerService> batchConsumerServiceList;


    private BibleConfigureCenterService bibleConfigureCenterService;


    @Override
    public void afterPropertiesSet() throws Exception {
        singleInit();
        batchInit();
    }

    private void singleInit() {
        for (SingleConsumerService consumerService : singleConsumerServiceList) {

            new Thread(() -> {
                Properties props = new Properties();
                String clusterConfig = bibleConfigureCenterService.getBibleConfig("config.properties", "kafka.cluster.addr");
                props.setProperty("bootstrap.servers", clusterConfig);
                props.setProperty("group.id", consumerService.getGroup());
                props.setProperty("enable.auto.commit", "false");
                props.setProperty("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
                props.setProperty("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
                KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);

                try {
                    if (0 == PARTITION_ID) {
                        consumer.subscribe(Arrays.asList(consumerService.getTopic()));
                    } else {
                        consumer.assign(Arrays.asList(new TopicPartition(consumerService.getTopic(), PARTITION_ID)));
                    }

                    while (flag) {
                        try {
                            if (statusCheck(consumer)) {
                                return;
                            }
                            ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(MILLIS));
                            singleConsumer(consumerService, consumer, records);
                        } catch (Throwable throwable) {
                            logger.error("error ", throwable);
                        }
                    }
                } finally {
                    consumer.close();
                }

            }).start();
        }
    }

    private boolean statusCheck(KafkaConsumer<String, String> consumer) {
        if (block.get()) {
            return true;
        } else {
            if (closed.get()) {
                consumer.wakeup();
                closed.set(false);
            }
        }
        return false;
    }

    private void singleConsumer(SingleConsumerService consumerService, KafkaConsumer<String, String> consumer, ConsumerRecords<String, String> records) {
        for (ConsumerRecord<String, String> record : records) {
            Transaction transaction = Cat.newTransaction("MQ_CONSUMER", consumerService.getTopic() + ":" + consumerService.getGroup());
            try {
                consumerService.consumer(record);
                transaction.setStatus("0");
                Map<TopicPartition, OffsetAndMetadata> commitInfo = new HashMap<>();
                commitInfo.put(new TopicPartition(consumerService.getTopic(), record.partition()), new OffsetAndMetadata(record.offset()));
                consumer.commitSync(commitInfo);
            } catch (Throwable e) {
                transaction.setStatus(e);
                //防止其中一个异常，下个消息跳跃ack导致消息丢失。
                break;
            } finally {
                transaction.complete();
            }
        }
    }


    private void batchInit() {
        for (BatchConsumerService consumerService : batchConsumerServiceList) {

            new Thread(new Runnable() {
                @Override
                public void run() {
                    Properties props = new Properties();
                    String clusterConfig = bibleConfigureCenterService.getBibleConfig("config.properties", "kafka.cluster.addr");
                    props.setProperty("bootstrap.servers", clusterConfig);
                    props.setProperty("group.id", consumerService.getGroup());
                    props.setProperty("enable.auto.commit", "false");
                    props.setProperty("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
                    props.setProperty("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
                    KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);

                    try {
                        if (0 == PARTITION_ID) {
                            consumer.subscribe(Arrays.asList(consumerService.getTopic()));
                        } else {
                            consumer.assign(Arrays.asList(new TopicPartition(consumerService.getTopic(), PARTITION_ID)));
                        }

                        while (flag) {
                            try {
                                ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(MILLIS));
                                ConsumerServiceInIt.this.batchConsumer(consumerService, consumer, records);
                            } catch (Throwable throwable) {
                                logger.error("error ", throwable);
                            }
                        }
                    } finally {
                        consumer.close();
                    }

                }
            }).start();
        }
    }

    private void batchConsumer(BatchConsumerService consumerService, KafkaConsumer<String, String> consumer, ConsumerRecords<String, String> records) {

        Transaction transaction = Cat.newTransaction("MQ_CONSUMER_GROUP", consumerService.getTopic() + ":" + consumerService.getGroup());
        try {
            consumerService.consumer(records);
            consumer.commitSync();
            transaction.setStatus(Message.SUCCESS);
        } catch (Throwable e) {
            logger.error("error ", e);
            transaction.setStatus(e);
        } finally {
            transaction.complete();
        }
    }

    protected void initParam(int partitionId, int millis) {
        PARTITION_ID = partitionId;
        MILLIS = millis;
        bibleConfigureCenterService = new BibleConfigureCenterService();
    }

    @Override
    public void destroy() throws Exception {
        this.flag = false;
    }

    /**
     * TODO 转从配置中心获取、设置状态
     * block this consumer
     * block true  closed true
     */
    public void block() {
        this.block.set(true);
        this.closed.set(true);
    }

    /**
     * TODO 转从配置中心获取、设置状态
     * unblock this consumer
     * block false
     */
    public void unblock() {
        this.block.set(false);
    }
}
