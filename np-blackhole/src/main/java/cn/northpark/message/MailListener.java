//package cn.northpark.message;
//
//import cn.northpark.constant.BC_Constant;
//import cn.northpark.manager.UserManager;
//import cn.northpark.model.User;
//import cn.northpark.utils.EmailUtils;
//import cn.northpark.utils.JsonUtil;
//import cn.northpark.utils.ObjectUtil;
//import com.rabbitmq.client.Channel;
//import org.springframework.amqp.core.Message;
//import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import org.springframework.util.CollectionUtils;
//
//import java.util.List;
//import java.util.Map;
//
///**
// * 接收mq消息来处理邮件通知
// * Created on 2018/4/13 0013.
// *
// */
//@Component
//public class MailListener implements  ChannelAwareMessageListener {
//
//    @Autowired
//    private UserManager userManager;
//
//	@Override
//	public void onMessage(Message message, Channel channel) throws Exception {
//		// TODO Auto-generated method stub
//		Map<String,Object> data = (Map<String, Object>) ObjectUtil.bytesToObject(message.getBody()) ;
//		String receivedRoutingKey = message.getMessageProperties().getReceivedRoutingKey();
//		//发送注册邮件
//		if(receivedRoutingKey.equals(BC_Constant.MQ_MAIL_JOIN)) {
//
//			 String email = (String) data.get("email");
//			 //发送邮件
//			   try {
//
//				   EmailUtils.getInstance().ThanksReg(email);
//			   } catch (Exception e) {
//				  //发送失败禁用账户
//				   List<User> list = userManager.querySql( " select * from bc_user where email = ?",email);
//				   if(!CollectionUtils.isEmpty(list)) {
//					   User user = list.get(0);
//					   user.setEmail_flag("0");
//					   userManager.updateUser(user);
//				   }
//			   }
//
//		//发送重置邮件
//		}else if(receivedRoutingKey.equals(BC_Constant.MQ_MAIL_RESET)) {
//			 String email = (String) data.get("email");
//			 String userid = (String) data.get("userid");
//			 String code = (String) data.get("code");
//
//			try {
//				EmailUtils.getInstance().changePwd(email, userid, code);
//	        } catch (Exception e) {
////	        	log.error("重置密码邮件错误========>{}",e);
//	        	e.printStackTrace();
//	        }
//		}
//
//
//		System.out.println("bruce: received mq msg========>{"+JsonUtil.object2json(data)+"}");
//	}
//
//
//}
