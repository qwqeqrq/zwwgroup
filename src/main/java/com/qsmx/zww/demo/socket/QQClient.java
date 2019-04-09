package com.qsmx.zww.demo.socket;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;


/**
 * Created by zww on 2019-03-19.利用网络编程写个简单的qq
 */
public class QQClient {


    public static void main(String[] args) {
        try {
            Socket socketClient = new Socket("localhost", 8081);//连接qq服务端
            OutputStream outputStream = socketClient.getOutputStream();
            InputStream inputStream = socketClient.getInputStream();
            Scanner sc = new Scanner(System.in);
            //创建发送线程和接受线程
            Thread sendThread = new Thread(new QQsendThread(sc, outputStream));
            Thread resciveThread = new Thread(new QQreseveThread(inputStream, socketClient));
            //启动线程
            resciveThread.start();
            sendThread.start();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
