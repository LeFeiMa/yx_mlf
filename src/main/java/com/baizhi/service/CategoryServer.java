package com.baizhi.service;

import com.baizhi.entity.Category;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CategoryServer {
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
    //修改二级类别
    void updata(String id,String name);
    //根据二级类别查询一及类别
    Category quen(String id);


}
