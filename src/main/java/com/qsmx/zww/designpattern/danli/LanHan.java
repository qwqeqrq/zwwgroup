package com.qsmx.zww.designpattern.danli;

/**
 * Created by zww on 2019-04-08.单例设计模式  懒汉式
 */
public class LanHan {
    //懒汉，之所以懒 就是第一时间不创建对象当被调用的时候才创建
    //单例  只有一个对象，不允许创建对象，因此 构造函数私有化
    private String lanHanName;//属性

    private static LanHan lanHan;

    private LanHan() {

    }
    //私有化了构造函数那么外界如何拿到这个实例呢，因此需要提供一个公开的静态方法获取实例的方法
    //此处存在竞态条件，所以势必会引起线程安全的问题来，必须进行同步管理
    public static synchronized LanHan getLanHan() {
        if (lanHan == null) {
            lanHan = new LanHan();
        }
        return lanHan;
    }

    public String getLanHanName() {
        return lanHanName;
    }

    public void setLanHanName(String lanHanName) {
        this.lanHanName = lanHanName;
    }

}
