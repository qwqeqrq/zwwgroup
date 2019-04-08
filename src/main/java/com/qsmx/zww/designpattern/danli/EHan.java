package com.qsmx.zww.designpattern.danli;


/**
 * Created by zww on 2019-04-08. 单例模式  饿汉式
 * <p>
 * <p>
 * 饿汉式  名字可以看出饿 就是一上来就开始创建对象，其他的符合单例模式原理
 */
public class EHan {

    private String eHanName;

    private static EHan eHan = new EHan();

    //构造函数私有化
    private EHan() {
    }

    //提供一个公共的方法，对外提供实例
    public static EHan geteHan() {
        if (eHan == null) {
            eHan = new EHan();
        }
        return eHan;
    }

    public String geteHanName() {
        return eHanName;
    }

    public void seteHanName(String eHanName) {
        this.eHanName = eHanName;
    }
}
