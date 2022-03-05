package com.jemmy.rocketmq.test.service;

import com.pingpongx.rocketmq.api.message.WithdrawOutboundMessage;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.MessageModel;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

/**
 * @author zhujiang.cheng
 * @since 2020/7/21
 */
//@Component
@RocketMQMessageListener(
    consumerGroup = "niceGroup",
    topic = "NICE_2020",
    messageModel = MessageModel.CLUSTERING,
    selectorExpression = "terrific")
@Slf4j
public class AnnotationMessageListener3 implements RocketMQListener<WithdrawOutboundMessage> {

    @Override
    public void onMessage(WithdrawOutboundMessage message) {
        log.info("receive withdraw outbound msg: {}", message);
    }
}
