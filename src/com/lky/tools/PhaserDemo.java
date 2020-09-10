package com.lky.tools;

import java.util.concurrent.Phaser;

/**
 * Phaser设置多重屏障,第一重屏障到达指定人数后,全部放行,再有线程到达的时候卡在第二重屏障处,到达指定线程数后再次全部放行
 */
public class PhaserDemo {

    private static Phaser phaser = new Phaser(2);

    public static void main1(String[] args) {
        Thread threadA = new Thread(new Runnable() {
            @Override
            public void run() {
                phaser.arriveAndAwaitAdvance();
                System.out.println("threadA 执行了");
                phaser.arriveAndAwaitAdvance();
                System.out.println("threadA-2 执行了");
            }
        });

        Thread threadB = new Thread(new Runnable() {
            @Override
            public void run() {
                phaser.arriveAndAwaitAdvance();
                System.out.println("threadB 执行了");
            }
        });

        Thread threadC = new Thread(new Runnable() {
            @Override
            public void run() {
                phaser.arriveAndAwaitAdvance();
                System.out.println("threadC 执行了");
            }
        });

        threadA.start();
        threadB.start();
        threadC.start();
    }

    public static void main(String[] args) {
        Thread threadA = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                phaser.arriveAndAwaitAdvance();
                int phase = phaser.getPhase();
                System.out.println("threadA 执行了");
                System.out.println("现在是第"+phase+"个屏障");
                phaser.arriveAndAwaitAdvance();
                int phase1 = phaser.getPhase();
                System.out.println("threadA-2 执行了");
                System.out.println("现在是第"+phase1+"个屏障");
            }
        });

        Thread threadB = new Thread(new Runnable() {
            @Override
            public void run() {
                //线程B退出了,不会等到其他线程到拦截点,它直接就过去了,现在phaser里的值是1,只要有一个线程到达就放行
                phaser.arriveAndDeregister();
                int phase = phaser.getPhase();
                System.out.println("threadB 执行了");
                System.out.println("现在是第"+phase+"个屏障");
            }
        });

        threadB.start();
        threadA.start();
    }
}
