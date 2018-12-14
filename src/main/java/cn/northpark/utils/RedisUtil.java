package cn.northpark.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;

import lombok.extern.slf4j.Slf4j;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

/**
 * redis缓存 
 * @Description: TODO 
 * 
 */
@Slf4j
public class RedisUtil {

//		   instance = new RedisUtil("69.12.82.101", 6379,"Cpdwvs678900");


	   private static JedisPool jedisPool = null;
	   private static ShardedJedisPool shardedJedisPool = null;
	   /**
	    * 初始化Redis连接池
	    */
	   static {
	       try {
	           // 加载redis配置文件
	           ResourceBundle bundle = ResourceBundle.getBundle("config");
	           if (bundle == null) {
	               throw new IllegalArgumentException(
	                       "[redis.properties] is not found!");
	           }
	           int maxActivity = Integer.valueOf(bundle
	                   .getString("redis.pool.maxActive"));
	           int maxIdle = Integer.valueOf(bundle
	                   .getString("redis.pool.maxIdle"));
	           String ip = bundle
	                   .getString("redis.ip");
	           int port = Integer.valueOf(bundle
	                   .getString("redis.port"));
	           int port1 = Integer.valueOf(bundle
	                   .getString("redis.port1"));
	           long maxWait = Long.valueOf(bundle.getString("redis.pool.maxWait"));
	           boolean testOnBorrow = Boolean.valueOf(bundle
	                   .getString("redis.pool.testOnBorrow"));
	           boolean onreturn = Boolean.valueOf(bundle
	                   .getString("redis.pool.testOnReturn"));
	           // 创建jedis池配置实例
	           JedisPoolConfig config = new JedisPoolConfig();
	           // 设置池配置项值
	           config.setMaxTotal(maxActivity);
	           config.setMaxIdle(maxIdle);  //最大空闲连接数
	           config.setMaxWaitMillis(maxWait);
	           config.setTestOnBorrow(testOnBorrow);
	           config.setTestOnReturn(onreturn);
	           jedisPool = new JedisPool(config, ip,
	                   port, 10000,
	                   bundle.getString("redis.password"));
	           // slave链接
	           List<JedisShardInfo> shards = new ArrayList<JedisShardInfo>();
	           shards.add(new JedisShardInfo(ip,  port1));
	           shardedJedisPool = new ShardedJedisPool(config, shards);
	           log.info("初始化Redis连接池success");
	       } catch (Exception e) {
	           log.error("初始化Redis连接池 出错！", e);
	       }
	   }

	   /**
	    * 获取Jedis实例
	    * 
	    * @return
	    */
	   public synchronized static Jedis getJedis() {
	       try {
	           if (jedisPool != null) {
	               Jedis resource = jedisPool.getResource();
	               return resource;
	           } else {
	               return null;
	           }
	       } catch (Exception e) {
	           log.error("Redis缓存获取Jedis实例 出错！", e);
	           return null;
	       }
	   }

	   /**
	    * 获取shardedJedis实例
	    * 
	    * @return
	    */
	   public static ShardedJedis getShardedJedis() {
	       try {
	           if (shardedJedisPool != null) {
	               ShardedJedis resource = shardedJedisPool.getResource();
	               return resource;
	           } else {
	               return null;
	           }
	       } catch (Exception e) {
	           log.error("Redis缓存获取shardedJedis实例 出错！", e);
	           return null;
	       }
	   }

	   /**
	    * 释放jedis资源
	    * 
	    * @param jedis
	    */
	   public static void returnResource(final Jedis jedis) {
	       if (jedis != null) {
	           jedisPool.returnResource(jedis);
	       }
	   }

	   /**
	    * 释放shardedJedis资源
	    * 
	    * @param jedis
	    */
	   public static void returnResource(final ShardedJedis shardedJedis) {
	       if (shardedJedis != null) {
	           shardedJedisPool.returnResource(shardedJedis);
	       }
	   }

	   
	   
	   
	   
