package com.jemmy.rocketmq.test.service;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;
import org.springframework.stereotype.Component;

/**
 * MQ监听器
 *
 * @author chengzhujiang
 * @since 2019/10/26
 */
@Slf4j
//@Component
public class TestMessageListener implements MessageListenerConcurrently {

    CountDownLatch latch = new CountDownLatch(1);

    @Override
    public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> list, ConsumeConcurrentlyContext consumeConcurrentlyContext) {
//        Thread.currentThread().interrupt();
//        Thread.currentThread().suspend();
//        try {
//            latch.await();
//        } catch (InterruptedException e) {
//            log.error("latch.await", e);
//        }

        for (MessageExt messageExt : list) {
            log.info("Receive msg, msgId: {}, queueId: {}, topic: {}, properties: {}",
                messageExt.getMsgId(), messageExt.getQueueId(), messageExt.getTopic(), messageExt.getProperties());

        }
//        try {
//            TimeUnit.SECONDS.sleep(1);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
    }
}
