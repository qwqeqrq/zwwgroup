package com.qsmx.zww.designpattern.danli;


/**
 *    单例
 *   懒汉式 双重检查  volatile 的经典用法
 */


public class Shuangchongjiancha {


    private static volatile Shuangchongjiancha signl = null;

    private Shuangchongjiancha() {
    }

    ;

    public static Shuangchongjiancha getsignlBean() {

        if (signl == null) {
            synchronized (Shuangchongjiancha.class) {
                if (signl == null) {
                    return new Shuangchongjiancha();
                }
            }
        }
        return signl;
    }
}
