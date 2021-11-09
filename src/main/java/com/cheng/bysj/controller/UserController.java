package com.cheng.bysj.controller;

import com.cheng.bysj.mapper.CarMapper;
import com.cheng.bysj.mapper.OrderMapper;
import com.cheng.bysj.mapper.UserMapper;
import com.cheng.bysj.pojo.Car;
import com.cheng.bysj.pojo.Order;
import com.cheng.bysj.pojo.User;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;
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
    @Autowired
    OrderMapper orderMapper;


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
        if (queryUserByUsername==null){
            model.addAttribute("logMsg","用户不存在");
            return "user/userLogin";
        }
        else if (!(queryUserByUsername.getPassword().equals(password))){
            model.addAttribute("logMsg","用户密码错误");
            return "user/userLogin";
        }else {
            session.setAttribute("user",queryUserByUsername);
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
    @RequestMapping("carDetails{id}")
    public String carDetails(@PathVariable("id") Integer id){

        return "user/carDetails";
    }
    @RequestMapping("orderAdd{id}")
    public String orderAdd(@PathVariable("id") Integer id, Model model){
        Car queryCarById = carMapper.queryCarById(id);
        model.addAttribute("car",queryCarById);
        return "user/orderAdd";
    }
    @PostMapping("/orderAdd")
    public String orderAdd(
            Integer id, String carClass, Double price, String carStartTime,String carEndTime,
            Model model,HttpSession session) throws Exception{

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate=df.parse(carStartTime);
        Date endDate=df.parse(carEndTime);
        int i = endDate.getDate() - startDate.getDate();
        if (i<0){
            i=(-i);
        }
        User user = (User) session.getAttribute("user");
        Order order=new Order();
        order.setCarId(id);
        order.setUserId(user.getId());
        order.setOrderPrice(price*i);
        order.setOrderStartTime(startDate);
        order.setOrderEndTime(endDate);

        Integer integer = orderMapper.insertOrder(order);
        System.out.println("integer = " + integer);
        return "redirect:/userFunc";
    }



}
