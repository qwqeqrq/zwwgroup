package com.qsmx.zww.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.Map;

/**
 * Created by zww on 2019-03-28. 智联招聘
 */
@Mapper
public interface ZhiLianJobMapper {

    @Insert("insert into job (jobName, display, welfare, salary, workingExp, company, company_name, type, eduLevel, deleted, createDate, endDate, updateDate) values" +
            "(#{map.jobName}, #{map.display}, #{map.welfare}, #{map.salary}, #{map.workingExp}, #{map.company}, #{map.name}, " +
            "#{map.type}, #{map.eduLevel}, #{map.deleted}, now(), #{map.endDate}, #{map.updateDate})")
    Integer insertJob(@Param("map") Map map);

    @Select("select count(id) from job where jobName=#{map.jobName} and company_name=#{map.name}")
    Integer selectJob(@Param("map") Map map);
}
