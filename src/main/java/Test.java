import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.HashMap;
import java.util.Map;

public class Test {
    /*
    public static Integer getRedisIsOk(String url, int port) {
        int result=0;
        try {
            Jedis jedis = new Jedis(url, port);
            String ping = jedis.ping();
            if (ping.equalsIgnoreCase("PONG")) {
                System.out.println("redis缓存有效！" + ping);
                result = 0;
            }
        }catch (Exception e){
            System.out.println("redis缓存失败！");
            result = 1;
        }
        return result;
    }
    */
    public static void main(String[] args) {
        JedisPool jedisPool = JedisPoolDoubleCheck.getRedisPoolUtil();
        Jedis jedis = jedisPool.getResource();
        jedis.set("name", "Alex");
        System.out.println(jedis.get("name"));
        /*
        Map<String,String> cartInfo = new HashMap<>();
        cartInfo.put("10088","1");
        cartInfo.put("10099","2");
        jedis.hset("cart:1001",cartInfo);
        System.out.println(jedis.hget("cart:1001","10088"));
        System.out.println(jedis.hget("cart:1001","10099"));
        System.out.println(jedis.hgetAll("cart:1001"));
         */

    }
}
