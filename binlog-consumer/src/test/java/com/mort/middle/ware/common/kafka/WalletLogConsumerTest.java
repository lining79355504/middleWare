package com.mort.middle.ware.common.kafka;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@ComponentScan({"com.mort.*"})
public class WalletLogConsumerTest {

    @Resource
    private WalletLogConsumer walletLogConsumer;

    @Test
    public void consumerTest() {
        while(true){

        }
    }

}
