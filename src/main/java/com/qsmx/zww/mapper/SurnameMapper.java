package com.qsmx.zww.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;


import java.util.List;
import java.util.Map;

/**
 * Created by zww on 2019-03-07.
 */
@Mapper
public interface SurnameMapper {
    //查询姓氏测试
    @Select("SELECT id id, surname 姓 FROM `chinesesurname`")
    List<Map<String, Object>> getsurname();
}
