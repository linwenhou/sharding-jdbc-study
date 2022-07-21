package com.sharding.mapper;

import com.sharding.entity.common.DictOrderType;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface DictOrderTypeMapper {

    @Insert("insert into dict_order_type(id,order_type) values(#{id},#{orderType})")
    public void insertDictOrderType(DictOrderType dictOrderType);

    @Delete("delete from dict_order_type where id = #{id}")
    public void DeleteDictOrderType(Integer id);
}
