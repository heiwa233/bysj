package com.cheng.bysj.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

/**
 * @author ：猫丞丞 软件19-3 SVTCC
 * @description：TODO
 * @date ：2021/10/21 8:14 下午
 */
@Data
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    private Integer id;
    private Date orderTime;
    private Integer car_id;
    private Integer user_id;
    private String name;
    private Double price;
    private Date startTime;
    private Date endTime;
    private String payStatus;
    private String orderStatus;

}
