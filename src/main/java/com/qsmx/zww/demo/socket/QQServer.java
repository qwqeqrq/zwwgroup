package com.qsmx.zww.demo.socket;

import com.qsmx.zww.demo.socket.QQreseveThread;
import com.qsmx.zww.demo.socket.QQsendThread;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created by zww on 2019-03-19.利用网络编程写个简单的qq
 */
public class QQServer {

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(8081);//实例化一f个 socket 服务端对象 ，端口 8080
            Socket socket = serverSocket.accept();//一直等待客户端的连接 如果连接成功 返回客户端和服务端公用的交互对象
            //System.in表示标准化输出，也就是键盘输出
            Scanner sc = new Scanner(System.in);
            OutputStream outputStream = socket.getOutputStream();//  得到输出流
            InputStream inputStream = socket.getInputStream();//  得到输入流
            System.out.println("皮卡丘已上线连接成功");
            //创建发送线程和接受线程
            Thread sendThread = new Thread(new QQsendThread(sc, outputStream));
            Thread resciveThread = new Thread(new QQreseveThread(inputStream, socket));
            //启动线程
            resciveThread.start();
            sendThread.start();
            //todo 由于这个检查键盘方法会阻塞，导致不能及时收到，所以我决定使用两个线程去分别做这两件事
            //todo 一个线程专门监听键盘输入
            //todo 一个线程专门准备显示对方的文字
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    //接受消息，发送消息
    public static void sendAndReseve(InputStream inputStream, byte[] bytes, Scanner sc, OutputStream outputStream) throws Exception {
        while (true) {
            //打印客户端发来的输入流
            inputStream.read(bytes);
            System.out.println(new String(bytes, "UTF-8"));
            boolean s = sc.hasNext();
            while (s) {
                //检查输出文字发送
                byte[] bytes1 = sc.next().getBytes("UTF-8");
                outputStream.write(bytes1);
                s = false;
            }
        }
    }


    //获得键盘输入
    static String jianpan() {
        //创建Scanner对象
        //System.in表示标准化输出，也就是键盘输出
        Scanner sc = new Scanner(System.in);
        //利用hasNextXXX()判断是否还有下一输入项
        while (sc.hasNext()) {
            //利用nextXXX()方法输出内容
            String str = sc.next();
            System.out.println(str);
        }
        return "";
    }
}
