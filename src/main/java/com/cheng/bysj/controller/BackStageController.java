package com.cheng.bysj.controller;

import com.cheng.bysj.mapper.OrderMapper;
import com.cheng.bysj.mapper.StoreMapper;
import com.cheng.bysj.pojo.Order;
import com.cheng.bysj.pojo.Store;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
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
    @GetMapping("/member-list")
    public String memberList(){
        return "backstage/member/member-list";
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
        return "backstage/order/order-list";
    }
    @RequestMapping("/orderView{id}")
    public String orderView(@PathVariable("id") Integer id,
                            Model model){
        System.out.println("id = " + id);
        List<Order> orderById = orderMapper.queryOrderById(id);
        model.addAttribute("orderById",orderById);
        return "backstage/order/order-view";
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
