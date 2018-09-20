package cn.northpark.zookeeper;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @Title: ZKConnectDemo.java
 * @Description: zookeeper 恢复之前的会话连接demo演示
 */
public class ZKConnectSessionWatcher implements Watcher {
	
	final static Logger log = LoggerFactory.getLogger(ZKConnectSessionWatcher.class);

	public static final String zkServerPath = "69.12.82.101:2181";
	public static final Integer timeout = 5000;
	
	public static void main(String[] args) throws Exception {
		
		ZooKeeper zk = new ZooKeeper(zkServerPath, timeout, new ZKConnectSessionWatcher());
		
		long sessionId = zk.getSessionId();
		String ssid = "0x" + Long.toHexString(sessionId);
		System.out.println(ssid);
		byte[] sessionPassword = zk.getSessionPasswd();
		
		log.warn("客户端开始连接zookeeper服务器...");
		log.warn("连接状态：{}", zk.getState());
		new Thread().sleep(1000);
		log.warn("连接状态：{}", zk.getState());
		
		new Thread().sleep(200);
		
		// 开始会话重连
		log.warn("开始会话重连...");
		
		ZooKeeper zkSession = new ZooKeeper(zkServerPath, 
											timeout, 
											new ZKConnectSessionWatcher(), 
											sessionId, 
											sessionPassword);
		log.warn("重新连接状态zkSession：{}", zkSession.getState());
		new Thread().sleep(1000);
		log.warn("重新连接状态zkSession：{}", zkSession.getState());
	}
	
	@Override
	public void process(WatchedEvent event) {
		log.warn("接受到watch通知：{}", event);
	}
}

