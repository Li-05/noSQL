package com.bjtu.redis.tool;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;


public class JedisUtil {
    private static JedisPool jedisPool = JedisInstance.getInstance();

    /*
    进入周杰伦老哥直播间后，调用comeIn，参数为用户名，对count自增，并更新redis内用户数据
     */
    public static void comeIn(String userName){
        setIncr("count");
        setIncr(userName+"count");
    }

    /*
    退出周杰伦老哥的直播间后，调用runOut，参数为用户名，对count自减，并更新redis内用户数据
     */
    public static void runOut(String userName){
        setDecr("count");
    }

    /*
    返回获取到的直播间内人数count
     */
    public static int getCount(String key){
        Jedis jedis=null;
        int ret=-1;
        try{
            jedis = jedisPool.getResource();
            String s_ret = jedis.get(key);
            ret=Integer.parseInt(s_ret);
        }catch(Exception e){

        }finally {
            jedisPool.returnResource(jedis);
        }
        return ret;
    }

    //自增
    public static long setIncr(String key) {
        long result = 0;
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            result =jedis.incr(key);
        } catch (Exception e) {

        } finally {
            jedisPool.returnResource(jedis);
        }
        return result;
    }

    //自减
    public static long setDecr(String key) {
        long result = 0;
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            result =jedis.decr(key);
        } catch (Exception e) {

        } finally {
            jedisPool.returnResource(jedis);
        }
        return result;
    }

}
