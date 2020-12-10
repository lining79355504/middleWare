package com.mort.middle.ware.common.kafka;


import com.mort.middle.ware.consumer.kafka.BatchConsumerService;
import com.mort.middle.ware.consumer.kafka.SingleConsumerService;
import com.mort.middle.ware.consumer.utils.JacksonUtils;
import com.mort.middle.ware.common.kafka.entity.AccAccountWalletLogPo;
import com.fasterxml.jackson.core.type.TypeReference;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.springframework.stereotype.Component;

@Component
public class WalletLogConsumer implements BatchConsumerService {

    @Override
    public String getTopic() {
        return "wallet_log_binlog";
    }

    @Override
    public String getGroup() {
        return "wallet_log_binlog_group_1";
    }

    @Override
    public void consumer(ConsumerRecords<String, String> records) throws Throwable {
        for (ConsumerRecord<String, String> record : records) {
            String value = record.value();
            AccAccountWalletLogPo entity = JacksonUtils.deserialize(value, new TypeReference<AccAccountWalletLogPo>(){});
            System.out.println("record = " + entity.toString());
        }
    }

}
