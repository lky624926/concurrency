package com.lky.tools;

import java.util.concurrent.Exchanger;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Exchanger用于两个线程间数据交换
 */
public class ExchangerDemo {

    private static Exchanger exchanger = new Exchanger<String>();

    public static void main(String[] args) {
        Thread threadA = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Object threadAMsg = exchanger.exchange("threadA");
                    System.out.println("threadAMsg:"+threadAMsg);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread threadB = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Object threadBMsg = exchanger.exchange("threadB");
                    System.out.println("threadBMsg:"+threadBMsg);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        threadA.start();
        threadB.start();
    }
}
