package com.jemmy.rocketmq.test.service;

import com.pingpongx.rocketmq.api.message.WithdrawOutboundMessage;
import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
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
 * @since 2020/7/21
 */
//@Service
@Slf4j
public class RocketMQTemplateProducer3 {

    @Resource
    private RocketMQTemplate rocketMQTemplate;

    @Scheduled(cron = "0/20 0/1 * * * ?")
    public void sendMsg() {
        WithdrawOutboundMessage obj = new WithdrawOutboundMessage();
        obj.setAcc_accountNo("12333333334434314243414314343");
        obj.setAcc_balance(new BigDecimal("1234315"));
        obj.setAcc_accountNo("12333333334434314243414314343");
        obj.setAcc_bankName("12333333334434314243414314343");
        obj.setAcc_currency("CNY");
        obj.setAcc_entityAccountNo("6333222222233333");
        obj.setAcc_holderName("12333333334434314243414314343");
        obj.setAcc_routingNo("43143125321412");
        obj.setAccId("12333333334434314243414314343");
        obj.setApplyDate(new Date());
        obj.setBeneficiary_accountName("12333333334434314243414314343");
        obj.setBeneficiary_bankName("12333333334434314243414314343");
        obj.setBeneficiary_bankNumber("12333333334434314243414314343");
        obj.setBeneficiary_branchCity("Hangzhou");
        obj.setBeneficiary_branchName("12333333334434314243414314343");
        obj.setBeneficiary_branchProvince("Zhejiang");
        obj.setBeneficiary_certificateNumber("12333333334434314243414314343");
        obj.setBeneficiary_contactName("12333333334434314243414314343");
        obj.setBeneficiary_contactPhoneNumber("314234143124312");
        obj.setBeneficiary_currency("CNY");
        obj.setBeneficiary_enterpriseName("12333333334434314243414314343");
        obj.setBeneficiary_holderType("12333333334434314243414314343");
        obj.setBeneficiary_swiftCode("3143214");
        obj.setBeneficiary_uid("dfafdafdafdafd");
        obj.setClientId("12333333334434314243414314343");
        obj.setLightYearChannel("fdafdasfdaf");
        obj.setPlatform("CYCVDDFDFS");
        obj.setRequestAmount(new BigDecimal("123456"));
        obj.setRequestCurrency("CNY");
        obj.setWithdrawId("12333333334434314243414314343");
        obj.setWithdrawType("CCC");

        // 方案1: MessageBuilder
        Message<WithdrawOutboundMessage> message = MessageBuilder
            .withPayload(obj)
            .setHeader(RocketMQHeaders.KEYS, System.currentTimeMillis())
            .setHeader(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_OCTET_STREAM) // 发送 JDK 序列化消息
            .build();

        // 同步发送
        SendResult sendResult = rocketMQTemplate.syncSend("NICE_2020:terrific", message, 6000);
        log.info("Send result 2: {}", sendResult);
    }
}
