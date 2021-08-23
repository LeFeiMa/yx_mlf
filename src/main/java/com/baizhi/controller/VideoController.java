package com.baizhi.controller;


import com.baizhi.entity.Category;
import com.baizhi.entity.Video;
import com.baizhi.service.VideoServer;
import com.baizhi.util.upload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("video")
public class VideoController {
    @Autowired
    private VideoServer videoServer;

    @RequestMapping("all")
    public Map<String,Object> queryAll(Integer str){
        try{
            Map<String, Object> stringObjectMap = videoServer.queryAll(str);
            return stringObjectMap;
        }catch (Exception e){
            e.printStackTrace();
            return (Map<String, Object>) new HashMap<String,Object>().put("or",false);
        }
    }



    @RequestMapping("add")
    public Boolean add(MultipartFile video,String id,String title,String brief){
        try{
            //System.out.println(video+id+title+brief);
            Category category = new Category(id,null,null,null);
            Video video1 = new Video();
            video1.setCategory(category);
            video1.setTitle(title);
            video1.setBrief(brief);
            videoServer.add(video1,video);


            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
    @RequestMapping("del")
    public Boolean del(String id,String path,String video){

        try{
            System.out.println(id);
            upload.del(path);
            videoServer.del(id,video);

            //upload.del(vid);
            System.out.println("删除执行力");





            return true;
        }catch (Exception e){
            return false;
        }



    }



}
