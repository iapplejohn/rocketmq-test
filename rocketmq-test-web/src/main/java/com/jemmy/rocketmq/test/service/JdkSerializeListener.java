package com.jemmy.rocketmq.test.service;

import com.jemmy.rocketmq.test.domain.TestMsg;
import com.pingpongx.rocketmq.api.seri.DefaultJDKMessageSeri;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;
import org.springframework.stereotype.Service;

/**
 * @author zhujiang.cheng
 * @since 2020/7/15
 */
@Service
@Slf4j
public class JdkSerializeListener implements MessageListenerConcurrently {

    private DefaultJDKMessageSeri seri = new DefaultJDKMessageSeri();

    @Override
    public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
        for (MessageExt msg : msgs) {
            log.info("Receive msg, msgId: {}, queueId: {}, topic: {}, properties: {}",
                msg.getMsgId(), msg.getQueueId(), msg.getTopic(), msg.getProperties());

            try {
                TestMsg testMsg = (TestMsg) seri.deserializing(msg.getBody());
                log.info("msg: {}", testMsg);
            } catch (Exception e) {
                log.error("consume failed, topic: " + msg.getTopic() + ", msg id: " + msg.getMsgId(), e);
                return ConsumeConcurrentlyStatus.RECONSUME_LATER;
            }
        }
        return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
    }
}
