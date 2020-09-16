package com.lky.executor;

import java.util.concurrent.*;

/**
 * Future用于获取另一个线程的返回值,总的实现思路是用FutureTask包装一下传过来的runable或callable对象,线程池执行的是FutureTask中的run方法,在run方法中调用call方法并将call的返回值封装到FutureTask中,所以就可以从这个对象中get到另一个线程的返回值了
 */
public class Future {

    public static void main(String[] args) {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(5,10,5, TimeUnit.SECONDS,new LinkedBlockingDeque<>(10));
        java.util.concurrent.Future<Object> future = threadPoolExecutor.submit(new Callable<Object>() {
            @Override
            public Object call() throws Exception {
                Thread.sleep(1000);
                return "aaa";
            }
        });

        try {
            Object o = future.get();
            System.out.println(o);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        try {
            Thread.sleep(100000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
