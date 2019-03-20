package com.qsmx.zww.demo;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

import static com.qsmx.zww.demo.QQServer.sendAndReseve;


/**
 * Created by zww on 2019-03-19.利用网络编程写个简单的qq
 */
public class QQClient {


    public static void main(String[] args) {
        try {
            Socket socketClient = new Socket("localhost", 8081);//连接qq服务端
            OutputStream outputStream = socketClient.getOutputStream();
            InputStream inputStream = socketClient.getInputStream();
            byte[] bytes1 = new byte[1024];
            Scanner sc = new Scanner(System.in);
            while (sc.hasNext()) {
                byte[] b = sc.next().getBytes("UTF-8");
                outputStream.write(b);
            }
            // sendAndReseve(inputStream, bytes1, sc, outputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
