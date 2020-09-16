package com.lky.executor;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 线程池执行过程,先看核心线程数够不够,不够就直接创建新线程,放入核心线程中,核心线程满了就往工作队列中放,工作队列也满了就看看有没有到达最大线程数,没有就创建新线程执行,到达了就抛出异常,拒绝执行
 * shutdown:停止接受任务,等池中所有的任务都执行完,全部停止
 * shutdownNow: 立即停止,停止接受任务,工作队列中的任务都清除,正在执行的线程调用中断接口,将可以中断的线程都中断
 * 问题: 线程池中的线程都是旧的,没有更换过,为什么放到threadlocal中的对象会取不出来???
 */
public class ThreadPoolExecutorDemo {

    public static void main(String[] args) {
        ThreadLocal<Object> threadLocal = new ThreadLocal<>();
        threadLocal.set("abc");
        System.out.println("main:"+Thread.currentThread().getName()+Thread.currentThread().getId());
        ThreadLocal<Object> threadLocal1 = new ThreadLocal<>();
        Object o = threadLocal1.get();
        System.out.println("-----------------"+o);
        System.out.println("main:"+Thread.currentThread().getName()+Thread.currentThread().getId());
//        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(5, 20, 100, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(1000), new ThreadFactory() {
//            private AtomicInteger atomicInteger = new AtomicInteger(0);
//            @Override
//            public Thread newThread(Runnable r) {
//                System.out.println("new thread");
//                return new Thread(r,"thread-"+atomicInteger.getAndIncrement());
//            }
//        });
//        for (int i=0;i<20;i++){
//            threadPoolExecutor.execute(new Runnable() {
//                @Override
//                public void run() {
//
//                    try {
//                        Thread.sleep(1000);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                    System.out.println("执行了"+Thread.currentThread().getId()+"--"+Thread.currentThread().getName());
////                    System.out.println(Thread.currentThread().getName()+ "---------" + new ThreadLocal().get());
////                    ThreadLocal<Object> threadLocal = new ThreadLocal<>();
////                    threadLocal.set("aaa");
//
//                }
//            });
//        }
//        threadPoolExecutor.shutdown();
//        System.out.println("shutdown");
////        try {
////            Thread.sleep(9999999);
////        } catch (InterruptedException e) {
////            e.printStackTrace();
////        }

    }
}
