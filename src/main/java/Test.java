import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;


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
    }
}
