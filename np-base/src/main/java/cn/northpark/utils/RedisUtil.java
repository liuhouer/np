package cn.northpark.utils;

import lombok.extern.slf4j.Slf4j;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;

/**
 * @author jeey
 * redis单机常用方法工具类封装
 * <<<
 * 如果是集群或者哨兵模式，
 * 可以用接口+各个实现类+工厂模式来封装一套工具类
 * >>>
 */
@Slf4j
public class RedisUtil implements RedisInterface{


    //私有化构造函数，禁止new
    private RedisUtil() {
    }

    public enum OneLimitEnum {
        T;
        private RedisUtil singleton;

        /**
         * 构造函数
         */
        OneLimitEnum() {
            singleton = new RedisUtil();
        }

        //get
        public RedisUtil getInstance() {
            return singleton;
        }
    }


    /**
     * 获取一个唯一的实例
     *
     * @return
     */
    public static RedisUtil getInstance() {
        return OneLimitEnum.T.getInstance();
    }



    private static JedisPool jedisPool = null;

    //获取连接
    private static synchronized Jedis getJedis() {
        if (jedisPool == null) {
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
        }
        return jedisPool.getResource();
    }

    //向连接池返回连接
    private static void returnResource(Jedis jedis) {
        if(jedis!=null) {
            jedis.close();
        }
    }


    @Override
    public byte[] get(byte[] key) {
        Jedis jedis = null;
        byte[] bytes = null;
        try {
            jedis = getJedis();
            bytes = jedis.get(key);
        } catch (Exception e) {
            log.error("获取缓存出错", e);
        } finally {
            returnResource(jedis);
        }
        return bytes;
    }

    @Override
    public String get(String key) {
        Jedis jedis = null;
        String value = null;
        try {
            jedis = getJedis();
            value = jedis.get(key);
        } catch (Exception e) {
            log.error("获取缓存出错", e);
        } finally {
            returnResource(jedis);
        }
        return value;
    }

    @Override
    public void set(byte[] key, byte[] value) {
        Jedis jedis = null;
        try {
            jedis = getJedis();
            String status = jedis.set(key, value);
            log.info("jedis set status = {}", status);
        } catch (Exception e) {
            log.error("保存缓存出错", e);
        } finally {
            returnResource(jedis);
        }
    }

