package com.qsmx.zww.designpattern.facotry;

/**
 * Created by zww on 2019-04-08.
 */
public abstract class Phone {

    private String brand;//品牌

    private String colour = "red";//颜色

    public String userIphone() {
        return brand;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }
}
