//package cn.northpark.service.impl;
//
//import cn.northpark.manager.MQProducerManager;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Service;
//
///**
// * @author bruce
// * @date 2016-12-01
// * @email zhangyang226@gmail.com
// * @site http://blog.northpark.cn | http://northpark.cn | orginazation https://github.com/jellyband
// */
//@Service
//@Slf4j
//public class MQProducerManagerImpl implements MQProducerManager {@Override
//	public void sendDataToQueue(String queueKey, Object object) {
//		// TODO Auto-generated method stub
//
//	}
//
////	@Autowired
////    private AmqpTemplate amqpTemplate;
////
////
////    //公共入队方法
////    public void sendDataToQueue(String queueKey, Object object) {
////        try {
////            amqpTemplate.convertAndSend(queueKey, object);
////        } catch (Exception e) {
////            log.error(e.toString());
////        }
////
////    }
//}
//
