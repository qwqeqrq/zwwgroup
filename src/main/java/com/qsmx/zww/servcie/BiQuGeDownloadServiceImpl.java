package com.qsmx.zww.servcie;

import com.qsmx.zww.uitil.HttpClient;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class BiQuGeDownloadServiceImpl implements BiQuGeDownloadService {

    @Override
    public String downLoad() {
        //url
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
                        || document.getElementsByTag("h1").text().contains("大结局")) {
                    System.out.println("===尾声==");
                    break;
                }
            }
            insertTxt(stringBuffer, "遮天");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String insertTxt(StringBuffer stringBuffer, String bookName) {
        try {
            File file = new File("C:\\Users\\zhangww1\\Desktop\\" + bookName + ".txt");
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

    public String downGBK() {
        /**
         * 专门爬取GBK 页面上的书籍  先来一套笑傲江湖
         *   http://ds.eywedu.com 在线读书网
         */
        try {
            StringBuffer stringBuffer = new StringBuffer();
            RestTemplate restTemplate = new RestTemplate();
            List<HttpMessageConverter<?>> httpMessageConverters = new ArrayList<>();
            httpMessageConverters.add(new StringHttpMessageConverter(Charset.forName("GBK")));
            restTemplate.setMessageConverters(httpMessageConverters);
            String s;
            for (int i = 1; i < 42; i++) { //循环每一章节
                if (i > 9) {
                    s = restTemplate.getForEntity("http://ds.eywedu.com/jinyong/xajh/mydoc0" + i + ".htm", String.class, new HashMap<>()).getBody();
                } else {
                    s = restTemplate.getForEntity("http://ds.eywedu.com/jinyong/xajh/mydoc00" + i + ".htm", String.class, new HashMap<>()).getBody();
                }
                Elements elements = Jsoup.parse(s).getElementsByTag("td");
                System.out.println(elements.get(10).text());//章节名
                stringBuffer.append("\r\r");
                stringBuffer.append(elements.get(10).text());
                stringBuffer.append("\r");
                stringBuffer.append("\r       ");
                stringBuffer.append(elements.get(11).text());//正文
            }
            insertTxt(stringBuffer, "笑傲江湖");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "完成";

    }
}
