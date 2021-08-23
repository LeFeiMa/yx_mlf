package com.baizhi.util;


import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.http.HttpProtocol;
import com.qcloud.cos.model.PutObjectRequest;
import com.qcloud.cos.model.PutObjectResult;
import com.qcloud.cos.region.Region;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

public final class upload {

    public static COSClient Before1(){

        // 1 初始化用户身份信息（secretId, secretKey）。
// SECRETID和SECRETKEY请登录访问管理控制台进行查看和管理
        String secretId = "AKIDnPmsuc6JGxOYR2MPAespVv4xZuymJZXF";
        String secretKey = "SdXAWcRzHI3XljQ5nJk083kCPO7bpoX1";
        COSCredentials cred = new BasicCOSCredentials(secretId, secretKey);
        // 2 设置 bucket 的地域, COS 地域的简称请参照 https://cloud.tencent.com/document/product/436/6224
// clientConfig 中包含了设置 region, https(默认 http), 超时, 代理等 set 方法, 使用可参见源码或者常见问题 Java SDK 部分。
        Region region = new Region("ap-nanjing");
        ClientConfig clientConfig = new ClientConfig(region);
// 这里建议设置使用 https 协议
        clientConfig.setHttpProtocol(HttpProtocol.https);
        // 3 生成 cos 客户端。
        COSClient cosClient = new COSClient(cred, clientConfig);
        return cosClient;
    }

    public static boolean up(MultipartFile photo, String uuid) {


     /*   String bucketName = "1234-1304296293";
// 指定被删除的文件在 COS 上的路径，即对象键。例如对象键为folder/picture.jpg，则表示删除位于 folder 路径下的文件 picture.jpg
        String key = "headimg/2.jpg";
        cosClient.deleteObject(bucketName, key);*/

        COSClient cosClient = Before1();


// 指定要上传的文件

        try {

            String fileRealName = photo.getOriginalFilename();
            //获得原始文件名;
            int pointIndex = fileRealName.lastIndexOf(".");
            // 点号的位置
            String fileSuffix = fileRealName.substring(pointIndex);
            // 截取文件后缀
            String fileNewName = uuid;
            // 新文件名,UUID
            System.out.println("====="+uuid);
            String saveFileName = fileNewName.concat(fileSuffix);
            //新文件完整名（含后缀）
            System.out.println(saveFileName);
           /* String filePath = "D:\\FileAll\\" + saveFileName;
            File path = new File(filePath);
            //判断文件路径下的文件夹是否存在，不存在则创建
            if (!path.exists()) {
                path.mkdirs();
            }
            File savedFile = new File(filePath);
            boolean isCreateSuccess = false;
            try {
                isCreateSuccess = savedFile.createNewFile();
            } catch (Exception e) {
                System.out.println(2);
                return false;
            }
            // 是否创建文件成功
            if (true) {
                // 将文件写入
                // 第一种
                try {


                    photo.transferTo(savedFile);
                } catch (Exception e) {
                    return false;
                }
            } else {
                System.out.println(3);
                return false;
            }
*/

            //File localFile = new File("D:\\FileAll", saveFileName);
// 指定文件将要存放的存储桶
            String bucketName = "1234-1304296293";
// 指定文件上传到 COS 上的路径，即对象键。例如对象键为folder/picture.jpg，则表示将文件 picture.jpg 上传到 folder 路径下
            String key = "headimg/" + saveFileName;
            byte[] po = null;
            try{
                po=photo.getBytes();
            }catch (Exception e){

            }




            //字节数组转file
            File file = new File("upload");
            OutputStream os = new FileOutputStream(file);
            BufferedOutputStream bos = new BufferedOutputStream(os);
            bos.write(po);




            PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, key, file);
            PutObjectResult putObjectResult = cosClient.putObject(putObjectRequest);
            System.out.println(putObjectResult);
            return true;

        }catch (Exception e){
            e.printStackTrace();
            return false;
        }

        }

        public static void del (String img){
            COSClient cosClient = Before1();

            String bucketName = "1234-1304296293";
// 指定被删除的文件在 COS 上的路径，即对象键。例如对象键为folder/picture.jpg，则表示删除位于 folder 路径下的文件 picture.jpg
            String image = img.substring(img.lastIndexOf("myqcloud.com") + 13);
            String key = image;

            System.out.println("upload删除"+key);
            cosClient.deleteObject(bucketName, key);
        }






}
