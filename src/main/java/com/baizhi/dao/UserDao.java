package com.baizhi.dao;

import com.baizhi.entity.MonthAndCount;
import com.baizhi.entity.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;


import java.util.List;

public interface UserDao {
    //查询所有用户
    List<User> queryAll(@Param("start") int start,@Param("end") int end);
    //修改用户状态
    void upstatus(@Param("id") String id,@Param("status") Integer status);
    //查询所有条数
    Integer count();
    //添加用户
    void add(User user);
    //删除一个用户
    void del(String id);
    //修改头像
    void uphead(@Param("id") String id,@Param("photo") String photo);
    //修改用户信息
    void upuser(@Param("id") String id,@Param("username") String username,@Param("brief")String brief);
    @Select("SELECT MONTH(create_date) month,COUNT(*) count FROM `user` where sex = #{sex}  GROUP BY MONTH(create_date)")
    List<MonthAndCount> mon(String sex);


}
