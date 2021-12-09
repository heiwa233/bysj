package com.cheng.bysj.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Setter;

import java.util.Date;

/**
 * @author ：猫丞丞 软件19-3 SVTCC
 * @description：TODO
 * @date ：2021/10/21 8:08 下午
 */
@Data
@Setter
@AllArgsConstructor
public class Question {
    private Integer id;
    private Integer orderId;
    private Date time;
    private String questionClass;
    private Integer customerId;

}
