package com.lky.executor;

import java.util.concurrent.*;


/**
 * ScheduledThreadPoolExecutor用于延迟一段时间后再执行,如果有一个线程占用了这个工作线程的话,设置的延时执行会等到这个工作线程执行结束后再执行
 */
public class ScheduledThreadPoolExecutorDemo {

    public static final ScheduledThreadPoolExecutor scheduledThreadPoolExecutor = new ScheduledThreadPoolExecutor(1);

    public static void main(String[] args) {
        System.out.println(System.currentTimeMillis());
        ScheduledFuture<?> schedule = scheduledThreadPoolExecutor.schedule(() -> {
                System.out.println("我执行了");
                System.out.println(System.currentTimeMillis());
                return "aaa";
        }, 10L, TimeUnit.SECONDS);

        scheduledThreadPoolExecutor.execute(() -> {
                try {
                    Thread.sleep(30000);
                    System.out.println("我睡醒了");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    // Restore interrupted state...      
                    Thread.currentThread().interrupt();
                }
        });
        try {
            Object o = schedule.get();
            System.out.println(o);
            System.out.println(System.currentTimeMillis());
        } catch (InterruptedException e) {
            e.printStackTrace();
            // Restore interrupted state...      
            Thread.currentThread().interrupt();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    /**
     * 周期性执行
     * 神奇的一点:看起来主线程跑完了,可是周期行的线程还在跑,没有被结束
     * @param args
     */
    public static void main1(String[] args) {
        scheduledThreadPoolExecutor.scheduleWithFixedDelay(() -> {
                System.out.println("我执行了--"+System.currentTimeMillis());
        },5L,10L,TimeUnit.SECONDS);

        System.out.println("main结束了");
    }
}
