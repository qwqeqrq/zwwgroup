package com.qsmx.zww.controller;


import com.qsmx.zww.mapper.DanMuMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping(value = "/danmu")
public class DouyudanmuController {

    @Autowired
    private  DanMuMapper danMuMapper;

    public static final String HOST = "124.95.155.51";
    public static final int DATA_HEAD_LEN = 4 + 4 + 1;
    public static final int CODE = 689;
    public static final SimpleDateFormat DF = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public static final String INVALID_MSG = "-1";

    @RequestMapping(value = "/go")
    public String getDanmu() {
        try {
            Thread t1 = new Thread(new CrawlerThread(71017));
            //Thread t2 = new Thread(new CrawlerThread(60937));
            Thread t3 = new Thread(new AliveThread());
            t1.start();
            // t2.start();
            t3.start();

            while (Thread.activeCount() > 1) {
                Thread.yield();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "success";
    }

    public static void sendRequest(Socket client, String msg) {
        try {
            int msgLength = 4 + 4 + msg.length() + 1;
            byte[] dataLength = intToBytes(msgLength);
            byte[] dataHead = intToBytes(CODE);
            byte[] data = msg.getBytes(StandardCharsets.ISO_8859_1);

            ByteArrayOutputStream byteArray = new ByteArrayOutputStream();

            byteArray.write(dataLength);
            byteArray.write(dataLength);
            byteArray.write(dataHead);
            byteArray.write(data);
            byteArray.write(0);

            OutputStream out = client.getOutputStream();
            out.write(byteArray.toByteArray());
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 以小端模式将int转成byte[]
     *
     * @param data int形式的数据
     * @return 字节形式的数据
     */
    private static byte[] intToBytes(int data) {
        byte[] src = new byte[4];
        src[3] = (byte) ((data >> 24) & 0xFF);
        src[2] = (byte) ((data >> 16) & 0xFF);
        src[1] = (byte) ((data >> 8) & 0xFF);
        src[0] = (byte) (data & 0xFF);
        return src;
    }

    /**
     * 以小端模式将byte[]转成int
     *
     * @param src 字节形式的数组
     * @return int形式的数据
     */
    private static int bytesToInt(byte[] src) {
        return ((src[0] & 0xFF)
                | ((src[1] & 0xFF) << 8)
                | ((src[2] & 0xFF) << 16)
                | ((src[3] & 0xFF) << 24));
    }

    public static String receiveMsg(Socket client) {
        ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
        try {
            InputStream inputStream = client.getInputStream();

            int dataLength = get4bytesResponse(inputStream);
            int dataLength2 = get4bytesResponse(inputStream);
            int msgType = get4bytesResponse(inputStream);

            if (dataLength <= 8 || dataLength >= 1032) {
                return INVALID_MSG;
            }

            dataLength = dataLength - 8;

            int len;
            int readLen = 0;
            byte[] bytes = new byte[dataLength];
            while ((len = inputStream.read(bytes, 0, dataLength - readLen)) != -1) {
                byteArray.write(bytes, 0, len);
                readLen += len;
                if (readLen == dataLength) {
                    break;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return byteArray.toString();
    }

    private static int get4bytesResponse(InputStream inputStream) throws IOException {
        byte[] bytes = new byte[4];
        inputStream.read(bytes, 0, 4);
        return bytesToInt(bytes);
    }

    public static Map<String, String> getMsg(String s) {
        String[] messages = s.split("/");
        Map<String, String> m = new HashMap<>();
        for (String message : messages) {
            String[] ms = message.split("@=");
            if (ms.length >= 2) {
                m.put(ms[0], ms[1]);
            }
        }
        return m;
    }

    static class AliveThread implements Runnable {
        private Socket client;

        @Override
        public void run() {
            try {
                client = new Socket(HOST, 8601);
            } catch (IOException e) {
                e.printStackTrace();
            }
            String keepliveMsg = "type@=mrkl/";
            while (true) {
                sendRequest(client, keepliveMsg);
                //System.out.println(DF.format(new Date()) + " keep alive ***********************");
                try {
                    Thread.sleep(40000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }
    }

     class CrawlerThread implements Runnable {
        private Socket client;
        private int roomId;

        public CrawlerThread(int roomId) {
            this.roomId = roomId;
        }

        @Override
        public void run() {

            connectToDy();

            while (true) {
                String s = receiveMsg(client);
                if (s.equals(INVALID_MSG)) {
                    connectToDy();
                    continue;
                }

                Map<String, String> m = getMsg(s);
                String danMu = m.get("txt");
                if (danMu != null && !danMu.equals("")) {
                    String name = m.get("nn");
                    String cardName = m.get("bnn");
                    String cardLevel = m.get("bl");
                    String roomId = m.get("rid");
                    String level = m.get("level");
                    String time = DF.format(new Date());
                    HashMap hashMap = new HashMap();
                    hashMap.put("title", cardLevel + "级" + cardName);
                    hashMap.put("name", name);
                    hashMap.put("value", danMu);
                    danMuMapper.insertDanMu(hashMap);
                    System.out.println("Insert Success! " + time + " " + cardLevel + "级" + cardName + " [" + name + "] : " + danMu);
                }

                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }

        private void connectToDy() {
            try {
                if (client != null) {
                    client.close();
                }
                client = new Socket(HOST, 8601);
                if (client == null) {
                    System.out.println("连接服务器失败");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            String loginMsg = "type@=loginreq/roomid@=" + String.valueOf(roomId) + "/";
            sendRequest(client, loginMsg);

            String joinGroupMsg = "type@=joingroup/rid@=" + String.valueOf(roomId) + "/gid@=-9999/";
            sendRequest(client, joinGroupMsg);
        }

        private void logout() {
            String logoutMsg = "type@=logout/";
            sendRequest(client, logoutMsg);
        }
    }


   /* public static void main(String[] args) {

        //Thread t1 = new Thread(new CrawlerThread(71017));
        Thread t1 = new Thread(new CrawlerThread(71017));
        //Thread t2 = new Thread(new CrawlerThread(5587551));
        Thread t3 = new Thread(new AliveThread());
        t1.start();
        //t2.start();
        t3.start();

        while (Thread.activeCount() > 1) {
            Thread.yield();
        }
    }*/
}

