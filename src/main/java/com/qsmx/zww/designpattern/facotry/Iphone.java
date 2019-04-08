package com.qsmx.zww.designpattern.facotry;

/**
 * Created by zww on 2019-04-08.
 */
public class Iphone extends Phone {
    @Override
    public String userIphone() {
        System.out.println("欢迎使用苹果手机 +++");
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
