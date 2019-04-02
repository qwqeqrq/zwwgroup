package com.qsmx.zww.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * Created by zww on 2019-04-02.
 */
@Mapper
public interface WeatherWarningMapper {

    @Insert("insert into weather_warning (headline,description,createDate,sendTime) values (#{map.headline},#{map.description},now(),#{map.sendTime})")
    Integer insertWeatherWarning(@Param("map") Map map);

    @Select("select id id , headline headline ,description description ,createDate createDate ,sendTime sendTime from weather_warning where deleted=0 and headline=#{map.headline} ")
    List<Map> selectWeatherWarning(@Param("map") Map map);
}
