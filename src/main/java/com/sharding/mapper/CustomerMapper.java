package com.sharding.mapper;

import com.sharding.entity.Customer;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface CustomerMapper {
    @Insert("insert into customer(id,name) values(#{id},#{name})")
    public void insertCustomer(Customer customer);
}
