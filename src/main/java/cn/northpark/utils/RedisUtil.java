package cn.northpark.utils;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import redis.clients.jedis.*;

import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;

/**
 * redis缓存
 * 
 * @Description: TODO
 * 
 */
@Slf4j
public class RedisUtil {

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
				throw new IllegalArgumentException("[redis.properties] is not found!");
			}
			int maxActivity = Integer.valueOf(bundle.getString("redis.pool.maxActive"));
			int maxIdle = Integer.valueOf(bundle.getString("redis.pool.maxIdle"));
			String ip = bundle.getString("redis.ip");
			int port = Integer.valueOf(bundle.getString("redis.port"));
			int port1 = Integer.valueOf(bundle.getString("redis.port1"));
			long maxWait = Long.valueOf(bundle.getString("redis.pool.maxWait"));
			boolean testOnBorrow = Boolean.valueOf(bundle.getString("redis.pool.testOnBorrow"));
			boolean onreturn = Boolean.valueOf(bundle.getString("redis.pool.testOnReturn"));
			// 创建jedis池配置实例
			JedisPoolConfig config = new JedisPoolConfig();
			// 设置池配置项值
			config.setMaxTotal(maxActivity);
			config.setMaxIdle(maxIdle); // 最大空闲连接数
			config.setMaxWaitMillis(maxWait);
			config.setTestOnBorrow(testOnBorrow);
			config.setTestOnReturn(onreturn);
			jedisPool = new JedisPool(config, ip, port, 10000, bundle.getString("redis.password"));
			// slave链接
			List<JedisShardInfo> shards = Lists.newArrayList();
			shards.add(new JedisShardInfo(ip, port1));
			shardedJedisPool = new ShardedJedisPool(config, shards);
			log.info("初始化Redis连接池success");
		} catch (Exception e) {
			log.error("初始化Redis连接池 出错！", e);
		}
	}

	/**
	 * 获取Jedis实例
	 *
	 * 注意：手动调用获取jedis 必须要finally释放掉！！！！！！！！
	 *
	 * 不建议调用
	 *
	 * @return
	 */
	public static Jedis getJedis() {
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
	 * 获取shardedJedis实例 注意：手动调用获取jedis 必须要finally释放掉！！！！！！！！ 不建议调用
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
	 * @param shardedJedis
	 */
	public static void returnResource(final ShardedJedis shardedJedis) {
		if (shardedJedis != null) {
			shardedJedisPool.returnResource(shardedJedis);
		}
	}

	/**
	 * 根据byte数组key获取数据
	 * 
	 * @param key
	 *            byte[]
	 * @return byte[]
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
	 * @param key
	 *            byte[]
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
	 * @param key
	 *            byte[]
	 * @param value
	 *            byte[]
	 */
	public static void set(byte[] key, byte[] value) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			String status = jedis.set(key, value);
			// if (this.expire != 0) {
			// jedis.expire(key, this.expire);
			// }
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
	public static void set(String key, String value) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			String status = jedis.set(key, value);
			// if (this.expire != 0) {
			// jedis.expire(key, this.expire);
			// }
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
	 * 
	 * @param key
	 * @param value
	 */
	public static void setnx(String key, String value) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			long status = jedis.setnx(key, value);
			// if (this.expire != 0) {
			// jedis.expire(key, this.expire);
			// }
			log.info("jedis set status = {}", status);
		} catch (Exception e) {
			log.error("保存缓存出错", e);
		} finally {
			returnResource(jedis);
		}
	}

	/**
	 * 并发设置key
	 * 
	 * @param key
	 * @param value
	 * @param expire
	 */
	public static void setnx(String key, String value, int expire) {
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
	 * 
	 * @param key
	 * @param value
	 */
	public static void setnx(byte[] key, byte[] value) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			long status = jedis.setnx(key, value);
			// if (this.expire != 0) {
			// jedis.expire(key, this.expire);
			// }
			log.info("jedis set status = {}", status);
		} catch (Exception e) {
			log.error("保存缓存出错", e);
		} finally {
			returnResource(jedis);
		}
	}

	/**
	 * 并发设置key
	 * 
	 * @param key
	 * @param value
	 * @param expire
	 */
	public static void setnx(byte[] key, byte[] value, int expire) {
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

	// set集合相关--集合获取
	public static Set<String> smembers(String key) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			return jedis.smembers(key);
		} catch (Exception e) {
			log.error("删除所有DB出错", e);
		} finally {
			returnResource(jedis);
		}
		return null;
	}

	// set集合相关--集合删除元素
	public static Long srem(String key, String member) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			return jedis.srem(key, member);
		} catch (Exception e) {
			log.error("删除一个set出错", e);
		} finally {
			returnResource(jedis);
		}
		return null;
	}

	// set集合相关--集合添加元素
	public static Long sadd(String key, String member) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			return jedis.sadd(key, member);
		} catch (Exception e) {
			log.error("set add 一个元素出错", e);
		} finally {
			returnResource(jedis);
		}
		return null;
	}

	// set集合相关--集合包含元素
	public static Boolean sismember(String key, String member) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			return jedis.sismember(key, member);
		} catch (Exception e) {
			log.error("set 包含一个元素出错", e);
		} finally {
			returnResource(jedis);
		}
		return null;
	}
	
	/**
	 * 有序set
	 * Redis Zadd 命令用于将一个或多个成员元素及其分数值加入到有序集当中。
		如果某个成员已经是有序集的成员，那么更新这个成员的分数值，并通过重新插入这个成员元素，来保证该成员在正确的位置上。
		分数值可以是整数值或双精度浮点数。
		如果有序集合 key 不存在，则创建一个空的有序集并执行 ZADD 操作。
		当 key 存在但不是有序集类型时，返回一个错误。
	 * @param key
	 * @param score
	 * @param member
	 * @return 被成功添加的新成员的数量，不包括那些被更新的、已经存在的成员。
	 */
	public static Long zadd(String key, double score, String member) {
		
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			Long result = jedis.zadd(key, score, member);
			return result;
		} catch (Exception e) {
			log.error("zadd 出错", e);
		} finally {
			returnResource(jedis);
		}
		
		return null;
	}
	
	
	/**
	 * Redis Zrevrangebyscore 返回有序集中指定分数区间内的所有的成员。有序集成员按分数值递减(从大到小)的次序排列。
		具有相同分数值的成员按字典序的逆序(reverse lexicographical order )排列。
		除了成员按分数值递减的次序排列这一点外， ZREVRANGEBYSCORE 命令的其他方面和 ZRANGEBYSCORE 命令一样。
	 * @param key
	 * @param max
	 * @param min
	 * @param offset
	 * @param count
	 * @return 指定区间内，带有分数值(可选)的有序集成员的列表。
	 */
	public static Set<String> zrevrangebyscore(String key, String max, String min, int offset, int count){
		
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			Set<String> result =  jedis.zrevrangeByScore(key, max, min, offset, count);
			return result;
		} catch (Exception e) {
			log.error("Zrevrangebyscore 出错", e);
		} finally {
			returnResource(jedis);
		}
		return null;
		
	}

	/**
	 * 有序集合：获取条数
	 * Redis Zcard 命令用于计算集合中元素的数量。
	 * @param key
	 */
	public static Long zcard(String key) {

		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			return jedis.zcard(key);
		} catch (Exception e) {
			log.error("zcard  出错", e);
		} finally {
			returnResource(jedis);
		}
		return null;
	}

	public static void main(String[] args) {
		RedisUtil re = new RedisUtil();
		re.set("a", "b");
	}
}
