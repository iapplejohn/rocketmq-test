package com.jemmy.rocketmq.test.service;

import java.util.Random;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

//@Service
@Slf4j
public class TestProducer {

    @Resource
    private DefaultMQProducer testMessageProducer;

    private Random random = new Random();

    @Scheduled(cron = "0/30 0/1 * * * ?")
    public void send() {
        String body = "message " + random.nextInt(9999999);

        Message message = new Message("TEST", "testTag", body.getBytes());
        message.setKeys(String.valueOf(System.currentTimeMillis()));
//        message.setDelayTimeLevel(3);
//        message.setWaitStoreMsgOK(true);

        try {
            log.info("Before sending msg: {}", message);
            testMessageProducer.send(message);
            log.info("Sending msg: {} complete", message);
        } catch (MQClientException | RemotingException | MQBrokerException | InterruptedException e) {
            log.error("send error", e);
        }
    }

}
