package utils;

/**
 * @Author: zengshunchao
 * @Date: 2018/11/21 17:15
 */

import java.util.Properties;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * redis连接池工具类
 */
public class RedisUtil {
    //连接池
    private static JedisPool jedisPool = null;
    //读取文件
    private static String redisConfigFile = "redis.properties";

    //把redis连接对象放到本地线程中
    private static ThreadLocal<Jedis> local = new ThreadLocal<Jedis>();

    //不允许通过new创建该类的实例
    private RedisUtil() {
    }

    /**
     * 初始化连接池
     */
    public static void initialPool() {
        try {
            Properties props = new Properties();
            //加载连接池配置文件
            props.load(RedisUtil.class.getClassLoader().getResourceAsStream(redisConfigFile));
            // 创建jedis池配置实例
            JedisPoolConfig config = new JedisPoolConfig();
            // 设置池配置项值
            config.setMaxTotal(Integer.valueOf(props.getProperty("jedis.pool.maxActive")));
            config.setMaxIdle(Integer.valueOf(props.getProperty("jedis.pool.maxIdle")));
            config.setMaxWaitMillis(Long.valueOf(props.getProperty("jedis.pool.maxWait")));
            config.setTestOnBorrow(Boolean.valueOf(props.getProperty("jedis.pool.testOnBorrow")));
            config.setTestOnReturn(Boolean.valueOf(props.getProperty("jedis.pool.testOnReturn")));
            // 根据配置实例化jedis池
            jedisPool = new JedisPool(config, props.getProperty("redis.host"),
                    Integer.valueOf(props.getProperty("redis.port")),
                    Integer.valueOf(props.getProperty("redis.timeout")),
                    props.getProperty(""));
            System.out.println("线程池被成功初始化");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取连接
     *
     * @return jedis
     */
    public static Jedis getConn() {
        //Redis对象
        Jedis jedis = local.get();
        if (jedis == null) {
            if (jedisPool == null) {
                initialPool();
            }
            jedis = jedisPool.getResource();
            local.set(jedis);
        }
        return jedis;
    }

    /**
     * 关闭连接
     */
    public static void closeConn() {
        //从本地线程中获取
        Jedis jedis = local.get();
        if (jedis != null) {
            jedis.close();
        }
        local.set(null);
    }

    /**
     * 关闭连接池
     */
    public static void closePool() {
        if (jedisPool != null) {
            jedisPool.close();
        }
    }

    public static void main(String[] args) {
        System.out.println(getConn().ping());
    }
}
