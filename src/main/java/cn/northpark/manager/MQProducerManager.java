
package cn.northpark.manager;

/**
 * @author bruce
 * @date 2016-12-01
 * @email zhangyang226@gmail.com
 * @site http://blog.northpark.cn | http://northpark.cn | orginazation https://github.com/jellyband
 */
public interface MQProducerManager {

	 public void sendDataToQueue(String queueKey, Object object) ;
}


