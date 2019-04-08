package com.qsmx.zww.designpattern.facotry;

/**
 * Created by zww on 2019-04-08.
 */
public class BbkPhone extends Phone {

    @Override
    public String userIphone() {
        System.out.println("我是BBK排字");
        System.out.println("步步高音乐手机，手机中的战斗机，欧耶！");
        return super.userIphone();
    }

    @Override
    public String getBrand() {
        return super.getBrand();
    }

    @Override
    public void setBrand(String brand) {
        super.setBrand(brand);
    }

    @Override
    public String getColour() {
        return super.getColour();
    }

    @Override
    public void setColour(String colour) {
        super.setColour(colour);
    }
}
