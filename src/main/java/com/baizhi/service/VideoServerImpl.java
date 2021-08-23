package com.baizhi.service;

import com.baizhi.annotation.Delete;
import com.baizhi.dao.VideoDao;
import com.baizhi.entity.Video;
import com.baizhi.util.upload;
import com.qcloud.cos.COSClient;
import com.qcloud.cos.model.PutObjectRequest;
import com.qcloud.cos.model.PutObjectResult;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.*;

import static com.baizhi.util.upload.Before1;


@Service
@Transactional
public class VideoServerImpl implements VideoServer{
    @Resource
    private VideoDao videoDao;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Map<String,Object> queryAll(Integer str) {
            Map<String,Object> map = new HashMap<>();
        try {
            int size = 2;
            int cou = 1;
            List<Video> videos = videoDao.queryAll((str - 1) * size, size);
            Integer count = videoDao.count();
            if(count%2==0){
                cou = count/size;
            }else{
                cou = count/size+1;
            }
            map.put("data",videos);
            map.put("now",str);
            map.put("count",cou);
            map.put("or",true);
            map.put("message","查询成功");
            map.put("status","100");
            return map;
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException();
        }

    }

    @Override
    @Delete
    public void del(String id,String vie) throws InterruptedException {
        upload.del(vie);
        System.out.println("server"+vie);
        videoDao.del(id);
        Thread.sleep(1000);







    }

    @Override
    @Delete
    public void add(Video video, MultipartFile vide) {

        try {
            COSClient cosClient = Before1();

            String uuid = UUID.randomUUID().toString();
            video.setId(uuid);
            String fileRealName = vide.getOriginalFilename();
            //获得原始文件名;
            int pointIndex = fileRealName.lastIndexOf(".");
            // 点号的位置
            String fileSuffix = fileRealName.substring(pointIndex);
            String aa = "https://1234-1304296293.cos.ap-nanjing.myqcloud.com/video/" + uuid + fileSuffix;
            String bb = "https://1234-1304296293.cos.ap-nanjing.myqcloud.com/headimg/" + uuid + "_0" + ".jpg";


            String bucketName = "1234-1304296293";
// 指定文件上传到 COS 上的路径，即对象键。例如对象键为folder/picture.jpg，则表示将文件 picture.jpg 上传到 folder 路径下
            String key = "video/" + uuid + fileSuffix;
            byte[] po = null;
            try {
                po = vide.getBytes();
            } catch (Exception e) {

            }

            //字节数组转file
            File file = new File("upload");
            OutputStream os = new FileOutputStream(file);
            BufferedOutputStream bos = new BufferedOutputStream(os);
            bos.write(po);

            PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, key, file);
            PutObjectResult putObjectResult = cosClient.putObject(putObjectRequest);
            System.out.println(putObjectResult);


            System.out.println(aa);
            System.out.println(bb);
            video.setVideopath(aa);
            video.setCoverpath(bb);
            video.setCreatedata(new Date());
            Thread.sleep(1000);
            videoDao.add(video);
        }catch (Exception e){

        }






    }


}
