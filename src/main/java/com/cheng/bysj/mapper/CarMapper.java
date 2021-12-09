package com.cheng.bysj.mapper;

import com.cheng.bysj.pojo.Car;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CarMapper {

    List<Car> queryAllCar();

    Car queryCarById(Integer id);
}
