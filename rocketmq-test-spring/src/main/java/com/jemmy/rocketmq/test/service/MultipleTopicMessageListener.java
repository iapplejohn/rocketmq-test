package com.jemmy.rocketmq.test.service;

import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeUnit;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.spring.annotation.MessageModel;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.apache.rocketmq.spring.core.RocketMQPushConsumerLifecycleListener;

import org.springframework.stereotype.Component;
/**
 * @author zhujiang.cheng
 * @since 2020/7/24
 */
//@Component
@RocketMQMessageListener(
    consumerGroup = "testComplianceGroup",
    topic = "TEST",
    messageModel = MessageModel.CLUSTERING,
    selectorExpression = "testTag")
@Slf4j
public class MultipleTopicMessageListener implements RocketMQListener<MessageExt>, RocketMQPushConsumerLifecycleListener {

    @Override
    public void onMessage(MessageExt message) {
        log.info("receive native msg, topic: {}, tag: {}, body: {}", message.getTopic(), message.getTags(), new String(message.getBody()));
//        int i = 5 / 0;
//        log.info("receive native msg, topic: {}, tag: {}", message.getTopic(), message.getTags());
    }

    @Override
    public void prepareStart(DefaultMQPushConsumer defaultMQPushConsumer) {
//        String topic = "NICE_2020";
//        String subExpression = "terrific";
//        try {
//            defaultMQPushConsumer.subscribe(topic, subExpression);
//        } catch (MQClientException e) {
//            log.error("Fail to subscribe topic: {}, tag: {} ", topic, subExpression, e);
//        }
    }
}
