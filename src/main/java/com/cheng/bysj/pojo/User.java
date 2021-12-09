package com.cheng.bysj.pojo;

import lombok.*;

/**
 * @author ：猫丞丞 软件19-3 SVTCC
 * @description：TODO
 * @date ：2021/10/20 5:09 下午
 */
@AllArgsConstructor
@Data
@Setter
@NoArgsConstructor
public class User {
    private Integer id;
    private String name;
    private String nickname;
    private String password;
    private String tel;
    private String email;
    private String age;
}
