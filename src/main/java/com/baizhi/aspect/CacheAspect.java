package com.baizhi.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.concurrent.TimeUnit;

@Aspect
@Component
public class CacheAspect {
    @Autowired
    private RedisTemplate redisTemplate;

    @Around("execution(* com.baizhi.service.*Impl.query*(..))")
    public Object cacher(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        System.out.println("进入前置通知");
        //获得类名
        StringBuilder sb = new StringBuilder();


        String name = proceedingJoinPoint.getTarget().getClass().getName();
        System.out.println(name);
        sb.append(name);
        //获取方法名
        String name1 = proceedingJoinPoint.getSignature().getName();
        System.out.println(name1);
        sb.append(name1);
        //实参值+
        Object[] args = proceedingJoinPoint.getArgs();
        for (Object arg : args) {
            System.out.println(arg);
            sb.append(arg);
        }
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        Object o = redisTemplate.opsForValue().get(sb.toString());
        System.out.println(o);
        if(o!=null){
            return o;
        }

        System.out.println("全名是"+ sb.toString());
        Object proceed = proceedingJoinPoint.proceed();
        redisTemplate.opsForValue().set(sb.toString(),proceed);
        return proceed;
    }


    @After("@annotation(com.baizhi.annotation.Delete)")
    public void del(JoinPoint joinPoint){

        System.out.println("后置通知进入");
        String name = joinPoint.getTarget().getClass().getName();
        System.out.println("======"+name);
        Set keys = redisTemplate.keys("*");
        for (Object key : keys) {
            System.out.println("    =====    "+key);
           String s =  (String)key;

           if(s.startsWith(name)){
               redisTemplate.delete(key);
           }
        }

    }

}
