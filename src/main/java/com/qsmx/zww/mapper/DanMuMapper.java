package com.qsmx.zww.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@Mapper
public interface DanMuMapper {

    @Insert("insert into douyudanmu (title, name, value, createDate) values(#{map.title},#{map.name},#{map.value},now() )")
    int insertDanMu(@Param("map") Map<String,Object> map);
}
