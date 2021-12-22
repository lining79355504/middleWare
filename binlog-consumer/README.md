#kafka 参数记录
  1. max.poll.records
  2. kafka一个分区只能有一个poll线程，这是kafka决定的。一次poll的数据可以用线程池多线程处理。
  3. (internal topic setting)kafka __consumer_offsets 会自动创建__consumer_offsets的topic 用于保存consumer提交的分区。
     副本为1的话，机器宕机影响消费。HA中要修改为多副本。
  4. (internal topic setting)消费方有提交也是同3一样修改副本才行，否则会被group 提交形成的topic 没有高可用。   
  5. kafka中topic数据默认保存7天，超过7天未消费的group 将会被自动删除。   
  6. kafaka client连接超时重试功能。监控consumer掉线功能。
  7. commit offset 可以往前（回溯消息） 可以往后（跳过消息）。
  8. 泳道支持 解决线下多机器不能消费到机器的问题。
  
  
  
#Roadmap
  1.防止单个消息一直失败，采用消费失败后 重新发送消息到topic，或者指定死信队列、发送成功后ACK该消息。同时打点重试次数。
    或者实现指定消费次数功能，可以保证消息不丢且不会被一条消息阻塞。
  