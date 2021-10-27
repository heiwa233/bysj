package com.cheng.bysj.config;

import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author ：猫丞丞 软件19-3 SVTCC
 * @description：TODO
 * @date ：2021/10/20 4:06 下午
 */
public class MyConfig implements WebMvcConfigurer {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/index.html").setViewName("index");
        registry.addViewController("/").setViewName("index");
//        registry.addViewController("/userLogin.html").setViewName("userLogin");
//        registry.addViewController("/userFunc.html").setViewName("userFunc");

    }
}
