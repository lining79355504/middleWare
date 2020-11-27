package com.mort.middle.ware.consumer.kafka.service;

import com.dianping.cat.Cat;
import com.dianping.cat.message.Transaction;
import com.mort.middle.ware.consumer.kafka.MultiConsumerService;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.consumer.OffsetAndMetadata;
import org.apache.kafka.common.TopicPartition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.Duration;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MultiConsumerServiceExecutor implements InitializingBean {


    private static final Logger logger = LoggerFactory.getLogger(MultiConsumerServiceExecutor.class);

    public static final int PARTITION_ID = 0;

    @Autowired
    private List<MultiConsumerService> multiConsumerServices;


    ExecutorService executorService = Executors.newFixedThreadPool(5);

    @Override
    public void afterPropertiesSet() throws Exception {

        new Thread(() -> {

            multiConsumerServices.forEach(consumerService -> {

                Properties props = new Properties();
                String clusterConfig = consumerService.getConfig("config.properties", "kafka.cluster.addr");
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

                    loop:
                    while (true) {
                        try {
                            ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));
                            for (ConsumerRecord<String, String> record : records) {
                                //TODO 多线程消费考虑 考虑消息ack问题 只能保证接收一次
                                //好的实现方式 业务方自己批量接收消息 自己实现多线程消费
                                executorService.submit(() -> {
                                            Transaction transaction = Cat.newTransaction("MQ_CONSUMER", consumerService.getTopic() + ":" + consumerService.getGroup());

                                            try {
                                                consumerService.consumer(record);
                                                transaction.setStatus("0");
                                                //TODO 多线程消费考虑 考虑消息ack问题 只能保证接收一次
                                                Map<TopicPartition, OffsetAndMetadata> commitInfo = new HashMap<>();
                                                commitInfo.put(new TopicPartition(consumerService.getTopic(), record.partition()), new OffsetAndMetadata(record.offset()));
                                                consumer.commitSync(commitInfo);

                                            } catch (Throwable throwable) {
                                                logger.error("", throwable);
                                                transaction.setStatus(throwable);
                                            } finally {
                                                transaction.complete();
                                            }
                                        }
                                );

                            }
                        } catch (Throwable throwable) {
                            logger.error("error ", throwable);
                        }
                    }
                } finally {
                    consumer.close();
                }
            });

        }).start();


    }

}
