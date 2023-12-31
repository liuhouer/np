package cn.northpark.utils;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.*;


/**
 * @author generate by chatGPT
 * @date 2023年02月08日 14:50:59
 */
public class RedisGPTUtil {

    private static JedisPool pool;

    //初始化连接池
    static {

        // 加载redis配置文件
        ResourceBundle bundle = ResourceBundle.getBundle("config");

        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(20);
        config.setMaxIdle(10);
        config.setMinIdle(5);
        config.setTestOnBorrow(true);
        config.setTestOnReturn(true);
        //pool = new JedisPool(config, "localhost", 6379);
        pool = new JedisPool(config, bundle.getString("redis.ip"), Integer.valueOf(bundle.getString("redis.port")), 10000, bundle.getString("redis.password"));

    }

    //获取Jedis实例
    public static Jedis getJedis() {
        return pool.getResource();
    }

    //读取String
    public static String getString(String key) {
        Jedis jedis = getJedis();
        String value = jedis.get(key);
        jedis.close();
        return value;
    }

    //写入String
    public static void setString(String key, String value) {
        Jedis jedis = getJedis();
        jedis.set(key, value);
        jedis.close();
    }

    //读取Set
    public static Set<String> getSet(String key) {
        Jedis jedis = getJedis();
        Set<String> set = jedis.smembers(key);
        jedis.close();
        return set;
    }

    //写入Set
    public static void setSet(String key, String... values) {
        Jedis jedis = getJedis();
        jedis.sadd(key, values);
        jedis.close();
    }

    //读取ZSet
    public static Set<String> getZSet(String key) {
        Jedis jedis = getJedis();
        Set<String> set = jedis.zrange(key, 0, -1);
        jedis.close();
        return set;
    }

    //写入ZSet
    public static void setZSet(String key, double score, String value) {
        Jedis jedis = getJedis();
        jedis.zadd(key, score, value);
        jedis.close();
    }

    //==================================hash==================================
    //读取Hash
    public static Map<String, String> getHash(String key) {
        Jedis jedis = getJedis();
        Map<String, String> map = jedis.hgetAll(key);
        jedis.close();
        return map;
    }

    // Hash:获取hash的数量
    public static Long hlen(String key) {
        Jedis jedis = getJedis();
        Long result = jedis.hlen(key);
        jedis.close();
        return result;
    }

    // Hash:根据key和字段获取值
    public static String hget(String key, String field) {
        Jedis jedis = getJedis();
        String result = jedis.hget(key, field);
        jedis.close();
        return result;
    }

    // Hash:根据key和字段设置值
    public static void hset(String key, String field, String value) {
        Jedis jedis = getJedis();
        jedis.hset(key, field, value);
        jedis.close();
    }



    //写入Hash
    public static void setHash(String key, Map<String, String> map) {
        Jedis jedis = getJedis();
        jedis.hmset(key, map);
        jedis.close();
    }

    //==================================hash==================================

    //读取List
    public static List<String> getList(String key) {
        Jedis jedis = getJedis();
        List<String> list = jedis.lrange(key, 0, -1);
        jedis.close();
        return list;
    }

    //写入List
    public static void setList(String key, String... values) {
        Jedis jedis = getJedis();
        jedis.lpush(key, values);
        jedis.close();
    }


    //添加分布式锁
    public static String tryLock(String lockKey, String lockValue, int expireTime) {
        Jedis jedis = getJedis();
        String result = jedis.set(lockKey, lockValue, "NX", "PX", expireTime);
        jedis.close();
        return "OK".equals(result) ? lockValue : null;
    }

    //解锁
    public static boolean unLock(String lockKey, String lockValue) {
        Jedis jedis = getJedis();
        String script = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";
        Object result = jedis.eval(script, Collections.singletonList(lockKey), Collections.singletonList(lockValue));
        jedis.close();
        return result != null && (Long) result == 1L;
    }

    //使用示例：
    //
    //String lockKey = "lockKey";
    //String requestId = UUID.randomUUID().toString();
    //if (RedisUtil.tryGetDistributedLock(lockKey, requestId, 10000)) {
    //    //获取锁成功，执行具体操作
    //    //...
    //    //释放锁
    //    RedisUtil.releaseDistributedLock(lockKey, requestId);
    //}



}

