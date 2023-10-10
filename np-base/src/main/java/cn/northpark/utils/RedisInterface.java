package cn.northpark.utils;

import java.util.List;
import java.util.Set;

/**
 * @author bruce
 * @date 2021年10月15日 15:46:56
 */
public interface RedisInterface {
    //===basic===
    byte[] get(byte[] key);

    String get(String key);

    void set(byte[] key, byte[] value);

    void set(byte[] key, byte[] value, int expire);

    void set(String key, String value);

    void set(String key, String value, int expire);

    void setNX(String key, String value);

    void setNX(String key, String value, int expire);

    void setNX(byte[] key, byte[] value);

    void setNX(byte[] key, byte[] value, int expire);

    void del(byte[] key);

    void del(String key);

    Set<String> keys(String pattern);

    Set<byte[]> keys(byte[] pattern);
    //===basic===

    //===set类型===
    Long sAdd(String key, String member);

    Boolean sIsMember(String key, String member);

    Set<String> sMembers(String key);

    Long sRem(String key, String member);
    //===set类型===


    //===list[有序、链表、队列]类型===
    Long lPush(String key, String member);

    String lPop(String key);

    Long lLen(String key);

    List<String> lRange(String key, long start, long end);// 获取列表指定区间的元素
    //===list[有序、链表、队列]类型===


    //===sorted set[TopN]类型===
    Long zAdd(String key,
              double score,
              String member);

    Set<String> zRangeByScore(String key,
                              String min,
                              String max,
                              int offset,
                              int count);//正序

    Set<String> zReRangeByScore(String key,
                                String max,
                                String min,
                                int offset,
                                int count);//倒序

    Long zRem(String key, String... members);//删除

    Long zCard(String key);//获取条数
    //===sorted set[TopN]类型===

    //=== hash ===
    String hGet(String key, String  field) ;
    Long hSet(String key, String  field ,String value);
    //=== hash ===

    //=== counter ===
    Long incrAndGet(String key);
    //=== counter ===

}
