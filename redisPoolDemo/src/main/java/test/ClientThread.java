package test;

import redis.clients.jedis.Jedis;
import utils.RedisUtil;

import java.util.Date;
import java.text.SimpleDateFormat;

/**
 * @Author: zengshunchao
 * @Date: 2018/11/21 17:55
 */
public class ClientThread extends Thread {

    int i = 0;

    public ClientThread(int i) {
        this.i = i;
    }

    public void run() {
        Jedis jedis = RedisUtil.getConn();
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
        String time = sdf.format(date);
        jedis.set("key" + i, time);
        try {
            //每次睡眠一个随机时间
            Thread.sleep((int) (Math.random() * 5000));
            String foo = jedis.get("key" + i);
            System.out.println("【输出>>>>】key:" + foo + " 第:" + i + "个线程");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            RedisUtil.closeConn();
        }
    }
}
