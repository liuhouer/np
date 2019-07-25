//package cn.northpark.test;
//
//import java.util.HashMap;
//import java.util.Map;
//
//import org.junit.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import cn.northpark.constant.BC_Constant;
//import cn.northpark.manager.MQProducerManager;
//
///**
// * @author zhangyang
// * <p>
// * 定时爬取今日情圣文章
// */
//public class Test_MQ  extends BaseTest{
//
//
//	/**
//	 * mq发消息
//	 */
//	@Autowired  
//    private MQProducerManager messageProducer; 
//	
//    public void runTask() {
//    	   //发送消息通知发邮件
//    	for (int i = 0; i < 3; i++) {
//			
//    		Map<String,Object> mqData=new HashMap<String,Object>();
//    		mqData.put("email", "r48866@gmail.com");
//    		messageProducer.sendDataToQueue(BC_Constant.MQ_MAIL_JOIN, mqData);    	
//		}
//    }
//
//
//    //测试多页
//
//
//    @Test
//    public void save() {
//        runTask();
//    }
//
//}
