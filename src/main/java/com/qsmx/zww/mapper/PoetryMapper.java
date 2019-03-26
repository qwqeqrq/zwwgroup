package com.qsmx.zww.mapper;

import com.qsmx.zww.po.PoetryInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * Created by zww on 2019-03-25.
 */
@Mapper
public interface PoetryMapper {
    @Insert("insert into poetry (name ,author,verse,createDate,updateDate) values (#{poetryInfo.name},#{poetryInfo.author},#{poetryInfo.verse},now(),now())")
    Integer insertPoetry(@Param("poetryInfo") PoetryInfo poetryInfo);

    @Select(" select name ,author,verse,createDate from poetry where name =#{poetryInfo.name}")
    PoetryInfo selectPoetryByName(@Param("poetryInfo") PoetryInfo poetryInfo);
}
