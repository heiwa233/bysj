package com.cheng.bysj.mapper;

import com.cheng.bysj.pojo.Car;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CarMapper {
    @Select("select * from car;")
    List<Car> queryAllCar();
}
