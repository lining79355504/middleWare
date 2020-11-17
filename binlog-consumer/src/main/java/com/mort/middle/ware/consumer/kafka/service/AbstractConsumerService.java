package com.mort.middle.ware.consumer.kafka.service;

import com.mort.middle.ware.consumer.kafka.ConsumerService;
import com.mort.middle.ware.consumer.kafka.config.BibleConfigureCenterService;
import com.dianping.cat.Cat;
import com.dianping.cat.message.Transaction;
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

public abstract class AbstractConsumerService implements ConsumerService, InitializingBean , DisposableBean {

    private static final Logger logger = LoggerFactory.getLogger(AbstractConsumerService.class);

    private int PARTITION_ID = 0;

    private long MILLIS = 10000;

    private volatile boolean flag = true;

    @Resource
    private BibleConfigureCenterService bibleConfigureCenterService;

    @Override
    public void afterPropertiesSet() throws Exception {


        new Thread(() -> {
            Properties props = new Properties();
            String clusterConfig = bibleConfigureCenterService.getBibleConfig("config.properties", "kafka.cluster.addr");
            props.setProperty("bootstrap.servers", clusterConfig);
            props.setProperty("group.id", getGroup());
            props.setProperty("enable.auto.commit", "false");
            props.setProperty("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
            props.setProperty("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
            KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);

            try {
                if (0 == PARTITION_ID) {
                    consumer.subscribe(Arrays.asList(getTopic()));
                } else {
                    consumer.assign(Arrays.asList(new TopicPartition(getTopic(), PARTITION_ID)));
                }

                while (flag) {
                    try {
                        ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(MILLIS));
                        for (ConsumerRecord<String, String> record : records) {
                            Transaction transaction = Cat.newTransaction("MQ_CONSUMER", getTopic() + ":" + getGroup());
                            try {
                                consumer(record);
                                transaction.setStatus("0");
                                Map<TopicPartition, OffsetAndMetadata> commitInfo = new HashMap<>();
                                commitInfo.put(new TopicPartition(getTopic(), record.partition()), new OffsetAndMetadata(record.offset()));
                                consumer.commitSync(commitInfo);
                            } catch (Throwable e) {
                                transaction.setStatus(e);
                                //防止其中一个异常，下个消息跳跃ack导致消息丢失。
                                break;
                            } finally {
                                transaction.complete();
                            }
                        }
                    } catch (Throwable throwable) {
                        logger.error("error ", throwable);
                    }
                }
            } finally {
                consumer.close();
            }

        }).start();

    }

    protected void initParam(int partitionId, int millis) {
        PARTITION_ID = partitionId;
        MILLIS = millis;
    }

    @Override
    public void destroy() throws Exception {
        this.flag = false;
    }
}
