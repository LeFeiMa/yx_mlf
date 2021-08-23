package com.baizhi.controller;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@CrossOrigin
@RestController

public class yzmController {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @RequestMapping("yzm")
    public Map<String,String> quen(String phone) throws JSONException {
        RestTemplate template = new RestTemplate();
        String jjjj = template.getForObject("http://81.70.153.232:8080/sms/sms?phone="+phone, String.class);

        System.out.println(jjjj);
        System.out.println(jjjj.substring(jjjj.length()-4));
        String substring = jjjj.substring(0, jjjj.length() - 5);
        JSONObject js = new JSONObject(jjjj);
        String code = js.getString("SendStatusSet");
        //System.out.println(js);
       // System.out.println(code);
        JSONArray jy = new JSONArray(code);
       // System.out.println(jy);
        JSONObject jsonObject = jy.getJSONObject(0);
        //System.out.println(jsonObject);
        System.out.println("手机号是："+jsonObject.getString("PhoneNumber"));
        System.out.println("验证码是："+jsonObject.getString("Code"));
        Map<String,String> map = new HashMap<>();
        if(jsonObject.getString("Code").equals("Ok")){
            map.put("sms","验证码发送成功");



            stringRedisTemplate.setValueSerializer(new JdkSerializationRedisSerializer());
            String substring1 = jjjj.substring(jjjj.length() - 4);
            stringRedisTemplate.opsForValue().set(phone, substring1,200, TimeUnit.SECONDS);
            String testMultiSet1 = stringRedisTemplate.opsForValue().get(phone);
            System.out.println(testMultiSet1);

        }
        if(jsonObject.getString("Code").equals("InvalidParameterValue.IncorrectPhoneNumber")){
            map.put("sms","您输入的手机号有误，请重新输入");
        }
        if(jsonObject.getString("Code").equals("LimitExceeded.PhoneNumberThirtySecondLimit")){
            map.put("sms","您的请求过于频繁请稍后再试");
        }
        if(jsonObject.getString("Code").equals("FailedOperation.FailResolvePacket")){
            map.put("sms","操作失败请重试");
        }
        return map;

    }



    @RequestMapping("sms")
    public String sms(String phone,String yzm){

        stringRedisTemplate.setValueSerializer(new JdkSerializationRedisSerializer());
        String testMultiSet1 = stringRedisTemplate.opsForValue().get(phone);
       if(testMultiSet1 != null){
           if(testMultiSet1.equals(yzm)){
               return "验证成功";
           }else {
               return "您的验证码输入错误";
           }

       }else {
           return "您的验证码已失效";
       }

    }


}




    /*public String bb(){
        RestTemplate template = MyTemplate.getRestTemplate();
        //直接调用，路径写死，不利于维护以及负载均衡
        //template.getForObject("http://localhost:8888/aa",String.class);

        //获得指定服务名的主机集合，自己写随机数获得随机的主机
    *//*List<ServiceInstance> order = discoveryClient.getInstances("ORDER");
    template.getForObject(order.get(new Random().nextInt(order.size())).getUri()+"/aa",String.class);*//*

        //直接获得随机循环的主机
    *//*ServiceInstance order = loadBalanced.choose("ORDER");
    template.getForObject(order.getUri()+"/aa",String.class);*//*

        //使用@loadBalcne注解：用在方法上，可以让对象具有ribbon负载均衡特性
        //ORDER:应用名，做负载均衡时只负责IP和端口号
        //某个时间之后会自动添加新的节点或者删除宕机的节点
        template.getForObject("http://ORDER/aa",String.class);

        return "bb";
    }*/