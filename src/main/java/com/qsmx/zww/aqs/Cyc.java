package com.qsmx.zww.aqs;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * CyclicBarrier 实现一个线程控制 线程调用await（）方法后会进行阻塞 ，知道有n（parties）个方法都阻塞 之后开始放行 可以重新set值进行重置
 */
public class Cyc {

    private  final static CyclicBarrier cyclicBarrier = new CyclicBarrier(5);

    public static void main(String[] args) throws InterruptedException {

        ExecutorService executor = Executors.newCachedThreadPool(); //创建线程池
        for (int i = 0; i < 20; i++) {
            Thread.sleep(1000);
            executor.execute(()-> {
                try {
                    test();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }

    }

    private static void test () throws Exception{
        System.out.println(Thread.currentThread().getName()+"准备就绪cyc阻塞中");
        cyclicBarrier.await();
        System.out.println(Thread.currentThread().getName()+"开始执行");
        System.out.println();
    }
}
