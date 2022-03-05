package com.jemmy.rocketmq.test.service;

import com.jemmy.rocketmq.test.domain.TestMsg;
import com.pingpongx.rocketmq.api.seri.DefaultJDKMessageSeri;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**
 * @author zhujiang.cheng
 * @since 2020/7/15
 */
//@Service
@Slf4j
public class JdkSerializeProducer {

    @Resource
    private DefaultMQProducer testMessageProducer;

    private DefaultJDKMessageSeri seri = new DefaultJDKMessageSeri();

    @Scheduled(cron = "0/30 0/1 * * * ?")
    public void send() {
        TestMsg testMsg = new TestMsg("prospective");

        try {
            byte[] data = seri.serializing(testMsg);
            Message message = new Message("TEST", "testTag", data);
            message.setKeys(String.valueOf(System.currentTimeMillis()));

            log.info("Before sending msg: {}", message);
            testMessageProducer.send(message);
            log.info("Sending msg: {} complete", message);
        } catch (Exception e) {
            log.error("send error", e);
        }
    }

}
