package com.qsmx.zww.designpattern.template;

/**
 * Created by zww on 2019-04-08. 模板方法设计模式
 */
public abstract class Template {

    private String colour;
    private String name = "钩子";

    private void doShing() {
        System.out.println("你可以通过钩子方法来对我进行控制");
    }

    public void run() {//子类的公用方法的方法
        if ("钩子".equals(this.name))
            doShing();
        else
            System.out.println("卢本伟牛逼！！！！");
    }

    public void geXing() { //子类的个性方法

    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public String getName() {
        return name;
    }

    //设置钩子的方法
    public void setName(String name) {
        this.name = name;
    }
}
