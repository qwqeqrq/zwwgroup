package com.qsmx.zww.designpattern.danli;

/**
 * Created by zww on 2019-04-08. 测试单例  饿汉式 以及懒汉式
 */
public class DanLiTest {

    private static void name() {
        LanHan lanHan = LanHan.getLanHan();
        EHan eHan = EHan.geteHan();
        System.out.println(eHan.geteHanName());
        //System.out.println(lanHan.getLanHanName());
    }

    public static void main(String[] args) {
        // LanHan lanHan = LanHan.getLanHan();
        EHan eHan = EHan.geteHan();
        eHan.seteHanName("我是恶汉1111");
        // lanHan.setLanHanName("我是不是单例呢");
        name();
        eHan.seteHanName("我改名字了 恶汉2222");
        // lanHan.setLanHanName("我赵日天改名字了");
        name();
    }
}
