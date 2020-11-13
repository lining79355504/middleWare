package com.mort.middle.ware.consumer.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;

import java.util.Properties;
import java.util.concurrent.Executor;

public interface MultiConsumerService extends ConsumerService{

    Executor getExecutor();

    String getConfig(String fileName, String key);

    Properties getConfig();
}
