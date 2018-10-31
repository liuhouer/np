//package cn.northpark.message;
//
//import org.springframework.cloud.stream.annotation.Input;
//import org.springframework.cloud.stream.annotation.Output;
//import org.springframework.messaging.MessageChannel;
//import org.springframework.messaging.SubscribableChannel;
//
///**
// * Created on 2018/4/13 0013.
// *
// * @author zlf
// * @email i@merryyou.cn
// * @since 1.0
// */
//public interface StreamClient {
//
//    String INPUT ="myMessage";
//    String INPUT2 ="myMessage2";
//
//    @Input(StreamClient.INPUT)
//    SubscribableChannel input();
//
//    @Output(StreamClient.INPUT)
//    MessageChannel output();
//
//    @Input(StreamClient.INPUT2)
//    SubscribableChannel input2();
//
//    @Output(StreamClient.INPUT2)
//    MessageChannel output2();
//}
