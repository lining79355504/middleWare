package com.mort.middle.ware.consumer.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;

/**
 * 顺序批量消费
 */
public interface BatchConsumerService extends ConsumerService{

    void consumer(ConsumerRecords<String, String> records) throws Throwable;

}
