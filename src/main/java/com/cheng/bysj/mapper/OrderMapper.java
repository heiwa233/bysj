package com.cheng.bysj.mapper;

import com.cheng.bysj.pojo.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author zhangzhou
 */
@Mapper
public interface OrderMapper {
    List<Order> orderList(Integer storeid);
    Order queryOrderById(Integer id);
}
