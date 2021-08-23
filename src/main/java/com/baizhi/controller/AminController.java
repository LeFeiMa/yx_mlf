package com.baizhi.controller;

import com.baizhi.entity.Admin;
import com.baizhi.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("admin")
public class AminController {
   @Autowired
   private AdminService adminService;

    //登录功能
    @RequestMapping("login")
    public Map<String, Object> login(String username, String password){
        System.out.println(username);
        Admin admin = adminService.querybyName(username);
        Map<String,Object> map = new HashMap<>();
        if(admin!=null){
            if(admin.getPassword().equals(password)){
                //登陆成功
                map.put("go",true);
                map.put("login","登录成功");
            }else{
                //密码错误
                map.put("login","密码错误");
                map.put("go",false);
            }
        }else{
            //用户不存在
            map.put("login","用户不存在");
            map.put("go",false);
        }

        return map;
    }


}
