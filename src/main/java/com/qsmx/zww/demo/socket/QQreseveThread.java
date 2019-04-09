package com.qsmx.zww.demo.socket;

import java.io.InputStream;
import java.net.Socket;


/**
 * Created by zww on 2019-03-20.qq消息接受 线程
 */
public class QQreseveThread implements Runnable {

    private InputStream inputStream;
    private Socket socket;


    //通过构造函数给多线程代码传参
    public QQreseveThread(InputStream inputStream, Socket socket) {
        this.inputStream = inputStream;
        this.socket = socket;
    }

    //多线程代码块,监听消息的到来
    @Override
    public void run() {
        try {
            while (true) {
                byte[] message = new byte[1024];
                inputStream.read(message);
                String user = "掠影地形男z:";
                System.out.println(user + new String(message, "UTF-8"));
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("多线程sendqq  run方法内部异常");
        }
    }
}
