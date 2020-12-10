package com.mort.middle.ware.consumer.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;

import java.util.Properties;
import java.util.concurrent.Executor;

/**
 * 并行消费  不能保证消费顺序
 */
public interface MultiConsumerService extends ConsumerService{

    Executor getExecutor();

    String getConfig(String fileName, String key);

    Properties getConfig();

    void consumer(ConsumerRecord<String, String> record) throws Throwable;
}
