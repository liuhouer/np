package cn.northpark.message;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;
import org.springframework.stereotype.Component;

import com.rabbitmq.client.Channel;

/**
 * 接收mq消息
 * Created on 2018/4/13 0013.
 *
 */
@Component
public class MailReceiver implements  ChannelAwareMessageListener {
    private static final  Logger log =  LoggerFactory.getLogger(MailReceiver.class);

	@Override
	public void onMessage(Message message, Channel channel) throws Exception {
		// TODO Auto-generated method stub
		log.info(message.toString()+"---->{}",message.getBody());
	}


}
