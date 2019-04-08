package com.qsmx.zww.designpattern.facotry;

/**
 * Created by zww on 2019-04-08.
 */
public class PhoneFactory extends Factory {

    public static Phone createPhone(String phone) {
        switch (phone) {
            case "步步高":
                return new BbkPhone();
            case "苹果":
                return new Iphone();
            case "华为":
                return new HuaWeiPhone();

        }
        return null;
    }

    @Override
    public String phoneTopic() {
        return super.phoneTopic();
    }

    @Override
    public String userIphone() {
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
