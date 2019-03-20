package com.qsmx.zww.demo;


import java.io.OutputStream;
import java.util.Scanner;


/**
 * Created by zww on 2019-03-20.qq发送线程类
 */
public class QQsendThread implements Runnable {

    private Scanner sc;
    private OutputStream outputStream;

    //通过构造函数给多线程代码传参
    public QQsendThread(Scanner sc, OutputStream outputStream) {
        this.sc = sc;
        this.outputStream = outputStream;
    }

    //多线程代码块 监听键盘的输入，有输入就发送

    @Override
    public void run() {
        try {
            while (sc.hasNext()) {
                byte[] message = sc.next().getBytes("UTF-8");
                outputStream.write(message);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("多线程sendqq  run方法内部异常");
        }
    }
}
