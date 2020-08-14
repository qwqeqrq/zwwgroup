package com.qsmx.zww.aqs;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * 自己实现并发测试程序
 */


public class ConcurrentTest {

    private final static int requestNum = 10000;  //请求总数

    private static List<String> arrayList = new ArrayList<>(); //线程不安全
    private static CopyOnWriteArrayList<String> copyOnWriteArrayList = new CopyOnWriteArrayList<>(); //线程安全

    private final static CountDownLatch countDownLatch = new CountDownLatch(requestNum); //闭锁 用来控制线程的阻塞

    private final static Semaphore semaphore = new Semaphore(10);//同时允许的并发请求数

    private final static ExecutorService executor = Executors.newCachedThreadPool();//创建线程池 启动线程使用

    private static void run() throws InterruptedException {

        for (int i = 0; i < requestNum; i++) {  //异步启动2000个线程进行调用test方法

            executor.execute(() -> {
                try {
                    semaphore.acquire();//获取执行许可
                    test();
                    test2();
                    semaphore.release();//释放许可
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    countDownLatch.countDown();//闭锁减1
                }
            });

        }
        executor.shutdown();
        countDownLatch.await();
        System.out.println("全部执行完成了:" + arrayList.size());
        System.out.println("全部执行完成了2:" + copyOnWriteArrayList.size());

    }

    private synchronized static void test() {

        try {
            arrayList.add("1");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static void test2() {

        try {
            copyOnWriteArrayList.add("1");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public static void main(String[] args) throws Exception {
        run();
    }

}
