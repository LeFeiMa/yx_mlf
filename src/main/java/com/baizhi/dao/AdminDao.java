package com.baizhi.dao;

import com.baizhi.entity.Admin;

import java.util.List;

public interface AdminDao {
   //管理员查所有
   List<Admin> queryAll();
   //登录  通过名字查询是否有这个人
   Admin querybyName(String username);
   //管理员用户修改密码
   void updata(Admin admin);
   //添加管理员
   void add(Admin admin);


}
