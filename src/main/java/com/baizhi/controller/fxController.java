package com.baizhi.controller;


import com.baizhi.dao.UserDao;
import com.baizhi.entity.MonthAndCount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController

public class fxController {

    @Autowired
    private UserDao userDao;
    @RequestMapping("fx")
    public Map<String,Object> aa(){
        Map<String,Object> map = new HashMap<>();
        List<MonthAndCount> man = userDao.mon("男");
        List<MonthAndCount> woman = userDao.mon("女");



       int [] mana = new int[12];
        for (int i = 0;i<mana.length;i++){
            for (int s = 0;s<man.size();s++){
                if((man.get(s).getMonth()-1)==i){
                    mana[i] = man.get(s).getCount();
                }
            }
            //System
        }
        map.put("nan",mana);

        int[] womana = new int[12];
        for (int i = 0;i<womana.length;i++){
            for (int s = 0;s<woman.size();s++){
                if((woman.get(s).getMonth()-1)==i){
                    womana[i] = woman.get(s).getCount();
                }
            }
            //System.out.print(mana[i]+"     ");

        }

        for (int s = 0;s<woman.size();s++){
            womana[woman.get(s).getMonth()-1] = woman.get(s).getCount();
        }


        map.put("nv",womana);

        return map;

    }



}
