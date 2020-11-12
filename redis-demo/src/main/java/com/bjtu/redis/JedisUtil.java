package com.bjtu.redis;

import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.logging.Logger;

public class JedisUtil {
    private static JedisPool jedisPool = JedisInstance.getInstance();

    public static long setIncr(String key, int cacheSeconds) {
        long result = 0;
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            result =jedis.incr(key);
            if (cacheSeconds != 0) {
                jedis.expire(key, cacheSeconds);
            }
            System.out.println("set "+ key + " = " + result);
        } catch (Exception e) {

        } finally {
            jedisPool.returnResource(jedis);
        }
        return result;
    }

}
