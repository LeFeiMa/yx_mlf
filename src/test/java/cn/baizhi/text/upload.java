package cn.baizhi.text;


import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.http.HttpProtocol;
import com.qcloud.cos.model.PutObjectRequest;
import com.qcloud.cos.model.PutObjectResult;
import com.qcloud.cos.region.Region;

import java.io.File;


public class upload {
    public static void main(String[] args) {


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

     /*   String bucketName = "1234-1304296293";
// 指定被删除的文件在 COS 上的路径，即对象键。例如对象键为folder/picture.jpg，则表示删除位于 folder 路径下的文件 picture.jpg
        String key = "headimg/2.jpg";
        cosClient.deleteObject(bucketName, key);*/




// 指定要上传的文件
        File localFile = new File("D:\\Desktop","243c264f529048bfa509cb24e5b6c36f.mp4");
// 指定文件将要存放的存储桶
        String bucketName = "1234-1304296293";
// 指定文件上传到 COS 上的路径，即对象键。例如对象键为folder/picture.jpg，则表示将文件 picture.jpg 上传到 folder 路径下
        String key = "video/243c264f529048bfa509cb24e5b6c36f.mp4";
        PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, key, localFile);
        PutObjectResult putObjectResult = cosClient.putObject(putObjectRequest);






    }

}
