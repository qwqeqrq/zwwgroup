package com.qsmx.zww.mapper;

import com.qsmx.zww.po.BilibiliInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by zww on 2019-03-26.
 */
@Mapper
public interface BilibiliMapper {

    @Insert("insert into bilibili (title, author, url, picture_url,createDate) values(#{map.title},#{map.author},#{map.url},#{map.pictureUrl},now() )")
    Integer insertBilibili(@Param("map") Map map);

    @Select("select a.id, a.title, a.author, a.url, a.picture_url from bilibili a where a.deleted =0 and a.title = #{map.title} ")
    BilibiliInfo getBilibiliInfo(@Param("map") Map map);

}
