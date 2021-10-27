package com.cheng.bysj.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Setter;

/**
 * @author ：猫丞丞 软件19-3 SVTCC
 * @description：TODO
 * @date ：2021/10/21 8:22 下午
 */
@Data
@Setter
@AllArgsConstructor
public class Car {
    private Integer id;
    private String model;
    private Integer store_id;
    private String car_class;
    private Double oldPrice;
    private Double price;
    private String imgUrl;
    private String gearBox;
    private Integer seat;
    private String aspiration;
    private String tank;
    private String burn;
}
