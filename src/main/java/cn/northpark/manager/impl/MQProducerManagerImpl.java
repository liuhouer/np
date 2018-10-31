package cn.northpark.manager.impl;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Service;

import cn.northpark.manager.MQProducerManager;

/**
 * @author bruce
 * @date 2016-12-01
 * @email zhangyang226@gmail.com
 * @site http://blog.northpark.cn | http://northpark.cn | orginazation https://github.com/jellyband
 */
@Service
public class MQProducerManagerImpl implements MQProducerManager {

	@Resource
    private AmqpTemplate amqpTemplate;

    private final static Logger logger = LoggerFactory.getLogger(MQProducerManagerImpl.class);

    //公共入队方法
    public void sendDataToQueue(String queueKey, Object object) {
        try {
            amqpTemplate.convertAndSend(queueKey, object);
        } catch (Exception e) {
            logger.error(e.toString());
        }

    }
}

