package com.qsmx.zww.servcie;

import com.qsmx.zww.uitil.HttpClient;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;


import java.io.*;

@Service
public class BiQuGeDownloadServiceImpl implements BiQuGeDownloadService {

    @Override
    public String downLoad() {
        //url
        int page = 0;
        StringBuffer stringBuffer = new StringBuffer();
        try {
            String url;
            for (int i = 3246381; i < 3248215; i++) {
                url = "http://www.xbiquge.la/7/7004/" + i + ".html";//一个章节 一个去获取
                String html = HttpClient.doGet(url); //得到页面
                Document document = Jsoup.parse(html);
                System.out.println(document.getElementsByTag("h1").text() + "=============" + i);
                stringBuffer.append("\r\n\n"); //章节前面加两个换行
                stringBuffer.append(document.getElementsByTag("h1").text());  //章节名
                stringBuffer.append("\r\n"); //章节后面加一个换行
                stringBuffer.append("\r     "); //章节后面加空格
                if (null == document.getElementById("content")) continue;//为空跳过
                String content = document.getElementById("content").text();
                content.replace("&nbsp;", "      ");//空格
                content.replace("<br />", "\r\t\n");
                stringBuffer.append(content);//内容
                if ("第一千八百二十二章 大结局".equals(document.getElementsByTag("h1").text())
                        ||document.getElementsByTag("h1").text().contains("大结局")) {
                    System.out.println("===尾声==");
                    break;
                }
            }
            insertTxt(stringBuffer);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String insertTxt(StringBuffer stringBuffer) {
        try {
            File file = new File("C:\\Users\\zhangww1\\Desktop\\遮天.txt");
            System.out.println("写入文件中");
            FileOutputStream out = new FileOutputStream(file, true);
            out.write(stringBuffer.toString().getBytes());
            out.close();
            System.out.println("写入完成");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("io异常");
        }
        return "";
    }
}
