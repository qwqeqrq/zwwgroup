package com.qsmx.zww.designpattern.facotry;

/**
 * Created by zww on 2019-04-08.
 */
public class HuaWeiPhone extends Phone {
    @Override
    public String userIphone() {
        System.out.println("欢迎使用华为P30手机");
        return super.userIphone();
    }
}
