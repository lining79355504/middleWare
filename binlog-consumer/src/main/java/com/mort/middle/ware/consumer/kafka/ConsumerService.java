package com.mort.middle.ware.consumer.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;

public interface ConsumerService {

    String getTopic();

    String getGroup();

    void consumer(ConsumerRecord<String, String> record) throws Throwable;

}
