package com.lky.executor;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class ThreadPoolExecutorDemo {

    public static void main(String[] args) {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(5, 20, 100, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(10), new ThreadFactory() {
            private AtomicInteger atomicInteger = new AtomicInteger(0);
            @Override
            public Thread newThread(Runnable r) {
                return new Thread("thread-"+atomicInteger.getAndIncrement());
            }
        });
        for (int i=0;i<10;i++){
            threadPoolExecutor.execute(new Runnable() {
                @Override
                public void run() {
//                    try {
//                        Thread.sleep(100);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
                    System.out.println("执行了"+Thread.currentThread().getId()+"--"+Thread.currentThread().getName());
                }
            });
        }

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//        threadPoolExecutor.shutdown();
        try {
            Thread.sleep(9999999);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
