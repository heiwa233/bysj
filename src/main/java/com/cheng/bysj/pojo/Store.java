package com.cheng.bysj.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Setter;

/**
 * @author ：猫丞丞 软件19-3 SVTCC
 * @description：TODO
 * @date ：2021/10/21 8:07 下午
 */
@Data
@Setter
@AllArgsConstructor
public class Store {
    private Integer id;
    private String name;
    private String password;
    private String tel;
}
