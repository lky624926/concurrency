package com.lky.ForkJoin;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveAction;

public class MyRecursiveAction extends RecursiveAction {

    private Integer left;
    private Integer right;

    public MyRecursiveAction(Integer left,Integer right){
        this.left = left;
        this.right = right;
    }

    @Override
    protected void compute() {
        System.out.println(Thread.currentThread().getName());
        if (right-left>10){
            int i = (right - left)/2;
            MyRecursiveAction leftAction = new MyRecursiveAction(left, left + i);
            MyRecursiveAction rightAction = new MyRecursiveAction(left + i + 1, right);
            ForkJoinTask.invokeAll(leftAction,rightAction);
        }else {
         for (int i=left;i<right;i++){
             System.out.println(i);
             try {
                 Thread.sleep(100);
             } catch (InterruptedException e) {
                 e.printStackTrace();
             }
         }
        }
    }


    public static void main(String[] args) {

        ForkJoinPool forkJoinPool = new ForkJoinPool();
        MyRecursiveAction myRecursiveAction = new MyRecursiveAction(0, 100);
        //这种写法当前线程也会被占用,用于执行任务,所以不睡也可以正常执行,应该在任务线程中使用这种写法
        ForkJoinTask.invokeAll(myRecursiveAction);
        //这种写法主线程跑完后这个进程就停了,任务线程不会执行,所以需要加上睡眠
//        forkJoinPool.submit(myRecursiveAction);
//        try {
//            Thread.sleep(10000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        System.out.println("我执行了");
    }
}
