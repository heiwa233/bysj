package com.cheng.bysj.controller;

import com.cheng.bysj.mapper.CarMapper;
import com.cheng.bysj.mapper.UserMapper;
import com.cheng.bysj.pojo.Car;
import com.cheng.bysj.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author ：猫丞丞 软件19-3 SVTCC
 * @description：TODO
 * @date ：2021/10/20 3:55 下午
 */
@Controller
public class UserController {

    @Autowired
    UserMapper userMapper;
    @Autowired
    CarMapper carMapper;


    @GetMapping("/regUser")
    public String regUserAction(){
        return "user/userRegister";
    }
    @PostMapping("/regUser")
    public String regUserAction(
            String username,
            String nickname,
            String password,
            User user,
            Model model
    ){
        if (userMapper.queryUserByUsername(username)==null){
            user.setName(username);
            user.setNickname(nickname);
            user.setPassword(password);
            Integer insertUser = userMapper.insertUser(user);
            if (insertUser!=1){
                model.addAttribute("regMsg","注册有误，请联系管理员");
                return "user/userRegister";
            }
            else {
                model.addAttribute("logMsg","用户注册成功，请登录");
                return "redirect:/loginUser";
            }
        }else{
            model.addAttribute("regMsg","账户名已存在，请更换账户名");
            return "user/userRegister";
        }
    }

    @GetMapping("/loginUser")
    public String loginAction(){
        return "user/userLogin";
    }
    @PostMapping("/loginUser")
    public String loginAction(
            String username,
            String password,
            HttpSession session,
            Model model
    ){
        User queryUserByUsername = userMapper.queryUserByUsername(username);
        System.out.println(queryUserByUsername.getPassword().equals(password));
        if (queryUserByUsername==null){
            model.addAttribute("logMsg","用户不存在");
            return "user/userLogin";
        }
        else if (!(queryUserByUsername.getPassword().equals(password))){
            model.addAttribute("logMsg","用户密码错误");
            return "user/userLogin";
        }else {
            session.setAttribute("username",queryUserByUsername.getNickname());
            return "redirect:/main.html";
        }
    }

    @RequestMapping("/loginOut")
    public String loginOut(HttpSession session){
        session.removeAttribute("username");
        return "redirect:/";
    }

    @RequestMapping("/main.html")
    public String userFunc(Model model){
        List<Car> carList = carMapper.queryAllCar();
        System.out.println("carList = " + carList);
        model.addAttribute("carList",carList);
        return "user/userFunc";
    }



}
