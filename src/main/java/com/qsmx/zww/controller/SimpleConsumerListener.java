package com.qsmx.zww.controller;


import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.concurrent.CountDownLatch;

/**
 * Created by zww on 2019-03-21.kafka的监听类
 */
@Component
public class SimpleConsumerListener {
    private final CountDownLatch latch1 = new CountDownLatch(1);


    @KafkaListener(id = "test-consumer-group", topics = {"test"})
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


}
