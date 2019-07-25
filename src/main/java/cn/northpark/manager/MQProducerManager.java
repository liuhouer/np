
package cn.northpark.manager;

/**
 * 封装关于MQ发送消息
 * @author bruce
 * @date  2018-11-1
 * @email zhangyang226@gmail.com
 * @site http://blog.northpark.cn | http://northpark.cn | orginazation https://github.com/jellyband
 */
public interface MQProducerManager {

	 public void sendDataToQueue(String queueKey, Object object) ;
}


