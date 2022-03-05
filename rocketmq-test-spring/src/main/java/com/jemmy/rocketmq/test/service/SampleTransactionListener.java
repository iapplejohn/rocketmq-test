package com.jemmy.rocketmq.test.service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionState;
import org.apache.rocketmq.spring.support.RocketMQHeaders;
import org.springframework.messaging.Message;

/**
 * @author zhujiang.cheng
 * @since 2020/7/22
 */
//@RocketMQTransactionListener
@Slf4j
public class SampleTransactionListener implements RocketMQLocalTransactionListener {

    private final Map<String, RocketMQLocalTransactionState> stateMap = new ConcurrentHashMap<>();

    /**
     * 执行本地事务
     *
     * @param msg 事务消息
     * @param arg 额外数据
     * @return 本地事务状态
     */
    @Override
    public RocketMQLocalTransactionState executeLocalTransaction(Message msg, Object arg) {
        String transId = (String) msg.getHeaders().get(RocketMQHeaders.TRANSACTION_ID);
        RocketMQLocalTransactionState state;

        try {
            log.info("开始执行事务, transId: {}", transId);
            TimeUnit.SECONDS.sleep(5);
//            int i = 5 / 0;
            log.info("事务执行完成, transId: {}", transId);
            state = RocketMQLocalTransactionState.COMMIT;
        } catch (Exception e) {
            state = RocketMQLocalTransactionState.ROLLBACK;
        }

        stateMap.put(transId, state);
        return state;
    }

    /**
     * RocketMQ broker 在一段时间内没有收到确认，将主动调用该方法确认本地事务的最终状态
     *
     * @param msg 事务消息
     * @return 本地事务状态
     */
    @Override
    public RocketMQLocalTransactionState checkLocalTransaction(Message msg) {
        String transId = (String) msg.getHeaders().get(RocketMQHeaders.TRANSACTION_ID);
        RocketMQLocalTransactionState state = stateMap.get(transId);
        log.info("检查事务状态, transId: {}, state: {}", transId, state);
        return state;
    }
}
