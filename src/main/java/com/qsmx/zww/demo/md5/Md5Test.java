package com.qsmx.zww.demo.md5;


import com.qsmx.zww.uitil.Md5Util;


/**
 * Created by zww on 2019-04-09.
 */
public class Md5Test {

    public static void main(String[] args) throws Exception {
        //MD5Encoder.encode();数字类型
        System.out.println(Md5Util.encryption("123456"));
    }
}



