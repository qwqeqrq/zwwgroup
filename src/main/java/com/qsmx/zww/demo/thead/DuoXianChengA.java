package com.qsmx.zww.demo.thead;

import com.qsmx.zww.demo.tielu12306.SellTicket;

/**
 * Created by zww on 2019-01-30.
 * 卖票的线程类
 * <p>
 * 要求：同一个类，线程A打印我是线程A
 * 要求：同一个类，线程B打印我是线程B
 */
public class DuoXianChengA implements Runnable {

    private String thredName;

    private static SellTicket sellTicket = new SellTicket();

    public DuoXianChengA(String thredName) {//根据线程名初始化线程
        this.thredName = thredName;
    }

    public String getThredName() {
        return thredName;
    }

    /**
     * @描述： 多线程的开启首先我们实现Runnable接口 （可执行接口 ，复写run方法）
     * @参数：
     * @返回值：
     * @创建人： zhangww
     * @创建时间： 2019-01-30
     * @修改人和其它信息：
     */
    @Override
    public void run() { //run方法就是多个线程执行的内容吧
        try {
            sellTicket.sellZ19Ticket();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
