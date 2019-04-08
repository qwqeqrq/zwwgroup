package com.qsmx.zww.designpattern.template;

/**
 * Created by zww on 2019-04-08. //使用模板方法模式，进行反向控制
 */
public class TemImpl1 extends Template {
    //重写钩子方法的值进行反向控制
    @Override
    public void setName(String name) {
        super.setName(name);
    }

    //个性化的东西进行重写
    @Override
    public void geXing() {
        System.out.println("我是个性化的马屁飞！！！！我就是本类的个性方法！");
    }

    public static void main(String[] args) {
        TemImpl1 temImpl1 = new TemImpl1();
        Template2 template2 = new Template2();
        template2.geXing();
        template2.run();

    }
}

class Template2 extends Template {

    //重写个性方法
    @Override
    public void geXing() {
        System.out.println("我是个性主播余小c");
    }
}