    @Override
    public void set(byte[] key, byte[] value, int expire) {
        Jedis jedis = null;
        try {
            jedis = getJedis();
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

    @Override
    public void set(String key, String value) {
        Jedis jedis = null;
        try {
            jedis = getJedis();
            String status = jedis.set(key, value);
            log.info("jedis set status = {}", status);
        } catch (Exception e) {
            log.error("保存缓存出错", e);
        } finally {
            returnResource(jedis);
        }
    }

    @Override
    public void set(String key, String value, int expire) {
        Jedis jedis = null;
        try {
            jedis = getJedis();
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

    @Override
    public void setNX(String key, String value) {
        Jedis jedis = null;
        try {
            jedis = getJedis();
            long status = jedis.setnx(key, value);
            log.info("jedis set status = {}", status);
        } catch (Exception e) {
            log.error("保存缓存出错", e);
        } finally {
            returnResource(jedis);
        }
    }

    @Override
    public void setNX(String key, String value, int expire) {
        Jedis jedis = null;
        try {
            jedis = getJedis();
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

    @Override
    public void setNX(byte[] key, byte[] value) {
        Jedis jedis = null;
        try {
            jedis = getJedis();
            long status = jedis.setnx(key, value);
            log.info("jedis set status = {}", status);
        } catch (Exception e) {
            log.error("保存缓存出错", e);
        } finally {
            returnResource(jedis);
        }
    }

    @Override
    public void setNX(byte[] key, byte[] value, int expire) {
        Jedis jedis = null;
        try {
            jedis = getJedis();
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

    @Override
    public void del(byte[] key) {
        Jedis jedis = null;
        try {
            jedis = getJedis();
            Long l = jedis.del(key);
            log.info("jedis del number = {}", l);
        } catch (Exception e) {
            log.error("删除缓存出错", e);
        } finally {
            returnResource(jedis);
        }
    }

    @Override
    public void del(String key) {
        Jedis jedis = null;
        try {
            jedis = getJedis();
            Long l = jedis.del(key);
            log.info("jedis del number = {}", l);
        } catch (Exception e) {
            log.error("删除缓存出错", e);
        } finally {
            returnResource(jedis);
        }
    }

    @Override
    public Set<String> keys(String pattern) {
        Jedis jedis = null;
        Set<String> set = null;
        try {
            jedis = getJedis();
            set = jedis.keys(pattern);
        } catch (Exception e) {
            log.error("通过表达式获取keys出错", e);
        } finally {
            returnResource(jedis);
        }
        return set;
    }

    @Override
    public Set<byte[]> keys(byte[] pattern) {
        Jedis jedis = null;
        Set<byte[]> set = null;
        try {
            jedis = getJedis();
            set = jedis.keys(pattern);
        } catch (Exception e) {
            log.error("通过表达式获取keys出错", e);
        } finally {
            returnResource(jedis);
        }
        return set;
    }

    @Override
    public Long sAdd(String key, String member) {
        Jedis jedis = null;
        try {
            jedis = getJedis();
            return jedis.sadd(key, member);
        } catch (Exception e) {
            log.error("set add 一个元素出错", e);
        } finally {
            returnResource(jedis);
        }
        return null;
    }

    @Override
    public Boolean sIsMember(String key, String member) {
        Jedis jedis = null;
        try {
            jedis = getJedis();
            return jedis.sismember(key, member);
        } catch (Exception e) {
            log.error("set 包含一个元素出错", e);
        } finally {
            returnResource(jedis);
        }
        return null;
    }

    @Override
    public Set<String> sMembers(String key) {
        Jedis jedis = null;
        try {
            jedis = getJedis();
            return jedis.smembers(key);
        } catch (Exception e) {
            log.error("删除所有DB出错", e);
        } finally {
            returnResource(jedis);
        }
        return null;
    }

    @Override
    public Long sRem(String key, String member) {
        Jedis jedis = null;
        try {
            jedis = getJedis();
            return jedis.srem(key, member);
        } catch (Exception e) {
            log.error("删除一个set出错", e);
        } finally {
            returnResource(jedis);
        }
        return null;
    }

    @Override
    public Long lPush(String key, String member) {
        Jedis jedis = null;
        try {
            jedis = getJedis();
            return jedis.lpush(key, member);
        } catch (Exception e) {
            log.error("lPush===>",e);
        } finally {
            returnResource(jedis);
        }
        return null;
    }


    @Override
    public String lPop(String key) {
        Jedis jedis = null;
        try {
            jedis = getJedis();
            return jedis.lpop(key);
        } catch (Exception e) {
            log.error("lPop===>", e);
        } finally {
            returnResource(jedis);
        }
        return null;
    }

    @Override
    public Long lLen(String key) {
        Jedis jedis = null;
        try {
            jedis = getJedis();
            return jedis.llen(key);
        } catch (Exception e) {
            log.error("lPop===>", e);
        } finally {
            returnResource(jedis);
        }
        return null;
    }

    @Override
    public List<String> lRange(String key, long start, long end) {
        Jedis jedis = null;
        try {
            jedis = getJedis();
            return jedis.lrange(key,start,end);
        } catch (Exception e) {
            log.error("lPop===>", e);
        } finally {
            returnResource(jedis);
        }
        return null;
    }

    @Override
    public Long zAdd(String key, double score, String member) {
        Jedis jedis = null;
        try {
            jedis = getJedis();
            return jedis.zadd(key,score,member);
        } catch (Exception e) {
            log.error("lPop===>", e);
        } finally {
            returnResource(jedis);
        }
        return null;
    }

    @Override
    public Set<String> zRangeByScore(String key, String min, String max, int offset, int count) {
        Jedis jedis = null;
        try {
            jedis = getJedis();
            Set<String> result = jedis.zrangeByScore(key, min, max, offset, count);
            return result;
        } catch (Exception e) {
            log.error("zRangebyScore===>", e);
        } finally {
            returnResource(jedis);
        }
        return null;
    }

    @Override
    public Set<String> zReRangeByScore(String key, String max, String min, int offset, int count) {
        Jedis jedis = null;
        try {
            jedis = getJedis();
            Set<String> result = jedis.zrevrangeByScore(key, max, min, offset, count);
            return result;
        } catch (Exception e) {
            log.error("Zrevrangebyscore 出错", e);
        } finally {
            returnResource(jedis);
        }
        return null;
    }

    @Override
    public Long zRem(String key, String... members) {
        Jedis jedis = null;
        try {
            jedis = getJedis();

            return jedis.zrem(key, members);
        } catch (Exception e) {
            log.error("zrem 出错", e);
        } finally {
            returnResource(jedis);
        }
        return null;
    }

    @Override
    public Long zCard(String key) {
        Jedis jedis = null;
        try {
            jedis = getJedis();

            return jedis.zcard(key);
        } catch (Exception e) {
            log.error("zcard 出错", e);
        } finally {
            returnResource(jedis);
        }
        return null;
    }


    /**
     * hash get
     * @param key
     * @param field
     * @return
     */
    @Override
    public String hGet(String key, String  field) {
        Jedis jedis = null;
        try {
            jedis = getJedis();

            return jedis.hget(key,field);
        } catch (Exception e) {
            log.error("hGet 出错", e);
        } finally {
            returnResource(jedis);
        }
        return null;
    }


    /**
     * hash set
     * @param key
     * @param field
     * @param value
     * @return
     */
    @Override
    public Long hSet(String key, String  field ,String value) {
        Jedis jedis = null;
        try {
            jedis = getJedis();

            return jedis.hset(key,field,value);
        } catch (Exception e) {
            log.error("hSet 出错", e);
        } finally {
            returnResource(jedis);
        }
        return null;
    }

    @Override
    public Long incrAndGet(String key) {
        Jedis jedis = null;
        try {
            jedis = getJedis();
            // 获取当前计数器的值
            Long counter = jedis.incr(key);

            return counter;
        } catch (Exception e) {
            System.err.println(e);
            log.error("incrAndGet 出错", e);
        } finally {
            returnResource(jedis);
        }
        return null;
    }

    /**
     * 删除当前选中的DB
     */
    public static void delCurrentAll() {
        Jedis jedis = null;
        try {
            jedis = getJedis();
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
            jedis = getJedis();
            String status = jedis.flushAll();
            log.info("jedis delAll status = {}", status);
        } catch (Exception e) {
            log.error("删除所有DB出错", e);
        } finally {
            returnResource(jedis);
        }
    }

    public static void main(String[] args) {
//		RedisUtil re = new RedisUtil();
//		re.set("a", "b");

        Set<String> donates_list_min_z1 = RedisUtil.getInstance().zRangeByScore("donates_list_min_z1", "12", "0", 0, 12);
        System.err.println(donates_list_min_z1);
    }
}
