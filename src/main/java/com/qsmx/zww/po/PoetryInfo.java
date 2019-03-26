package com.qsmx.zww.po;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by zww on 2019-03-25.古诗
 */
public class PoetryInfo implements Serializable {
    //id主键
    private Integer id;

    //诗名
    private String name;

    //作者
    private String author;

    //诗句
    private String verse;

    //创建时间
    private Date createDate;

    //修改时间
    private Date updateDate;

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

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getVerse() {
        return verse;
    }

    public void setVerse(String verse) {
        this.verse = verse;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
}
