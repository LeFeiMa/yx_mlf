package com.baizhi.service;

import com.baizhi.annotation.Delete;
import com.baizhi.dao.CategoryDao;
import com.baizhi.entity.Category;
import com.baizhi.util.SendSms;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.stream.IntStream;

@Transactional
@Service
public class CategoryServerImpl implements CategoryServer{
    @Resource
    private CategoryDao cate;

    //通过类别查所有
    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<Category> queryOne(Integer clz) {
        List<Category> categories = cate.queryOne(clz);
        return categories;
    }
    //通过上级id查二级类别
    @Override
    public List<Category> querTwo(String parentid) {
       return cate.querTwo(parentid);
    }
    //添加二级类别

    @Delete
    @Override
    public void addTwo(Category category) {

        if(cate.queryByname(category)!=null){

            throw new RuntimeException();
        }

        String s = UUID.randomUUID().toString();
        category.setId(s);
        category.setLevels(2);
        cate.addTwo(category);
    }
    //删除二级类别

    public void delTwo(String id) {
        cate.delTwo(id);
    }
    //删除一级类别
    @Delete
    @Override
    public void delOne(String id) {
        List<Category> queryall = cate.querTwo(id);


            if(queryall.size()!=0){
                System.out.println(queryall.size()!=0);
                throw new RuntimeException();
            }

            cate.delOne(id);



    }
    //添加一节类别
    @Delete
    @Override
    public void addOne(Category category) {
        if(cate.queryByname(category)!=null){
            System.out.println(cate.queryByname(category));
            throw new RuntimeException();
        }

        category.setId(UUID.randomUUID().toString());
        category.setLevels(1);

        //短信验证码
        /*int a = (int)((Math.random()*9+1)*1000);
        String c = a+"";
        System.out.println("yanzhengma"+c);
        SendSms.sms("17831132376",c);*/



       cate.addOne(category);
    }

    @Override
    @Delete
    public void updata(String id, String name) {
        cate.updata(id, name);
        System.out.println("con");
        System.out.println(id+name);
    }

    @Override
    public Category quen(String id) {
        Category quen = cate.quen(id);

        return quen;
    }
}
