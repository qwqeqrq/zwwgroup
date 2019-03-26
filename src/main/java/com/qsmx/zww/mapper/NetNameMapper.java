package com.qsmx.zww.mapper;

import com.qsmx.zww.po.NetNameInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.Map;

/**
 * Created by zww on 2019-03-25.
 */
@Mapper
public interface NetNameMapper {
    @Insert("insert into netname (name) values (#{netNameInfo.name})")
    Integer insertName(@Param("netNameInfo") NetNameInfo netNameInfo);

    @Select(" select id id ,name name from netname where name =#{netNameInfo.name}")
    Map<String, Object> selectNetName(@Param("netNameInfo") NetNameInfo netNameInfo);
}
