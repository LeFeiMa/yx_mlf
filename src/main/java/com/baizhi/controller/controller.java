package com.baizhi.controller;

import com.baizhi.entity.Video;
import com.baizhi.entity.contr;
import com.baizhi.service.VideoServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RequestMapping("yingx/app")
@CrossOrigin
@RestController
public class controller {
 @Autowired
    private VideoServer videoServer;

 @RequestMapping("queryByReleaseTime")
 public Map<String,Object> queryByReleaseTime(){
     System.out.println("=======================================");
         Map<String,Object> map = new HashMap<>();
         List<contr> aa = new ArrayList<>();
         aa.add(new contr("1","hahahha","https://1234-1304296293.cos.ap-nanjing.myqcloud.com/headimg/2cb8f497-ef5b-4be5-b27f-e662dfbce73c_0.jpg","https://1234-1304296293.cos.ap-nanjing.myqcloud.com/video/2cb8f497-ef5b-4be5-b27f-e662dfbce73c.mp4",new Date(),"你好",20,"java","https://1234-1304296293.cos.ap-nanjing.myqcloud.com/headimg/e78b7bfc9c9d4347bef3665320217a5d.JPG"));
     aa.add(new contr("2","hahahha","https://1234-1304296293.cos.ap-nanjing.myqcloud.com/headimg/2cb8f497-ef5b-4be5-b27f-e662dfbce73c_0.jpg","https://1234-1304296293.cos.ap-nanjing.myqcloud.com/video/2cb8f497-ef5b-4be5-b27f-e662dfbce73c.mp4",new Date(),"你好",20,"java","https://1234-1304296293.cos.ap-nanjing.myqcloud.com/headimg/e78b7bfc9c9d4347bef3665320217a5d.JPG"));
 try {

             map.put("data",aa);

             map.put("message","查询成功");
             map.put("status","100");
             return map;
         }catch (Exception e){
             e.printStackTrace();
     return  map;
         }

 }


}
