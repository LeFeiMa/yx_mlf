package cn.baizhi.text;


import com.baizhi.dao.AdminDao;
import com.baizhi.dao.CategoryDao;
import com.baizhi.dao.UserDao;
import com.baizhi.dao.VideoDao;
import com.baizhi.entity.*;

import com.baizhi.yxApplication;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@SpringBootTest(classes = yxApplication.class)
public class tst1 {
    @Autowired
    private AdminDao adminDao;
    @Autowired
    private UserDao us;
    @Autowired
    private CategoryDao ca;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private VideoDao vd;

    @Test
    public void aa(){
        System.out.println(adminDao);
        List<Admin> admins = adminDao.queryAll();
        for (Admin admin : admins) {
            System.out.println(admin);
        }
    }

    @Test
    public void bb(){
        System.out.println(adminDao);
        Admin admin = adminDao.querybyName("ll");

    }
    @Test
    public void cc(){
        adminDao.updata(new Admin("123456","gj","123456","0"));
    }
    @Test
    public void dd(){
        adminDao.add(new Admin("123456","gj1","123456","0"));
    }
    @Test
    public void ee(){
        List<User> users = us.queryAll(0, 3);
        for (User user : users) {
            System.out.println(user);
        }
    }

    @Test
    public void ff() {
            us.upstatus("111",0);
    }

    @Test
    public void gg() {
        List<Category> categories = ca.queryOne(1);
        for (Category category : categories) {
            System.out.println(category);
        }
    }

    @Test
    public void hh() {
        List<Category> categories = ca.querTwo("1");
        for (Category category : categories) {
            System.out.println(category);
        }
    }

    @Test
    public void nn() {
       ca.delOne("100");
    }

    @Test
    public void rt() throws JSONException {
        List<String> list = new ArrayList<>();
        String a = "{\"SendStatusSet\":[{\"SerialNo\":\"2433:264667004616288587816853237\",\"PhoneNumber\":\"+8617831132376\",\"Fee\":1,\"SessionContext\":\"xxx\",\"Code\":\"Ok\",\"Message\":\"send success\",\"IsoCode\":\"CN\"}],\"RequestId\":\"55947b61-05b1-4855-b48e-7e0bcc931007\"}";
        JSONObject js = new JSONObject(a);
        String code = js.getString("SendStatusSet");
        System.out.println(js);
        System.out.println(code);
        JSONArray jy = new JSONArray(code);
        System.out.println(jy);
        JSONObject jsonObject = jy.getJSONObject(0);
        System.out.println(jsonObject);
        System.out.println(jsonObject.getString("PhoneNumber"));
        System.out.println(jsonObject.getString("Code"));
    }

    @Test
    public void re()  {
        stringRedisTemplate.setValueSerializer(new JdkSerializationRedisSerializer());
        stringRedisTemplate.opsForValue().set("17831132376", "hello redis", 10, TimeUnit.SECONDS);
        String testMultiSet1 = stringRedisTemplate.opsForValue().get("17831132376");
        System.out.println(testMultiSet1);
    }

    @Test
    public void vb(){

    }

    @Test
    public void er()  {
        List<MonthAndCount> videos = us.mon("男");
        int [] mana = new int[12];
        for (int i = 0;i<mana.length;i++){
            for (int s = 0;s<videos.size();s++){
                if((videos.get(s).getMonth()-1)==i){
                    mana[i] = videos.get(s).getCount();
                }
            }
            System.out.print(mana[i]+"        ");

        }



    }





    }







