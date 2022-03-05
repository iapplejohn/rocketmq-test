package com.jemmy.rocketmq.test.service;

import com.jemmy.rocketmq.test.domain.TestMsg;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.MessageModel;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

/**
 * @author zhujiang.cheng
 * @since 2020/7/17
 */
//@Component
@RocketMQMessageListener(
    consumerGroup = "niceGroup",
    topic = "TEST",
    messageModel = MessageModel.CLUSTERING,
    selectorExpression = "testTag")
@Slf4j
public class AnnotationMessageListener2 implements RocketMQListener<TestMsg> {

    @Override
    public void onMessage(TestMsg message) {
        log.info("receive msg: {}", message);
    }
}
