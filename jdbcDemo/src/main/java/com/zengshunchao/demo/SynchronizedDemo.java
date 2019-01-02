package com.zengshunchao.demo;

public class SynchronizedDemo {

    //全局锁,锁住整个方法代码段
    public static synchronized void test() {

    }

    public void test1() {
        synchronized (SynchronizedDemo.class) {


            System.out.println("test1开始执行");
            try {
                Thread.sleep(1000);
            } catch (Exception e) {

            }
            System.out.println("test1结束执行");
        }
    }


}
