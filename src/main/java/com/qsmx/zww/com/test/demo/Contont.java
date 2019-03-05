package com.qsmx.zww.com.test.demo;

/**
 * Created by zww on 2019-01-30.//感觉自己需要写个单利设计模式,饿汉式
 */
public class Contont {


    private static int count = 500;//火车票张数

    private static Contont contont = new Contont();//私有一个自己实例的属性

    //首先构造函数私有化
    private Contont() {
    }

    //提供一个公共的对外的方法
    public  static Contont getContont() {
        return contont;
    }

    public int getCount() {
        return count;
    }

    public  void setCount(int count) {
        this.count = count;
    }
}
