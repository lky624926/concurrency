package com.lky.tools;

import java.util.concurrent.CountDownLatch;

/**
 * CountDownLatch用于阻止某些线程的执行,直到减少了指定的数目后,拦截的线程才会被放开
 */
public class CountDownLatchDemo {
    private static CountDownLatch countDownLatch = new CountDownLatch(2);

    private static CountDownLatch countDownLatch1 = new CountDownLatch(1);



    public static void main(String[] args) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    countDownLatch.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("thread 执行了");
            }
        });

        Thread threadA = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("threadA 执行了");
                countDownLatch.countDown();
            }
        });

        Thread threadB = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("threadB 执行了");
                countDownLatch1.countDown();
                countDownLatch.countDown();
            }
        });

        thread.start();
        threadA.start();
        threadB.start();
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("main线程执行了");
    }
}
