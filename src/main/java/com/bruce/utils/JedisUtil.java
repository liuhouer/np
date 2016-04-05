package com.bruce.utils;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class JedisUtil {
	private static JedisPool pool = null;
	
	private static final Logger LOGGER = Logger
			.getLogger(JedisUtil.class);


	static{
		JedisPoolConfig config = new JedisPoolConfig();  
		config.setMaxIdle(10);
		config.setMinIdle(1);
		config.setMaxWaitMillis(10*2000);
		config.setTestOnBorrow(true);
		config.setMaxTotal(10);
		pool = new JedisPool(config, "123.56.129.117", 6379, 2000, "redisredis");
	}
	

	/**
	 * @author zhangyang
	 * 添加List[byte[],byte[]]
	 * @param key
	 * @param value
	 */
	public static void addList(String key,List list){
		Jedis jedis = pool.getResource();
		try {
			// 存入一个 List对象
			jedis.set(key.getBytes(), SerializationUtil.serialize(list));
		} catch (Exception e) {
			closeJedis(jedis);
			LOGGER.error("JedisUtil putString Exception",e);
		} finally {
			closeJedis(jedis);
		}
	}
	
	/**
	 * @author zhangyang
	 * 根据key【byte】取出value【byte【】】
	 * @param key
	 * @return byte[list]
	 */
	public static byte[] getListByte(String key){
		Jedis jedis = pool.getResource();
		byte[] b = null;
		try {
			// 取出byte数组
			b = jedis.get(key.getBytes());
		
		}catch (Exception e) {
			closeJedis(jedis);
			LOGGER.error("JedisUtil getList Exception",e);
		} finally {
			closeJedis(jedis);
		}
		return b;
	}
	
	/**
	 * 删除操作
	 * @param key
	 */
	public static void remove(String key){
		Jedis jedis = pool.getResource();
		try {
			jedis.del(key);
		} catch (Exception e) {
			closeJedis(jedis);
			LOGGER.error("JedisUtil putString Exception",e);
		} finally {
			closeJedis(jedis);
		}
	}
	
	
	/**
	 * 添加字符串
	 * @param key
	 * @param value
	 */
	public static void putString(String key,String value){
		Jedis jedis = pool.getResource();
		try {
			jedis.set(key, value);
		} catch (Exception e) {
			closeJedis(jedis);
			LOGGER.error("JedisUtil putString Exception",e);
		} finally {
			closeJedis(jedis);
		}
	}
	/**
	 * 字符串增加
	 * @param key
	 * @param value
	 */
	public static void appendString(String key,String value){
		Jedis jedis = pool.getResource();
		try {
			jedis.append(key, value);
		} catch (Exception e) {
			closeJedis(jedis);
			LOGGER.error("JedisUtil appendString Exception",e);
		} finally {
			closeJedis(jedis);
		}
	}
	/**
	 * 获取字符串
	 * @param key
	 * @return
	 */
	public static String getString(String key){
		Jedis jedis = pool.getResource();
		try {
			return jedis.get(key);
		} catch (Exception e) {
			closeJedis(jedis);
			LOGGER.error("JedisUtil getString Exception",e);
		} finally {
			closeJedis(jedis);
		}
		return null;
	}
	/**
	 * 向List中增加元素
	 * 从表象上来看，redis的List对象是个双向链表，输出时此操作按放入顺序输出
	 * @param key
	 * @param value
	 */
	public static void pushList(String key ,String value){
		Jedis jedis = pool.getResource();
		try {
			jedis.rpush(key, value);
		} catch (Exception e) {
			closeJedis(jedis);
			LOGGER.error("JedisUtil pushList Exception",e);
		} finally {
			closeJedis(jedis);
		}
	}
	/**
	 * 向List中增加元素
	 * 从表象上来看，redis的List对象是个双向链表，输出时此操作按放入倒序输出
	 * @param key
	 * @param value
	 */
	public static void pushList_stack(String key ,String value){
		Jedis jedis = pool.getResource();
		try {
			jedis.lpush(key, value);
		} catch (Exception e) {
			closeJedis(jedis);
			LOGGER.error("JedisUtil pushList_stack Exception",e);
		} finally {
			closeJedis(jedis);
		}
	}
	/**
	 * 获取List头结点元素,并删除
	 * @param key
	 * @return
	 */
	public static String popList(String key) {
		Jedis jedis = pool.getResource();
		try {
			return jedis.lpop(key);
		} catch (Exception e) {
			closeJedis(jedis);
			LOGGER.error("JedisUtil popList Exception",e);
		} finally {
			closeJedis(jedis);
		}
		return null;
	}
	/**
	 * 获取全部list内容
	 * @param key
	 * @return
	 */
	public static List<String> getList(String key) {
		return getList(key,0,-1);
	}
	/**
	 * 根据位置获取，若end大于LIST长度，则返回LIST长度的数据
	 * @param key
	 * @param start
	 * @param end
	 * @return
	 */
	public static List<String> getList(String key,int start ,int end){
		Jedis jedis = pool.getResource();
		try {
			return jedis.lrange(key, start, end);
		} catch (Exception e) {
			closeJedis(jedis);
			LOGGER.error("JedisUtil pushList Exception",e);
		} finally {
			closeJedis(jedis);
		}
		return null;
	}
	/**
	 * 获取LIST长度
	 * @param key
	 * @return
	 */
	public static long getListSize(String key){
		Jedis jedis = pool.getResource();
		try {
			return jedis.llen(key);
		} catch (Exception e) {
			closeJedis(jedis);
			LOGGER.error("JedisUtil pushList Exception",e);
		} finally {
			closeJedis(jedis);
		}
		return 0;
	}
	
	/**
	 * 放map
	 * 个人感觉redis的Map存储很鸡肋，不建议使用
	 * @param key
	 * @param map
	 */
	public static void putMap(String key ,Map<String,String> map){
		Jedis jedis = pool.getResource();
		try {
			jedis.hmset(key, map);
		} catch (Exception e) {
			closeJedis(jedis);
			LOGGER.error("JedisUtil putMap Exception",e);
		} finally {
			closeJedis(jedis);
		}
	}
	/**
	 * 根据key获取缓存中的Map ,然后再根据field(map中的key)获取value
	 * 个人感觉redis的Map存储很鸡肋，不建议使用
	 * @param key
	 * @param field
	 * @return
	 */
	public static List<String> getMapValue(String key,String field){
		Jedis jedis = pool.getResource();
		try {
			return jedis.hmget(key, field);
		} catch (Exception e) {
			closeJedis(jedis);
			LOGGER.error("JedisUtil getMapValue Exception",e);
		} finally {
			closeJedis(jedis);
		}
		return null;
	}
	/**
	 * 根据key获取缓存中的MapKeySet
	 * 个人感觉redis的Map存储很鸡肋，不建议使用
	 * @param key
	 * @param field
	 * @return
	 */
	public static Set<String> getMapKeys(String key){
		Jedis jedis = pool.getResource();
		try {
			if (!jedis.exists(key))
				return null;
			return jedis.hkeys(key);
		} catch (Exception e) {
			closeJedis(jedis);
			LOGGER.error("JedisUtil getMapKeys Exception",e);
		} finally {
			closeJedis(jedis);
		}
		return null;
	}
	/**
	 * 根据key获取缓存中的MapValues
	 * 个人感觉redis的Map存储很鸡肋，不建议使用
	 * @param key
	 * @param field
	 * @return
	 */
	public static List<String> getMapValues(String key) {
		Jedis jedis = pool.getResource();
		try {
			if (!jedis.exists(key))
				return null;
			return jedis.hvals(key);
		} catch (Exception e) {
			closeJedis(jedis);
			LOGGER.error("JedisUtil getMapValues Exception",e);
		} finally {
			closeJedis(jedis);
		}
		return null;
	}
	
	public static long getMapSize(String key) {
		Jedis jedis = pool.getResource();
		try {
			return jedis.hlen(key);
		} catch (Exception e) {
			closeJedis(jedis);
			LOGGER.error("JedisUtil getMapValues Exception",e);
		} finally {
			closeJedis(jedis);
		}
		return 0;
	}
	
	public static void closeJedis(Jedis jedis){
		if (jedis != null) {
			pool.returnResource(jedis);
			jedis = null;
		}
	}
}
