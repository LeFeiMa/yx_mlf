package com.baizhi.service;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import com.baizhi.annotation.Delete;
import com.baizhi.dao.UserDao;
import com.baizhi.entity.User;
import com.baizhi.util.upload;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileOutputStream;
import java.util.*;

@Transactional
@Service

public class UserServiceImpl implements UserSercice{
    @Resource
    private UserDao userDao;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Map<String,Object> queryAll(int start) {
        Map<String,Object> map = new HashMap<>();
        int size = 3;
        int size1=0;
        System.out.println("===========================");
        List<User> users = userDao.queryAll((start - 1) * size, size);
        map.put("lists",users);
        map.put("now",start);
        map.put("message","查询成功");
        map.put("status","100");
        /*"message": "查询成功",
                "status": "100"*/
        size1 = count();
        if(size1%size!=0){
            size1 = size1/size+1;
        }else{
            size1 = size1/size;
        }
        map.put("size",size1);

        return map;
    }

    @Override
    @Delete
    public void upstatus(String id, Integer status) {
        userDao.upstatus(id,status);
    }

    @Override

    public Integer count() {
        Integer count = userDao.count();
        return count;
    }

    @Override
    @Delete
    public void add(User user) {

        user.setId(UUID.randomUUID().toString());
        user.setCreate_date(new Date());
        user.setStatus(1);
        user.setWechat(null);
        //.setBrief("默认想法");
        userDao.add(user);
    }

    @Override
    @Delete
    public void del(String id,String img) {
        userDao.del(id);
        upload.del(img);
    }

    @Override
    @Delete
    public void uphead(String id, String photo) {
        userDao.uphead(id, photo);
    }

    @Delete
    @Override
    public void upuser(String id, String username, String brief) {
        userDao.upuser(id, username, brief);
    }

    @Override

    public void daochu() {

        List<User> users = userDao.queryAll(0, 9999999);


        //参数：标题，表名，实体类类对象，导出的集合
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("计算机一班学生","学生"),User.class, users);
        try{
        workbook.write(new FileOutputStream(new File("E:/easypoi.xlsx")));
        workbook.close();
            }catch (Exception e){

}
    }
    }


