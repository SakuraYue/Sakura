package com.fh.util;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;

import java.io.InputStream;
import java.util.UUID;

public class AliyunOssUtil {

 private final static String ENDPOINT = "http://oss-cn-qingdao.aliyuncs.com";
 // 阿里云主账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM账号进行API访问或日常运维，请登录 https://ram.console.aliyun.com 创建RAM账号。
 private final static String ACCESS_KEY_ID = "LTAI4FqAyjEUPBmNcR8ceDv3";
 private final static String ACCESS_KEY_SECRET = "PBEJg2LKUktRQC5I34GmBkyvBaFawr";
 private final static String BUCKET_NAME = "xueya0923";
 private final static String BUCKET_URL = "http://xueya0923.oss-cn-qingdao.aliyuncs.com";

 public static String uploadFile(InputStream inputStream,String originalFileName,String folderName){
  // <yourObjectName>上传文件到OSS时需要指定包含文件后缀在内的完整路径，例如abc/efg/123.jpg。
  //给用户上传的文件重命名，有两种策略:UUID或者是时间戳
  //1.通过原始文件名获取后缀名
  String suffix = originalFileName.substring(originalFileName.lastIndexOf("."));
  //2.生成一个新的文件名
  String newFileName = UUID.randomUUID().toString() + suffix;

  String objectName = folderName + "/" + newFileName;

  String objectUrl = BUCKET_URL + "/" + objectName;

  // 创建OSSClient实例。
  OSS ossClient = new OSSClientBuilder().build(ENDPOINT, ACCESS_KEY_ID, ACCESS_KEY_SECRET);

  // 上传内容到指定的存储空间（bucketName）并保存为指定的文件名称（objectName）。
  ossClient.putObject(BUCKET_NAME, objectName, inputStream);

  // 关闭OSSClient。
  ossClient.shutdown();

  return objectUrl;
 }

 public static void deleteFile(String url){
  // 创建OSSClient实例。
  OSS ossClient = new OSSClientBuilder().build(ENDPOINT, ACCESS_KEY_ID, ACCESS_KEY_SECRET);
  // 删除文件。
  ossClient.deleteObject(BUCKET_NAME,url.replace(BUCKET_URL+"/",""));
  // 关闭OSSClient。
  ossClient.shutdown();
 }
}
