package com.baizhi.controller;

import com.baizhi.entity.User;
import com.baizhi.service.UserSercice;
import com.baizhi.util.upload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;
import java.util.UUID;

@RestController
@CrossOrigin
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserSercice userSercice;

    @RequestMapping("all")
    //分页查所有
    public Map<String,Object> all(int page){
        Map<String, Object> map = userSercice.queryAll(page);

        return map;
    }


    @RequestMapping("upstatus")
    //修改状态

    public void upstatus(String id,Integer status){
        userSercice.upstatus(id,status);
    }

    @RequestMapping("add")
    //添加用户

    public Boolean upstatus(MultipartFile photo,String username,String phone,String brief){
        String uuid = UUID.randomUUID().toString().replace("-", "");
        Boolean up = upload.up(photo, uuid);

        if(up){

            String fileRealName = photo.getOriginalFilename();
            //获得原始文件名;
            int pointIndex = fileRealName.lastIndexOf(".");
            // 点号的位置
            String fileSuffix = fileRealName.substring(pointIndex);
            // 截取文件后缀

            userSercice.add(new User(null,username,phone,"https://1234-1304296293.cos.ap-nanjing.myqcloud.com/headimg/"+uuid+fileSuffix,brief,null,null,null));
            return true;
        }else {
            return false;
        }
    }
    @RequestMapping("del")
    public boolean del(String id,String img){
        try {
            System.out.println(img);
            userSercice.del(id,img);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @RequestMapping("uphead")
    public boolean updata(MultipartFile photo,String imgname,String id){
        try {
           /* System.out.println("chu");
            System.out.println(imgname);
            String image = imgname.substring(imgname.lastIndexOf("myqcloud.com")+21);
            System.out.println("          =======      "+image);
            String fileSuffix = image.substring(0,image.indexOf("."));*/

            String fileRealName = photo.getOriginalFilename();
            //获得原始文件名;
            int pointIndex = fileRealName.lastIndexOf(".");
            // 点号的位置
            String fileSuffix = fileRealName.substring(pointIndex);
            // 截取文件后缀
            upload.del(imgname);
            System.out.println("要删除的数据是"+imgname);
            /*userSercice.add(new User(null,username,phone,"https://1234-1304296293.cos.ap-nanjing.myqcloud.com/headimg/"+uuid+fileSuffix,brief,null,null,null));*/




            String uuid = UUID.randomUUID().toString().replace("-", "");
            Boolean up = upload.up(photo, uuid);

            userSercice.uphead(id,"https://1234-1304296293.cos.ap-nanjing.myqcloud.com/headimg/"+uuid+fileSuffix);

            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @RequestMapping("upuser")
    public boolean upuser(String id,String username,String brief){
        try {
           userSercice.upuser(id, username, brief);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }


    @RequestMapping("daochu")
    public boolean daochu(){
        try {
            userSercice.daochu();
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }






}


