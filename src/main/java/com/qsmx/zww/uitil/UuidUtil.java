package com.qsmx.zww.uitil;

import java.util.UUID;

/**
 * Created by zww on 2019-04-09.
 */
public class UuidUtil {

    //生成UUID
    public static String getUuid() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    //当前时间戳()
    public static String getOrderSn() {
        return String.valueOf(System.currentTimeMillis());
    }
}
