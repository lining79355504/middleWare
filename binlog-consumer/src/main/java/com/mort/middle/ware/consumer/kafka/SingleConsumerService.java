package com.mort.middle.ware.consumer.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;

/**
 * 顺序单个消费
 */
public interface SingleConsumerService extends ConsumerService{

    void consumer(ConsumerRecord<String, String> record) throws Throwable;

}
