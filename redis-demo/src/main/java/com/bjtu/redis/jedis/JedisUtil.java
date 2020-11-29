package com.bjtu.redis.jedis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class JedisUtil {
    private static JedisPool jedisPool = JedisInstance.getInstance();

    public static void setIncrNum(String key , int field){
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            jedis.incrBy(key,Long.parseLong(String.valueOf(field)));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            jedisPool.returnResource(jedis);
        }
    }

    public static String getValueNum(String key){
        String ret=null;
        Jedis jedis=null;
        try{
            jedis = jedisPool.getResource();
            ret = jedis.get(key);
        }catch(Exception e){

        }finally {
            jedisPool.returnResource(jedis);
        }
        return ret;
    }

}
