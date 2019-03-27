package com.qsmx.zww.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * Created by zww on 2019-03-26.
 */
@Mapper
public interface CarMapper {

    @Insert("insert into car (name ,logo,bfirstletter,createDate) values (#{map.name},#{map.logo},#{map.bfirstletter},now() )")
    Integer insertCar(@Param("map") Map map);

    @Insert("insert into university (school_id ,name,level_name,type_name,dual_class_name,county_name,province_name,city_name,is_top,f211,f985,special,createDate) " +
            "values (#{map.school_id},#{map.name},#{map.level_name},#{map.type_name},#{map.dual_class_name},#{map.county_name},#{map.province_name},#{map.city_name}" +
            ",#{map.is_top},#{map.f211},#{map.f985},#{map.sss},now() )")
    Integer insertUniversity(@Param("map") Map map);
}
