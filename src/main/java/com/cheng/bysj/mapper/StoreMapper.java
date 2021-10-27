package com.cheng.bysj.mapper;

import com.cheng.bysj.pojo.Store;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * @author ：猫丞丞 软件19-3 SVTCC
 * @description：TODO
 * @date ：2021/10/27 4:18 下午
 */
@Mapper
public interface StoreMapper {
    @Select("SELECT * FROM Store WHERE id=#{id}")
    Store queryStoreById(String id);
}
