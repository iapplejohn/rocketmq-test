package com.jemmy.rocketmq.test.service;

import com.jemmy.rocketmq.test.domain.TestMsg;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.apache.rocketmq.spring.support.RocketMQHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.MimeTypeUtils;

/**
 * @author zhujiang.cheng
 * @since 2020/7/22
 */
//@Component
@Slf4j
public class TemplateTransactionProducer {

    @Resource
    private RocketMQTemplate rocketMQTemplate;

    @Scheduled(cron = "0/38 0/1 * * * ?")
    public void sendMsg() {
        TestMsg obj = new TestMsg();
        obj.setName("transactional");

        // 方案1: MessageBuilder
        long timestamp = System.currentTimeMillis();
        Message<TestMsg> message = MessageBuilder
            .withPayload(obj)
            // 设置 事务ID
            .setHeader(RocketMQHeaders.TRANSACTION_ID, timestamp)
            // 唯一标识，比如订单号
            .setHeader(RocketMQHeaders.KEYS, timestamp)
            // 发送 JDK 序列化消息
            .setHeader(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_OCTET_STREAM)
            .build();

        // 同步发送，Topic和Tag之间用冒号分开，构成destination参数
        SendResult sendResult = rocketMQTemplate.sendMessageInTransaction("TransactionTest:brilliant", message, timestamp);
        log.info("Send transaction message result: {}", sendResult);
    }

}
