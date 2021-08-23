package com.baizhi.controller;



import com.baizhi.entity.Category;
import com.baizhi.service.CategoryServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("cate")
public class CategoryController {
    @Autowired
    private CategoryServer category;

    //查一级类别
    @RequestMapping("queryOne")
    public List<Category> queryOne(Integer clz){
        try{
        return category.queryOne(clz);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }

    }

    //查二级类别上级id查找二级类别
    @RequestMapping("queryTwo")
    public List<Category> queryTwo(String parent){
        try{
            return category.querTwo(parent);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    //删除二级类别
    @RequestMapping("delTwo")
    public Boolean delTwo(String id){
        try{
            category.delTwo(id);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }


    //添加二级类别
    @RequestMapping("addTwo")
    public String addTwo(String id,String catename,Integer levels,String parent){
        try{
            Category categorys = new Category(id,catename,levels,parent);

            category.addTwo(categorys);
            return "添加成功";
        }catch (RuntimeException re){
            return "您重名啦，请换个名字吧";
        }catch (Exception e){
            e.printStackTrace();
            return "添加失败";
        }
    }


    //删除一节类别
    @RequestMapping("/delOne")
    public String delOne(String id){
        try{
            category.delOne(id);
            return "删除成功";
        }catch (RuntimeException re){
            re.printStackTrace();
            return "此类别下还有二级类别呢，先删除二级类别吧0v0";
        }catch (Exception e){
            e.printStackTrace();
            return "删除失败";
        }
    }

    //添加一节类别
    @RequestMapping("addOne")
    public String addOne(String catename){
        try {
            category.addOne(new Category(null, catename, null, null));
            return "添加成功了";
        }catch(Exception e){
            e.printStackTrace();
            return "添加失败了";
        }
    }
    //修改二级类别
    @RequestMapping("updata")
    public String updata(String id,String name){
        try {
            category.updata(id, name);
            System.out.println("con");
            return "修改成功了";
        }catch(Exception e){
            e.printStackTrace();
            return "修改失败了";
        }
    }

    //根据二级类别查询一节类别
    @RequestMapping("quen")
    public Category quen(String id){
        try{
            return category.quen(id);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }



}
