package com.cheng.bysj.mapper;

import com.cheng.bysj.pojo.Car;
import com.cheng.bysj.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author ：猫丞丞 软件19-3 SVTCC
 * @description：TODO
 * @date ：2021/10/25 8:07 下午
 */
@Mapper
public interface UserMapper {
    @Select("select * from user where name=#{username}")
    User queryUserByUsername(String username);

    Integer insertUser(User user);

}
