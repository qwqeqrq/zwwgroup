package com.qsmx.zww.servcie;

import com.qsmx.zww.mapper.PhoneMapper;
import com.qsmx.zww.po.PhoneInfo;
import com.qsmx.zww.uitil.HttpClient;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class PhoneServerImpl implements PhoneServer {

    @Autowired
    private PhoneMapper phoneMapper;

    @Override
    public String getPhone() {
        for (int i = 0; i < 221; i++) {
            System.out.println("========================第" + i + "圈======================");
            String url1;
            try {
                url1 = "http://product.yesky.com/mobilephone/list" + i + ".html#page";
                String htmlstr = HttpClient.doGet(url1);
                Document doc = Jsoup.parseBodyFragment(htmlstr);
                Elements elements = doc.getElementsByClass("list blue");
                elements.stream().forEach(element -> {
                    Elements aElements = element.getElementsByTag("a");
                    System.out.println("=========================");
                    Element element1 = aElements.get(7);//获取到点评的a标签 他在第7个
                    String url = element1.attr("href");//获取到点评的地址，准备依靠点评去爬取详情
                    try {
                        if (ThreadLocalRandom.current().nextInt(3) < 1)//1/3的概率睡眠1秒
                            Thread.sleep(1000);
                        String htmlstr2 = HttpClient.doGet(url); //==============================访问点评地址
                        Document doc2 = Jsoup.parseBodyFragment(htmlstr2);
                        Map<String, String> map = new HashMap<>();
                        System.out.println(doc2.getElementsByTag("font").text());//分数
                        map.put("score", doc2.getElementsByTag("font").text());//装填分数
                        Elements elements2 = doc2.getElementsByClass("detilnr");
                        elements2.forEach(e2 -> {
                                    Elements aElements2 = e2.getElementsByTag("a");
                                    System.out.println(aElements2.get(1).text());
                                    map.put("brand_model", aElements2.get(1).text());//装填 品牌 型号
                                    Elements ddElements2 = e2.getElementsByTag("span");//
                                    System.out.println(ddElements2.text());
                                    map.put("price", ddElements2.text());//装填指导价
                                    Elements lements3 = e2.getElementsByClass("detijs");//基本信息
                                    lements3.forEach(e3 -> {
                                        Elements elements1 = e3.getElementsByTag("li");
                                        elements1.forEach(e4 -> {
                                                    String ss = e4.text();
                                                    String[] ssArray = ss.split("：");
                                                    System.out.println(ssArray[0]);//key
                                                    System.out.println(ssArray[1]);//value
                                                    //进行转换key
                                                    switch (ssArray[0]) {
                                                        case "屏幕尺寸":
                                                            map.put("screen_size", ssArray[1]);
                                                            break;
                                                        case "分辨率":
                                                            map.put("resolving_power", ssArray[1]);
                                                            break;
                                                        case "CPU型号":
                                                            map.put("cpu", ssArray[1]);
                                                            break;
                                                        case "4G制式":
                                                            map.put("fourg", ssArray[1]);
                                                            break;
                                                        case "处理器核心":
                                                            map.put("cpu_core", ssArray[1]);
                                                            break;
                                                        case "操作系统版本":
                                                            map.put("os", ssArray[1]);
                                                            break;
                                                        case "RAM容量":
                                                            map.put("ram", ssArray[1]);
                                                            break;
                                                        case "ROM容量":
                                                            map.put("rom", ssArray[1]);
                                                            break;
                                                        case "电池容量(mAh)":
                                                            map.put("battery", ssArray[1]);
                                                        case "后置摄像头":
                                                            map.put("rear_camera", ssArray[1]);
                                                            break;
                                                        case "前置摄像头":
                                                            map.put("front_camera", ssArray[1]);
                                                            break;
                                                        case "识别方式":
                                                            map.put("lock_type", ssArray[1]);
                                                            break;
                                                    }
                                                }
                                        );

                                    });
                                }
                        );
                        //插入
                        PhoneInfo phoneInfo = phoneMapper.select(map);
                        if (phoneInfo == null) {
                            System.out.println("已入库");
                            phoneMapper.insertPhone(map);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        System.out.println("进入点评页面失败");

                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return "success";
    }
}
