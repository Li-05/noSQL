import redis.clients.jedis.Jedis;

public class Test {
    public Test(){

        Jedis jedis = new Jedis("127.0.0.1",6379);
        System.out.println("服务正在运行");


    }
    public static void main(String[] args) {
        Test a = new Test();
    }
}
