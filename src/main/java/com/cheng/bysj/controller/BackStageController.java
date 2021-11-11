package com.cheng.bysj.controller;

import com.cheng.bysj.mapper.OrderMapper;
import com.cheng.bysj.mapper.StoreMapper;
import com.cheng.bysj.mapper.UserMapper;
import com.cheng.bysj.pojo.Order;
import com.cheng.bysj.pojo.Store;
import com.cheng.bysj.pojo.User;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

/**
 * @author ：猫丞丞 软件19-3 SVTCC
 * @description：TODO
 * @date ：2021/10/26 11:33 上午
 */
@Controller
public class BackStageController {
    @Autowired
    StoreMapper storeMapper;
    @Autowired
    OrderMapper orderMapper;
    @Autowired
    UserMapper userMapper;

    @GetMapping("/manageLogin")
    public String manageLogin(){
        return "backstage/login";
    }
    @PostMapping("/manageLogin")
    public String manageLogin(String id,
                              String password,
                              HttpSession session,
                              Model model){
        Store store = storeMapper.queryStoreById(id);
        if (store==null){
            model.addAttribute("manageLogin","商家账户不存在，请重试或联系在线客服");
            return "backstage/login";
        }else if (!(store.getPassword().equals(password))){
            model.addAttribute("manageLogin","商家密码错误，请重试");
            return "backstage/login";
        }else {
            session.setAttribute("manage",store);
            return "redirect:/manageIndex";
        }
    }
    @GetMapping("/manageIndex")
    public String manageIndex(){
        return "backstage/index";
    }

    @GetMapping("/welcome1")
    public String welcome1(){
        return "backstage/welcome1";
    }
    @RequestMapping("/member-list")
    public String memberList(Model model){
        List<User> users = userMapper.queryUsers();
        model.addAttribute("users",users);
        return "backstage/member/member-list";
    }
    @RequestMapping("/member-edit_{id}")
    public String memberEdit(Model model,@PathVariable("id") Integer id){
        User user = userMapper.queryUserById(id);
        model.addAttribute("user",user);
        return "backstage/member/member-edit";
    }
@RequestMapping("/member-edit")
    public String memberEdit(
            String username,
            String email,
            String nickname,
            String tel){
    User user=new User();
    user.setName(username);
    user.setEmail(email);
    user.setNickname(nickname);
    user.setTel(tel);
    userMapper.editUserByUserName(user);
    return "redirect:/member-list";
    }
    @RequestMapping("/member-password_{id}")
    public String memberPassword(@PathVariable("id") Integer id,
                                 Model model){
        User user = userMapper.queryUserById(id);
        model.addAttribute("user",user);
        return "backstage/member/member-password";
    }
    @PostMapping("/member_password{id}")
    public String memberPassword(Integer id,
                                 String newpass,
                                 String repass){
        if (newpass.equals(repass)){
            User user=new User();
            user.setId(id);
            user.setPassword(newpass);
            userMapper.editUserByUserid(user);
        }
        return "redirect:/member-list";
    }

    @RequestMapping("/welcome")
    public String welcome(){
        return "backstage/welcome";
    }

    @RequestMapping("/carList")
    public String carList(){
        return "backstage/car/car-list";
    }
    @RequestMapping("/orderAdd")
    public String orderAdd(){
        return "backstage/order/order-add";
    }
    @RequestMapping("/orderList")
    public String orderList(Model model,HttpSession session){
        Store store = (Store) session.getAttribute("manage");
        List<Order> orderList = orderMapper.orderList(store.getId());
        model.addAttribute("orderList",orderList);
        System.out.println("orderList = " + orderList);
        return "backstage/order/order-list";
    }
    @RequestMapping("/orderView_{id}")
    public String orderView(@PathVariable("id") Integer id,
                            Model model){
        System.out.println("id = " + id);
        Order orderById = orderMapper.queryOrderById(id);
        model.addAttribute("orderById",orderById);
        return "backstage/order/order-view";
    }
    @PostMapping("/editOrder")
    public String editOrder(
            @RequestParam("id") String id,
            @RequestParam("startTime") String startTime,
            @RequestParam("endTime") String endTime,
            @RequestParam("payStatus") String payStatus,
            @RequestParam("orderStatus") String orderStatus

    ){
        System.out.println("id = " + id);
        System.out.println("startTime = " + startTime);
        System.out.println("endTime = " + endTime);
        System.out.println("payStatus = " + payStatus);
        System.out.println("orderStatus = " + orderStatus);
        return "redirect:/orderList";
    }
    @RequestMapping("delOrder/{id}")
    public String delOrder(Integer id){
        System.out.println("id"+id);
        return "redirect:/orderList";
    }



    @RequestMapping("/adminRole")
    public String adminRole(){
        return "backstage/admin/admin-role";
    }


    @RequestMapping("/roleAdd")
    public String roleAdd(){
        return "backstage/role-add";
    }
    @RequestMapping("/demo.html")
    public String demo(){
        return "backstage/demo";
    }


}
