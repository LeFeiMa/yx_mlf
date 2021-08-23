package com.baizhi.service;

import com.baizhi.dao.AdminDao;
import com.baizhi.entity.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Transactional
@Service
public class AdminServerImpl implements AdminService{

   @Resource
   private AdminDao adminDao;

    @Override
    //登录查询
    @Transactional(propagation = Propagation.SUPPORTS)

    public Admin querybyName(String username) {
        Admin admin = adminDao.querybyName(username);
        return admin;
    }

    @Override
    //修改密码
    public void updata(Admin admin) {

    }

    @Override
    //添加一个管理员
    public void add(Admin admin) {

    }
}
