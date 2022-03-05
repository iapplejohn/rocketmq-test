//package com.jemmy.rocketmq.test.source;
//
//import java.util.concurrent.TimeUnit;
//import org.apache.commons.lang3.time.DateUtils;
//import org.apache.rocketmq.broker.BrokerController;
//import org.apache.rocketmq.common.BrokerConfig;
//import org.apache.rocketmq.common.MQVersion;
//import org.apache.rocketmq.remoting.netty.NettyClientConfig;
//import org.apache.rocketmq.remoting.netty.NettyServerConfig;
//import org.apache.rocketmq.remoting.protocol.RemotingCommand;
//import org.apache.rocketmq.store.config.FlushDiskType;
//import org.apache.rocketmq.store.config.MessageStoreConfig;
//
///**
// * @author zhujiang.cheng
// * @since 2020/4/27
// */
//public class BrokerControllerTest {
//
//    public static void main(String[] args) throws Exception {
//        // 设置版本号
//        System.setProperty(RemotingCommand.REMOTING_VERSION_KEY, Integer.toString(MQVersion.CURRENT_VERSION));
//        // NettyServerConfig 配置
//        final NettyServerConfig nettyServerConfig = new NettyServerConfig();
//        nettyServerConfig.setListenPort(10911);
//        // BrokerConfig 配置
//        final BrokerConfig brokerConfig = new BrokerConfig();
//        brokerConfig.setBrokerName("broker-a");
//        brokerConfig.setNamesrvAddr("127.0.0.1:9876");
//        // MessageStoreConfig 配置
//        final MessageStoreConfig messageStoreConfig = new MessageStoreConfig();
//        messageStoreConfig.setDeleteWhen("04");
//        messageStoreConfig.setFileReservedTime(72);
//        messageStoreConfig.setFlushDiskType(FlushDiskType.ASYNC_FLUSH);
//        messageStoreConfig.setDuplicationEnable(false);
//
//        //        BrokerPathConfigHelper.setBrokerConfigPath("/Users/yunai/百度云同步盘/开发/Javascript/Story/incubator-rocketmq/conf/broker.conf");
//        // 创建 BrokerController 对象，并启动
//        BrokerController brokerController = new BrokerController(
//            brokerConfig,
//            nettyServerConfig,
//            new NettyClientConfig(),
//            messageStoreConfig
//        );
//        brokerController.initialize();
//        brokerController.start();
//        // 睡觉，就不起来
//        System.out.println("你猜");
//        TimeUnit.MILLISECONDS.sleep(DateUtils.MILLIS_PER_DAY);
//    }
//
//}
