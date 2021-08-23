package com.baizhi.dao;

import com.baizhi.entity.Video;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface VideoDao {
    //分页查所有视频
    List<Video> queryAll(@Param("str") Integer str,@Param("size") Integer size);
    //删除视频
    void del(String id);
    //总页数
    Integer count();
    //添加一个
    void add(Video video);
    //修改视频
    void upda(Video video);



}