	   /**
	    * 根据byte数组key获取数据
	    * 
	    *@param key byte[] 
	    *@return byte[]
	    */
	   public static byte[] get(byte[] key) {
	     Jedis jedis = null;
	     byte[] bytes = null;
	     try {
	       jedis = jedisPool.getResource();
	       bytes = jedis.get(key);
	     } catch (Exception e) {
	       log.error("获取缓存出错", e);
	     } finally {
	       returnResource(jedis);
	     }
	     return bytes;
	   }

	   /**
	    * 根据String key获取数据
	    * 
	    * @param key byte[] 
	    * @return String
	    */
	   public static String get(String key) {
	     Jedis jedis = null;
	     String value = null;
	     try {
	       jedis = jedisPool.getResource();
	       value = jedis.get(key);
	     } catch (Exception e) {
	       log.error("获取缓存出错", e);
	     } finally {
	       returnResource(jedis);
	     }
	     return value;
	   }

	   /**
	    * 根据key ,value存储,value最大1G
	    * 
	    *@param key byte[]
	    *@param value byte[]
	    */
	   public static void set(byte[] key, byte[] value) {
	     Jedis jedis = null;
	     try {
	       jedis = jedisPool.getResource();
	       String status = jedis.set(key, value);
//	       if (this.expire != 0) {
//	         jedis.expire(key, this.expire);
//	       }
	       log.info("jedis set status = {}", status);
	     } catch (Exception e) {
	       log.error("保存缓存出错", e);
	     } finally {
	       returnResource(jedis);
	     }
	   }

	   /**
	    * 根据key ,value ,expire(单位s)过期时间存储,value最大1G
	    * 
	    *@param key
	    *@param value
	    *@param expire
	    */
	   public static void set(byte[] key, byte[] value, int expire) {
	     Jedis jedis = null;
	     try {
	       jedis = jedisPool.getResource();
	       jedis.set(key, value);
	       if (expire != 0) {
	         jedis.expire(key, expire);
	       }
	     } catch (Exception e) {
	       log.error("保存缓存出错", e);
	     } finally {
	       returnResource(jedis);
	     }
	   }

	   /**
	    * 
	    * @param key
	    * @param value
	    */
	   public  static void set(String key, String value) {
	     Jedis jedis = null;
	     try {
	       jedis = jedisPool.getResource();
	       String status = jedis.set(key, value);
//	       if (this.expire != 0) {
//	         jedis.expire(key, this.expire);
//	       }
	       log.info("jedis set status = {}", status);
	     } catch (Exception e) {
	       log.error("保存缓存出错", e);
	     } finally {
	       returnResource(jedis);
	     }
	   }

	   /**
	    * 根据key ,value ,expire(单位s)过期时间存储,value最大1G
	    * 
	    * @param key
	    * @param value
	    * @param expire
	    */
	   public static void set(String key, String value, int expire) {
	     Jedis jedis = null;
	     try {
	       jedis = jedisPool.getResource();
	       jedis.set(key, value);
	       if (expire > 0) {
	         jedis.expire(key, expire);
	       }
	     } catch (Exception e) {
	       log.error("保存缓存出错", e);
	     } finally {
	       returnResource(jedis);
	     }
	   }
	   
	   /**
	    * 并发设置key
	    * @param key
	    * @param value
	    */
	   public static void setnx(String key, String value){
	     Jedis jedis = null;
	     try {
	       jedis = jedisPool.getResource();
	       long status = jedis.setnx(key, value);
//	       if (this.expire != 0) {
//	         jedis.expire(key, this.expire);
//	       }
	       log.info("jedis set status = {}", status);
	     } catch (Exception e) {
	       log.error("保存缓存出错", e);
	     } finally {
	       returnResource(jedis);
	     }
	   }
	   
