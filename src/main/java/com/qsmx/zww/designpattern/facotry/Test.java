package com.qsmx.zww.designpattern.facotry;

/**
 * Created by zww on 2019-04-08.工厂模式测试类
 */
public class Test {

    public static void main(String[] args) {
        Phone huaw = PhoneFactory.createPhone("华为");
        huaw.userIphone();
        huaw.setColour("blue");
        Phone bbkPhone1 = PhoneFactory.createPhone("苹果");
        bbkPhone1.userIphone();
        System.out.println(bbkPhone1.getColour());
        System.out.println(huaw.getColour());
        Wow wow = new Wow();
        wow.method();
        wow.haha();
        wow.run();
    }
}
