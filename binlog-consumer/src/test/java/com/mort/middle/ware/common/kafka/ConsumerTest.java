package com.mort.middle.ware.common.kafka;


import com.mort.middle.ware.consumer.kafka.ConsumerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;

public class ConsumerTest {


    private static final Logger logger = LoggerFactory.getLogger(ConsumerTest.class);

    @Resource
    private ConsumerService consumerService;

}
