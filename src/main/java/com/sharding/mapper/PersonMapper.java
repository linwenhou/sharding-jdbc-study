package com.sharding.mapper;

import com.sharding.entity.Person;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface PersonMapper {

    @Insert("insert into person(id,name) values(#{id},#{name})")
    public void insertPerson(Person person);

    @Select("select * from person where id = #{id}")
    public Person queryPerson(Long id);
}
