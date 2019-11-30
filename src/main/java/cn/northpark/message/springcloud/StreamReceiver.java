package cn.northpark.message.springcloud;
//package cn.northpark.message;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.cloud.stream.annotation.EnableBinding;
//import org.springframework.cloud.stream.annotation.StreamListener;
//import org.springframework.messaging.handler.annotation.SendTo;
//import org.springframework.stereotype.Component;
//
///**
// * Created on 2018/4/13 0013.
// *
// * @author zlf
// * @email i@merryyou.cn
// * @since 1.0
// */
//@Component
//@EnableBinding(StreamClient.class)
//@Slf4j
//public class StreamReceiver {
//
////    @StreamListener(StreamClient.INPUT)
////    public void process(Object message) {
////        log.info("StreamReceiver:{}", message);
////    }
//
//    /**
//     * 接收OrderDTO对象 消息
//     *
//     * @param message
//     */
//    @StreamListener(StreamClient.INPUT)
//    @SendTo(StreamClient.INPUT2)//用于接收到消息之后回复消息
//    public String process(Object message) {
//        log.info("StreamReceiver:{}", message);
//
//        return "received!";
//    }
//
//    @StreamListener(StreamClient.INPUT2)
//    public void process2(String message) {
//        log.info("StreamReceiver2:{}", message);
//    }
//}
