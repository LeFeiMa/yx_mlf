package com.baizhi.service;

import com.baizhi.dao.AdminDao;
import com.baizhi.entity.Admin;
import org.springframework.beans.factory.annotation.Autowired;

public interface AdminService {
    //登录  通过名字查询是否有这个人
    Admin querybyName(String username);
    //管理员用户修改密码
    void updata(Admin admin);
    //添加管理员
    void add(Admin admin);


}
