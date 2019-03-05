package com.qsmx.zww;

/**
 * Created by zww on 2019-01-30.
 * 多线程联系
 * <p>
 * 要求：同一个类，线程A打印我是线程A
 * 要求：同一个类，线程B打印我是线程B
 */
public class DuoXianChengB implements Runnable {

    private static SellTicket sellTicket = new SellTicket();

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
            sellTicket.sellZ43Ticket();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
