package com.sharding;

import com.sharding.entity.*;
import com.sharding.entity.common.*;
import com.sharding.mapper.CustomerMapper;
import com.sharding.mapper.DictOrderTypeMapper;
import com.sharding.mapper.OrdersMapper;
import com.sharding.mapper.PersonMapper;
import com.sharding.service.ExampleService;
import com.sharding.service.OrderServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.SQLException;
import java.util.Random;

@SpringBootTest
public class ShardingsphereDemoApplicationTests {

    @Autowired
    private OrdersMapper ordersMapper;
    @Test
    public void addOrders(){
        for (int i = 1; i <=10 ; i++) {
            Orders orders = new Orders();
            orders.setId(i);
            orders.setCustomerId(i);
            orders.setOrderType(i);
            orders.setAmount(1000.0*i);
            ordersMapper.insert(orders);
        }
    }
    @Test
    public void queryOrders(){
        Orders orders = ordersMapper.selectOne(1);
        System.out.println(orders);
    }


    //================水平分库===============

    @Test
    public void addOrdersDB(){
        for (int i = 1; i <=10 ; i++) {
            Orders orders = new Orders();
            orders.setId(i);
            orders.setCustomerId(new Random().nextInt(10));
            orders.setOrderType(i);
            orders.setAmount(1000.0*i);
            ordersMapper.insert(orders);
        }
    }
    @Test
    public void queryOrdersDB(){
        Orders orders = new Orders();
        orders.setCustomerId(7);
        orders.setId(7);
        Orders o = ordersMapper.selectOneDB(orders);
        System.out.println(o);
    }

    //=======================垂直分库

    @Autowired
    private CustomerMapper customerMapper;

    @Test
    public void insertCustomer(){
        for (int i = 1; i <= 10 ; i++) {
            Customer customer = new Customer();
            customer.setId(i);
            customer.setName("zs"+i);
            customerMapper.insertCustomer(customer);
        }
    }


    //======公共表
    @Autowired
    private DictOrderTypeMapper dictOrderTypeMapper;

    @Test
    public void insertDictOrderType(){
        for (int i = 1; i <= 10 ; i++) {
            DictOrderType dictOrderType = new DictOrderType();
            dictOrderType.setId(i);
            dictOrderType.setOrderType("orderType"+i);
            dictOrderTypeMapper.insertDictOrderType(dictOrderType);
        }
    }

    @Test
    public void deleteDictOrderType(){
        dictOrderTypeMapper.DeleteDictOrderType(1);
    }

  //-----读写分离

    @Autowired
    private PersonMapper personMapper;

    @Test
    public void insertPerson(){
        Person person = new Person();
        person.setId(1l);
        person.setName("zhangsan");
        personMapper.insertPerson(person);
    }

    @Test
    public void queryPerson(){
        Person person = personMapper.queryPerson(1l);
        System.out.println(person);
    }


    //sharding-jdbc强制路由

    @Autowired
    private ExampleService orderService;

    @Test
    public void initEnv(){
        try {
            orderService.initEnvironment();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
