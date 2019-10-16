package com.qsmx.zww.controller;


import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.concurrent.CountDownLatch;

/**
 * Created by zww on 2019-03-21.kafka的监听类
 */
@Component
public class KafkaSimpleConsumerListener {
    private final CountDownLatch latch1 = new CountDownLatch(1);

    /**
     * 通过下面两个监听方法 测试出kafka的监听者如果属于同一个group.id
     *
     * 那么该group.id下面的多个监听者只会有一个能获取到消息  多个group.id就都能消费到
     * @param message
     */

    //@KafkaListener(id = "test-consumer-group", topics = {"demo"})
    public void listen(String message) {
        //do something here  停止容器
        System.out.println("--------------我正在消费------------");
        this.latch1.countDown();
        try {
            System.out.println("--------------消费者正在运行");
            System.out.println(message);
        } catch (Exception e) {
            System.out.println("listen异常");
        }
    }
    //@KafkaListener(topics = {"demo"})
    public void listen2(String message) {
        //do something here  停止容器
        System.out.println("--------------我正在消费------------");
        this.latch1.countDown();
        try {
            System.out.println("--------------消费者2正在运行");
            System.out.println(message);
        } catch (Exception e) {
            System.out.println("listen异常");
        }
    }

}
