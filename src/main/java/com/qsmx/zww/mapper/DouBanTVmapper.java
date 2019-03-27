package com.qsmx.zww.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.Map;

/**
 * Created by zww on 2019-03-26.
 */
@Mapper
public interface DouBanTVmapper {


    @Insert("insert into  doubantv ( title, rate, url, cover,createDate) values (#{map.title},#{map.rate},#{map.url},#{map.cover},now() )")
    Integer insertDoubanTV(@Param("map") Map map);


    @Select("select id id, title title, rate rate, url url, cover cover from doubantv where title=#{map.title} and deleted=0 ")
    Map selectDouBanTV(@Param("map") Map map);
}
