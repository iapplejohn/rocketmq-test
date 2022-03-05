package com.jemmy.rocketmq.test.config;

import lombok.Getter;
import lombok.Setter;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.consumer.rebalance.AllocateMessageQueueAveragely;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.protocol.heartbeat.MessageModel;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
@Setter
@ConfigurationProperties("rocketmq")
public class RocketMQConfig {

    private static final String ROCKET_MQ_PREFIX = "rocketmq";

    private String namesrvAddr;

    private String instanceName;

    private String producerGroup;

    static {
        System.setProperty("rocketmq.client.log.loadconfig", "false");
    }

    @Bean
    @ConditionalOnProperty(prefix = ROCKET_MQ_PREFIX, name = "namesrvAddr")
    public DefaultMQProducer testMessageProducer() throws MQClientException {
//        DefaultMQProducer mqProducer = new DefaultMQProducer(producerGroup, true);
        DefaultMQProducer mqProducer = new DefaultMQProducer();
        mqProducer.setRetryTimesWhenSendFailed(2);

        mqProducer.setNamesrvAddr(namesrvAddr);
//        mqProducer.setInstanceName(instanceName);
        mqProducer.setProducerGroup(producerGroup);
        mqProducer.start();
        return mqProducer;
    }

    private String topic;
    private String subExpression;
    private String consumerGroup;

    @Bean
    @ConditionalOnProperty(prefix = ROCKET_MQ_PREFIX, name = "namesrvAddr")
    public DefaultMQPushConsumer testMessageConsumer(MessageListenerConcurrently jdkSerializeListener) throws MQClientException {
//        DefaultMQPushConsumer pushConsumer = new DefaultMQPushConsumer(consumerGroup, true);
        DefaultMQPushConsumer pushConsumer = new DefaultMQPushConsumer();

        pushConsumer.setNamesrvAddr(namesrvAddr);
        pushConsumer.setInstanceName(instanceName);
        pushConsumer.subscribe(topic, subExpression);
        pushConsumer.setConsumerGroup(consumerGroup);
        pushConsumer.setMessageModel(MessageModel.CLUSTERING);
        pushConsumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_LAST_OFFSET);
        pushConsumer.registerMessageListener(jdkSerializeListener);
//        pushConsumer.registerMessageListener();
        pushConsumer.setAllocateMessageQueueStrategy(new AllocateMessageQueueAveragely());
        pushConsumer.setConsumeMessageBatchMaxSize(3);
        pushConsumer.start();
        return pushConsumer;
    }
}
