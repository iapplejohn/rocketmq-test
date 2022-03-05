package com.jemmy.rocketmq.test.service;

import com.jemmy.rocketmq.test.domain.TestMsg;
import java.util.concurrent.atomic.AtomicInteger;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.apache.rocketmq.spring.support.RocketMQHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.util.MimeTypeUtils;

/**
 * @author zhujiang.cheng
 * @since 2020/5/13
 */
//@Service
@Slf4j
public class RocketMQTemplateProducer {

    @Resource
    private RocketMQTemplate rocketMQTemplate;

    private AtomicInteger count = new AtomicInteger();

    boolean bSend = false;

    @Scheduled(cron = "0 0/1 * * * ?")
    public void sendMsg() {
        if (bSend) {
            return;
        }

        TestMsg obj = new TestMsg();
        obj.setName("prospective" + count.incrementAndGet());

        // 方案1: MessageBuilder
        Message<TestMsg> message = MessageBuilder
            .withPayload(obj)
            .setHeader(RocketMQHeaders.KEYS, System.currentTimeMillis())
//            .setHeader("good", "nice")
            .setHeader(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_JSON) // 发送 JSON 消息
            .build();

        // 方案2: 实例化Message
//        Message<PersonMsg> message = new Message<PersonMsg>() {
//            @Override
//            public PersonMsg getPayload() {
//                return obj;
//            }
//
//            @Override
//            public MessageHeaders getHeaders() {
//                Map<String, Object> headerMap = new HashMap<>(8);
//                headerMap.put(RocketMQHeaders.KEYS, System.currentTimeMillis());
//                headerMap.put(RocketMQHeaders.MESSAGE_ID, System.currentTimeMillis());
//                return new MessageHeaders(headerMap);
//            }
//        };


        // 方案3：MessageBuilder的createMessage方法
//        Map<String, Object> headerMap = new HashMap<>(4);
//        headerMap.put(RocketMQHeaders.KEYS, System.currentTimeMillis());
////        headerMap.put(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_JSON);
//        headerMap.put(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_OCTET_STREAM);
//        Message<TestMsg> message = MessageBuilder.createMessage(obj, new MessageHeaders(headerMap));

        // 异步发送
        rocketMQTemplate.asyncSend("TEST:testTag", message, new SendCallback() {
            @Override
            public void onSuccess(SendResult sendResult) {
                log.info("Send result: {}", sendResult);
            }

            @Override
            public void onException(Throwable e) {
                log.error("send failed, message: " + message, e);
            }
        });

        bSend = true;
    }
}
