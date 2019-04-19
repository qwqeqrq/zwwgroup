package com.qsmx.zww.designpattern.observer;


/**
 * Created by zww on 2019-04-19. 实现观察者韩
 */
public class HanXin implements Observer {
    @Override
    public String obser(String message) {
        System.out.println("我是韩信:"+message);return null;
    }
}
