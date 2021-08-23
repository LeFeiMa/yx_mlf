package com.baizhi.service;

import com.baizhi.entity.Video;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface VideoServer {
    //分页查所有视频
    Map<String,Object> queryAll(Integer str);
    //删除一个
    void del(String id,String vie) throws InterruptedException;
    //添加一个
    void add(Video video, MultipartFile vide);
}
