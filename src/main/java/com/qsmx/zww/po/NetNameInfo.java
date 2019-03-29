package com.qsmx.zww.po;

import java.io.Serializable;

/**
 * Created by zww on 2019-03-25.网名
 */
public class NetNameInfo implements Serializable {
    private Integer id;
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
