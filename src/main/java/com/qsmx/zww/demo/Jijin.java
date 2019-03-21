package com.qsmx.zww.demo;

import java.math.BigDecimal;

/**
 * Created by zww on 2019-02-20.计算基金净值类产品的收益
 */
public class Jijin {

    public static void main(String[] args) {
        System.out.println("----------------------------------------------");
        System.out.println("累计收益："+jisuan(new BigDecimal(1.0365)));//传入当日净值
        System.out.println("----------------------------------------------");

        System.out.println("----------------------------------------------");
        System.out.println("当年分红："+fenhong(new BigDecimal(1.0374)));//传入当日净值
        System.out.println("----------------------------------------------");
    }

    static BigDecimal jisuan(BigDecimal jinzhi) {//计算每日收益，并保留两位小数
        return (jinzhi.subtract(new BigDecimal(1.0371))).multiply(new BigDecimal(8678.04)).setScale(2,BigDecimal.ROUND_UP);//计算与初始精致的值
    }
    static BigDecimal fenhong(BigDecimal meifen){
        return (meifen.multiply(new BigDecimal(8678.04)).setScale(2,BigDecimal.ROUND_UP));
    }
}
