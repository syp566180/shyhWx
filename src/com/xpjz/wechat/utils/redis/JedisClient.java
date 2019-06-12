package com.xpjz.wechat.utils.redis;

/**
 * Created by chenyuping on 2018/8/7.
 */
public interface JedisClient {
    String set(String key, String value);
    String get(String key);
    Long incr(String key);
    Long hset(String key, String item, String value);
    String hget(String key, String item);
    Long hdel(String key ,String item);
    Long expire(String key, int second);
    Long ttl(String key);
}
