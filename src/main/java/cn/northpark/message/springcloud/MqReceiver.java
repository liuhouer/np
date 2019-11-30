package cn.northpark.message.springcloud;
//package cn.northpark.message;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.amqp.rabbit.annotation.Exchange;
//import org.springframework.amqp.rabbit.annotation.Queue;
//import org.springframework.amqp.rabbit.annotation.QueueBinding;
//import org.springframework.amqp.rabbit.annotation.RabbitListener;
//import org.springframework.stereotype.Component;
//
///**
// * 接收mq消息
// * Created on 2018/4/13 0013.
// *
// * @author zlf
// * @email i@merryyou.cn
// * @since 1.0
// */
//@Slf4j
//@Component
//public class MqReceiver {
//
//	//   1.需要手动创建队列 @RabbitListener(queues = "myQueue") 
//	//   2.自动创建队列  @RabbitListener(queuesToDeclare = @Queue("myQueue")) 
//    //   3.自动创建Exchange和Queue绑定
//    @RabbitListener(bindings = @QueueBinding(
//            value = @Queue("myQueue"),
//            exchange = @Exchange("myExchange")
//    ))
//    public void process(String message){
//        log.info("MqReceiver：{}",message);
//    }
//
//    /**
//     * 接收电脑订单消息的服务
//     * @param message
//     */
//    @RabbitListener(bindings = @QueueBinding(
//            value = @Queue("myOrder"),
//            key = "computer",
//            exchange = @Exchange("computerOrder")
//    ))
//    public void processComputer(String message){
//        log.info(" computer MqReceiver：{}",message);
//    }
//
//    /**
//     * 接收水果订单消息的服务
//     * @param message
//     */
//    @RabbitListener(bindings = @QueueBinding(
//            value = @Queue("myOrder"),
//            key = "fruit",
//            exchange = @Exchange("fruitOrder")
//    ))
//    public void processFruit(String message){
//        log.info("fruit MqReceiver：{}",message);
//    }
//}
