package cn.northpark.test;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import cn.northpark.constant.BC_Constant;
import cn.northpark.manager.MQProducerManager;

/**
 * @author zhangyang
 * <p>
 * 定时爬取今日情圣文章
 */
public class Test_MQ  extends BaseTest{


	/**
	 * mq发消息
	 */
	@Autowired  
    private MQProducerManager messageProducer; 
	
    public void runTask() {
    	   //发送消息通知发邮件
        Map<String,Object> mqData=new HashMap<String,Object>();
        mqData.put("email", "654714226@qq.com");
        messageProducer.sendDataToQueue(BC_Constant.MQ_MAIL_JOIN, mqData);    	
    }


    //测试多页


    @Test
    public void save() {
        runTask();
    }

}
