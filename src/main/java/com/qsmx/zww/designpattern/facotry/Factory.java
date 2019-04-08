package com.qsmx.zww.designpattern.facotry;

/**
 * Created by zww on 2019-04-08. 抽象工厂 设计模式
 * 工厂模式   其实就是大量的利用java中的抽象类 进行继承扩展
 */
public abstract class Factory extends Phone{

    public String phoneTopic(){
        System.out.println("我是抽象工厂");
        return "我是抽象工厂";
    }
}
