package com.baizhi.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
//自定义注解哈哈哈哈  我是2021版本的idea
//固定写法
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Delete {
}
