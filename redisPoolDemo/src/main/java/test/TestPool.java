package test;

import utils.RedisUtil;

/**
 * @Author: zengshunchao
 * @Date: 2018/11/21 17:54
 */
public class TestPool {

    public static void main(String[] args) {
        //初始化连接池
        RedisUtil.initialPool();
        //启动1000个线程
        for (int i = 0; i < 1000; i++) {
            ClientThread t = new ClientThread(i);
            t.start();
        }
    }
}
