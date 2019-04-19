package com.qsmx.zww.designpattern.observer;

/**
 * Created by zww on 2019-04-19.实现观察者杨
 */
public class YangGuo implements Observer {
    private String name = "杨过";
    @Override
    public String obser(String message) {
        System.out.println("我是"+name+"那里有新情况:"+message);
        return null;
    }
}
