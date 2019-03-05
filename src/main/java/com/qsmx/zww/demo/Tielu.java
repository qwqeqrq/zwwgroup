package com.qsmx.zww.demo;

/**
 * Created by zww on 2019-01-30.
 * 破解中国铁路12306官方网站接口返回票的信息
 * 读取其中的意思
 * 拿到车次 座位种类 等相关数据
 */


public class Tielu {
    public static void main(String[] args) throws Exception {
        String ss = "预订|24000000T714|T7|BXP|CDW|BXP|XAY|16:40|06:21|13:41|Y|SeADnUelHBvuzsuESqvexis5B4KBLQvOkuuDv89Rfnxoh2bwSM9iAKVGNjo%3D|20190219|3|\n" +
                "P3|01|07|0|0||||3|||有||有|有|||||10401030|1413|0|0|null";
        String[] strings = new String[40];
        //将字符串中的||之间的数据提取到字符串数组当中
        int index = ss.indexOf("|") + 1;//拿到字符在字符串中的位置
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(ss.substring(index));
        int i = 0;
        while (stringBuffer.indexOf("|") != -1) {
            int index1 = stringBuffer.indexOf("|");
            String resustr = stringBuffer.substring(0, index1);
            String shengxi = stringBuffer.substring(index1 + 1, stringBuffer.length());
            strings[i] = resustr;
            stringBuffer.delete(0, stringBuffer.length());//清空容器
            stringBuffer.append(shengxi);
            i++;
        }
        //首先获取到|的对应位置
        for (int j = 0; j < strings.length; j++) {
            System.out.println(j + "----" + strings[j]);
        }
        System.out.println("车次---" + strings[1]);
        System.out.println("高级软---" + strings[19]);
        System.out.println("硬卧--- " + strings[26]);
        System.out.println("硬座---" + strings[27]);
        System.out.println("软卧---" + strings[21]);
        System.out.println("无座---" + strings[24]);
    }
/*
        demoA();
        demoB();*/


    public static void demoA() {
        Thread duoXianChengA = new Thread(new DuoXianChengA("A"));//线程A1
        duoXianChengA.start();
    }

    public static void demoB() {
        Thread duoXianChengB = new Thread(new DuoXianChengB());//线程B
        duoXianChengB.start();

    }


}
