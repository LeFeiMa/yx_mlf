package com.baizhi.service;

import com.baizhi.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface UserSercice {

    //查询所有用户
    Map<String,Object> queryAll(int start);
    //修改用户状态
    void upstatus(String id,Integer status);
    //查询所有条数
    Integer count();
    //添加用户
    void add(User user);
    //删除用户
    void del(String id,String img);
    //修改头像
    void uphead(String id,String photo);
    //修改用户信息
    void upuser(String id,String username,String brief);
    void daochu();
}
