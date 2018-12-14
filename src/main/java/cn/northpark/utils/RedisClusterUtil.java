package cn.northpark.utils;

import java.io.IOException;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import lombok.extern.slf4j.Slf4j;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPoolConfig;

/**
 * redis缓存 
 * @Description: TODO 
 * 
 */
@Slf4j
public class RedisClusterUtil {
	
	

//redis.clusterNodes=114.215.142.252:6379
//#redis.clusterNodes=127.0.0.1:6379
//redis.password=bugu
//redis.timeout=1000
//redis.maxIdle=50
//redis.maxTotal=200
//redis.maxWaitMillis=5000
//redis.db=0

    public static final Integer MONTH_SECONDS = 60 * 60 * 24 * 30;
    public static final Integer WEEK_SECONDS = 60 * 60 * 24 * 7;
    public static final Integer DAY_SECONDS = 60 * 60 * 24;
    public static final Integer HOUR_SECONDS = 60 * 60;

 

    private static Set<HostAndPort> jedisClusterNodes;
    private static JedisPoolConfig config;


    static {

        try {
            config = new JedisPoolConfig();
            config.setMaxIdle(50);
            config.setMaxTotal(200);
            config.setMaxWaitMillis(5000);
            config.setTestOnBorrow(true);

//            redis集群的节点集合
            jedisClusterNodes = new HashSet<>();
            String nodeInfo = "10.88.147.57:7001,10.88.147.57:7002,10.88.147.57:7003,10.88.147.57:7004,10.88.147.57:7005,10.88.147.57:7006";
            String[] nodeList = nodeInfo.trim().split(",");

            if (nodeList.length > 0) {
                for (String node : nodeList) {
                    String[] item = node.split(":");
                    String ip = item[0];
                    Integer port = Integer.parseInt(item[1]);
                    jedisClusterNodes.add(new HostAndPort(ip, port));
                }
            } else {
                throw new Exception("初始化jedis异常, redis.clusterNodes参数配置有误，需要配置集群");
            }
        } catch (Exception e) {
            log.error("初始化异常", e);
        }
    }

    /**
     * 释放资源
     *
     * @param jedisCluster
     */
    public static void release(JedisCluster jedisCluster) {
        if (jedisCluster != null) {
            try {
                jedisCluster.close();
            } catch (IOException e) {
                log.error("redis cluster 释放失败", e);
            }
        }
    }

    /**
     * 获取Jedis实例
     *
     * @return
     */
    public static JedisCluster getJedisCluster() {
        /**
         * 节点，超时时间、最多重定向次数，连接池
         * */
        JedisCluster jedisCluster = new JedisCluster(jedisClusterNodes, 2000, 100, config);
        return jedisCluster;
    }



    public static void set(String str,String value) {
    	JedisCluster cluster = null;
    	try {
    		cluster =  RedisClusterUtil.getJedisCluster();
    		cluster.set(str,value);
   	     	log.info( new Date()+"--->set--->"+str);	 
		} finally {
			// TODO: handle finally clause
			RedisClusterUtil.release(cluster);
		}
    	 
    }


	public static void main(String[] args) {
		
		 JedisCluster cluster = RedisClusterUtil.getJedisCluster();
	     cluster.set("shit", "nice");
	    
	     log.info( cluster.get("shit"));	 
//	     RedisClusterUtil.release(cluster);
	     
	}

}


