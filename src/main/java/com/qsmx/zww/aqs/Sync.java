package com.qsmx.zww.aqs;

import java.util.ArrayDeque;
import java.util.NoSuchElementException;
import java.util.concurrent.*;

public class Sync {


    /**
     * 实现一个阻塞队列   队列为空,阻塞get线程  队列为满的时候，入队阻塞
     */
    private final static ArrayDeque<String> queue = new ArrayDeque();

    private final static ExecutorService excutor = Executors.newCachedThreadPool();

    private final static CyclicBarrier cyclicBarrier = new CyclicBarrier(2);


    //入队列
    private static void put(String s) throws InterruptedException, BrokenBarrierException {
        if (queue.size() < 10) {
            System.out.println("入队列:" + s);
            if (queue.size() == 0) {
                queue.add(s);
                System.out.println("put方法唤醒get阻塞线程");
                cyclicBarrier.await();
            } else {
                queue.add(s);
                System.out.println("put方法唤醒get阻塞线程");
                cyclicBarrier.await();
            }
        }
        if (queue.size() == 10) {
            //阻塞队列为满，阻塞该线程
            System.out.println("阻塞队列为满，阻塞该put线程");
            cyclicBarrier.await();
            cyclicBarrier.reset();
        }
    }

    //出队列
    private static String get() throws InterruptedException, BrokenBarrierException {
        try {
            if (queue.size() <= 10) {
                //取出并唤醒阻塞线程
                String s = queue.pop();
                cyclicBarrier.await();
                System.out.println("get方法唤醒put阻塞线程");
                return s;
            } else {
                return queue.pop();
            }

        } catch (NoSuchElementException n) {
            //就应该阻塞该线程
            System.out.println("阻塞队列为空,阻塞当前get线程");
            cyclicBarrier.await();
            cyclicBarrier.reset();
            return queue.pop();
        }
    }


    public static void main(String[] args) throws Exception {

        excutor.execute(() -> {
            for (int i = 0; i < 50; i++) {
                try {
                    Thread.sleep(2000);
                    Sync.put("伞兵" + i + "号卢本伟准备就绪!");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }
        });
        //测试队列为空  阻塞情况
        for (int i = 0; i < 50; i++) {
            Thread.sleep(1000);
            System.out.println("=========================阻塞队列的值为："+Sync.get());
        }

        //测试队列不为空 阻塞
       /* excutor.execute(() -> {
            try {
                for (int i = 0; i <4 ; i++) {
                    Thread.sleep(10000);
                    System.out.println("取出值：" + Sync.get());
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        });

        for (int i = 0; i < 20; i++) {
            Thread.sleep(100);
            Sync.put("卢本伟牛逼" + i);
        }*/

    }
}
