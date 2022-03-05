package com.jemmy.rocketmq.test.service;

import com.jemmy.rocketmq.test.domain.TestMsg;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.spring.annotation.MessageModel;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

/**
 * @author zhujiang.cheng
 * @since 2020/5/13
 */
//@Component
@RocketMQMessageListener(
    consumerGroup = "testGroup",
    topic = "TEST",
    messageModel = MessageModel.CLUSTERING,
    selectorExpression = "testTag")
@Slf4j
public class AnnotationMessageListener implements RocketMQListener<MessageExt> {

    @Override
    public void onMessage(MessageExt message) {
        log.info("receive msg: {}", message);
    }
}
