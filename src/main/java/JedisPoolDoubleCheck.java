import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import redis.clients.jedis.JedisPool;

public class JedisPoolDoubleCheck {
    private static volatile JedisPool jedisPool = null;
    private JedisPoolDoubleCheck(){}

    public static JedisPool getRedisPoolUtil() {
        if(null == jedisPool ){
            synchronized (JedisPoolDoubleCheck.class){
                if(null == jedisPool){
                    GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig();
                    poolConfig.setMaxTotal(100);
                    poolConfig.setMaxIdle(10);
                    poolConfig.setMaxWaitMillis(100*1000);
                    poolConfig.setTestOnBorrow(true);
                    jedisPool = new JedisPool(poolConfig,"127.0.0.1",6379);
                }
            }
        }
        return jedisPool;
    }
}
