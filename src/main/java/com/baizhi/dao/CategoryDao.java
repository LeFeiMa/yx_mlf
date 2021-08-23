package com.baizhi.dao;

import com.baizhi.entity.Category;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CategoryDao {
    //通过类别查所有
    List<Category> queryOne(Integer clz);
    //通过上级id查所有（查二级分类）
    List<Category> querTwo(String parentid);
    //添加一个二级分类
    void addTwo(Category category);
    //删除一个二级分类
    void delTwo(String id);
    //删除一个一级分类
    void delOne(String id);
    //添加一个一级分类
    void addOne(Category category);
    //通过名字和id查
    Category queryByname(Category category);
    //查询一级类别是否有二级类别
    List<Category> queryall(String id);
    //修改二级类别
    void updata(@Param("id") String id, @Param("name") String name);
    //根据二级类别查询一及类别
    Category quen(String id);
}
