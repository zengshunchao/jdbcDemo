package com.zengshunchao.demo;

public class MyThread extends Thread {

    @Override
    public void run() {
        SynchronizedDemo demo = new SynchronizedDemo();
        demo.test1();
    }

    public static void main(String[] args) {
        for (int i = 0; i < 3; i++) {
            Thread thread = new MyThread();
            thread.start();
        }

    }
}
