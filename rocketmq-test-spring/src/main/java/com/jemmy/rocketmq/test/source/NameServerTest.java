//package com.jemmy.rocketmq.test.source;
//
//import java.util.concurrent.TimeUnit;
//import org.apache.commons.lang3.time.DateUtils;
//import org.apache.rocketmq.common.namesrv.NamesrvConfig;
//import org.apache.rocketmq.namesrv.NamesrvController;
//import org.apache.rocketmq.remoting.netty.NettyServerConfig;
//
///**
// * @author zhujiang.cheng
// * @since 2020/4/27
// */
//public class NameServerTest {
//
//    public static void main(String[] args) throws Exception {
//        // NamesrvConfig配置
//        final NamesrvConfig namesrvConfig = new NamesrvConfig();
//        // NettyServerConfig配置
//        final NettyServerConfig nettyServerConfig = new NettyServerConfig();
//        nettyServerConfig.setListenPort(9876); // 设置端口
//        // 创建 NamesrvController 对象，并启动
//        NamesrvController namesrvController = new NamesrvController(namesrvConfig, nettyServerConfig);
//        namesrvController.initialize();
//        namesrvController.start();
//        TimeUnit.MILLISECONDS.sleep(DateUtils.MILLIS_PER_DAY);
//    }
//
//}
