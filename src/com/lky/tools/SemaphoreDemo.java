package com.lky.tools;

import java.util.concurrent.Phaser;
import java.util.concurrent.Semaphore;

/**
 * Semaphore是一种线程锁,可以保证同一时间只有一定数量的线程在执行一段代码
 */
public class SemaphoreDemo {

    private static Semaphore semaphore = new Semaphore(1);

    public static void main(String[] args) {
        Thread threadA = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    semaphore.acquire();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("threadA 执行了");
                semaphore.release();
            }
        });

        Thread threadB = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    semaphore.acquire();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("threadB 执行了");
                semaphore.release();
            }
        });

        threadA.start();
        threadB.start();
    }
}
