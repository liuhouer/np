package cn.northpark.message;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.northpark.manager.MQProducerManager;

/**
 * @author w_zhangyang
 * 测试发送消息
 */
@Controller
@RequestMapping("/mq")
public class RabbitmqController {
 
	@Resource  
    private MQProducerManager messageProducer; 
	
	@RequestMapping(value="/mqSend",method = RequestMethod.GET)
	@ResponseBody
	public Map<String,Object> rabbitmqSend(HttpServletRequest request,HttpServletResponse response) throws Exception{
			String message = "bruce test mail send";
			Map<String,Object> map=new HashMap<String,Object>();
	        map.put("info", message);
	        messageProducer.sendDataToQueue("rest.mail.bruce", message);
		return map;
	}
	
 

}
