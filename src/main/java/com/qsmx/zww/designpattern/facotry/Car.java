package com.qsmx.zww.designpattern.facotry;

/**
 * Created by zww on 2019-04-08.
 */
public interface Car {

    void method();

    default String haha() {
        System.out.println("我不用被实现吗");
        return "我不用被实现";
    }


    default String run() {
        System.out.println("开车开车");
        return "开车开车";
    }
}
