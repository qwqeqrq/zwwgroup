package com.qsmx.zww.mapper;

import com.qsmx.zww.po.PhoneInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@Mapper
public interface PhoneMapper {
    @Insert(" INSERT INTO phone ( brand_model, price, screen_size, resolving_power, cpu, " +
            "fourg, cpu_core, os, ram, rom, battery, rear_camera, front_camera, score, " +
            "lock_type, createDate ) VALUES ( #{map.brand_model},#{map.price},#{map.screen_size}," +
            "#{map.resolving_power},#{map.cpu},#{map.fourg},#{map.cpu_core},#{map.os},#{map.ram},#{map.rom}," +
            "#{map.battery},#{map.rear_camera},#{map.front_camera},#{map.score},#{map.lock_type},now() );")
    Integer insertPhone(@Param("map") Map map);

    @Select("select * from phone where brand_model=#{map.brand_model}")
    PhoneInfo select(@Param("map") Map map);
}
