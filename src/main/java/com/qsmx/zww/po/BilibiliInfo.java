package com.qsmx.zww.po;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by zww on 2019-03-26. 哔哩哔哩
 */
public class BilibiliInfo implements Serializable {
    //id主键
    private  Long id;

    //标题
    private  String title;

    //投稿作者
    private  String author;

    //视频链接
    private  String url;

    //图片url
    private  String pictureUrl;

    //创建时间
    private Date createDate;

    //修改时间
    private  Date updateDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
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