	   /**
	    * 并发设置key
	    * @param key
	    * @param value
	    * @param expire
	    */
	   public static void setnx(String key, String value, int expire){
	     Jedis jedis = null;
	     try {
	       jedis = jedisPool.getResource();
	       long status = jedis.setnx(key, value);
	       if (expire > 0) {
	         jedis.expire(key, expire);
	       }
	       log.info("jedis set status = {}", status);
	     } catch (Exception e) {
	       log.error("保存缓存出错", e);
	     } finally {
	       returnResource(jedis);
	     }
	   }
	   /**
	    * 并发设置key
	    * @param key
	    * @param value
	    */
	   public static void setnx(byte[] key, byte[] value){
	     Jedis jedis = null;
	     try {
	       jedis = jedisPool.getResource();
	       long status = jedis.setnx(key, value);
//	       if (this.expire != 0) {
//	         jedis.expire(key, this.expire);
//	       }
	       log.info("jedis set status = {}", status);
	     } catch (Exception e) {
	       log.error("保存缓存出错", e);
	     } finally {
	       returnResource(jedis);
	     }
	   }
	   /**
	    * 并发设置key
	    * @param key
	    * @param value
	    * @param expire
	    */
	   public static void setnx(byte[] key, byte[] value, int expire){
	     Jedis jedis = null;
	     try {
	       jedis = jedisPool.getResource();
	       long status = jedis.setnx(key, value);
	       if (expire > 0) {
	         jedis.expire(key, expire);
	       }
	       log.info("jedis set status = {}", status);
	     } catch (Exception e) {
	       log.error("保存缓存出错", e);
	     } finally {
	       returnResource(jedis);
	     }
	   }

	   /**
	    * 根据key删除缓存
	    * 
	    * @param key
	    */
	   public static void del(byte[] key) {
	     Jedis jedis = null;
	     try {
	       jedis = jedisPool.getResource();
	       Long l = jedis.del(key);
	       log.info("jedis del number = {}", l);
	     } catch (Exception e) {
	       log.error("删除缓存出错", e);
	     } finally {
	       returnResource(jedis);
	     }
	   }

	   /**
	    * 
	    * @param key
	    */
	   public static void del(String key) {
	     Jedis jedis = null;
	     try {
	       jedis = jedisPool.getResource();
	       Long l = jedis.del(key);
	       log.info("jedis del number = {}", l);
	     } catch (Exception e) {
	       log.error("删除缓存出错", e);
	     } finally {
	       returnResource(jedis);
	     }
	   }

	   /**
	    * 获取所有的keys
	    * 
	    * @param pattern
	    * @return
	    */
	   public static Set<String> keys(String pattern) {
	     Jedis jedis = null;
	     Set<String> set = null;
	     try {
	       jedis = jedisPool.getResource();
	       set = jedis.keys(pattern);
	     } catch (Exception e) {
	       log.error("通过表达式获取keys出错", e);
	     } finally {
	       returnResource(jedis);
	     }
	     return set;
	   }

	   /**
	    * 
	    * @param pattern
	    * @return
	    */
	   public static Set<byte[]> keys(byte[] pattern) {
	     Jedis jedis = null;
	     Set<byte[]> set = null;
	     try {
	       jedis = jedisPool.getResource();
	       set = jedis.keys(pattern);
	     } catch (Exception e) {
	       log.error("通过表达式获取keys出错", e);
	     } finally {
	       returnResource(jedis);
	     }
	     return set;
	   }

	   /**
	    * 删除当前选中的DB
	    */
	   public static void delCurrentAll() {
	     Jedis jedis = null;
	     try {
	       jedis = jedisPool.getResource();
	       String status = jedis.flushDB();
	       log.info("jedis delAll status = {}", status);
	     } catch (Exception e) {
	       log.error("删除DB出错", e);
	     } finally {
	       returnResource(jedis);
	     }
	   }

	   /**
	    * 删除所有的DB
	    */
	   public static void delAll() {
	     Jedis jedis = null;
	     try {
	       jedis = jedisPool.getResource();
	       String status = jedis.flushAll();
	       log.info("jedis delAll status = {}", status);
	     } catch (Exception e) {
	       log.error("删除所有DB出错", e);
	     } finally {
	       returnResource(jedis);
	     }
	   }
	   
	   
	   public static void main(String[] args) {
		   RedisUtil  re = new RedisUtil();
		   re.set("a", "b");
	   }
	}


