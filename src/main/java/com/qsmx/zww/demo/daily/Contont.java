package com.qsmx.zww.demo.daily;

/**
 * Created by zww on 2019-01-30.//感觉自己需要写个单利设计模式,饿汉式
 */
public class Contont {


    private static int count = 500;//火车票张数

    private static Contont contont = new Contont();//私有一个自己实例的属性

    //首先构造函数私有化
    private Contont() {
    }

    //提供一个公共的对外的方法
    public static Contont getContont() {
        return contont;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    //选择
    public static void main(String[] args) {

        int[] arr = {1, 31, 9, 7, 5, 4, 6, 3};
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = i+1; j <arr.length ; j++) {
                if (arr[i]>arr[j]){
                    int z ;
                    z=arr[j];
                    arr[j]=arr[i];
                    arr[i]=z;
                }

            }

        }

        for (int i = 0; i < arr.length; i++) {
            System.out.print(" " + arr[i]);
        }

    }
}
