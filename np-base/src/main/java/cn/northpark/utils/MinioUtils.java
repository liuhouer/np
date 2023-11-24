package cn.northpark.utils;

import cn.hutool.core.io.FileUtil;
import cn.northpark.form.FileUploadResponse;
import io.minio.*;
import io.minio.errors.*;
import io.minio.messages.Bucket;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Slf4j
public class MinioUtils {


    //==================================================文本部分 start========================================================================
    /**
     * 上传一段富文本到MINIO
     * @param text  富文本字符串
     * @date 2023-11-10 10:30:03
     */
    public static String uploadText(String text){
        // 文件唯一ID
        String oid = IDUtils.getInstance().generateOrderNumber()+".txt";

        // 文本转文件
        File tempFile = FileUtil.newFile(oid);
        FileUtil.writeString(text, tempFile, StandardCharsets.UTF_8);

        // 客户端
        MinioClient client = buildClient();

        // 上传文件
        try{
            client.uploadObject(UploadObjectArgs.builder().bucket(EnvCfgUtil.getValByCfgName("TEXT_BUCKET")).object(oid).filename(tempFile.getName()).build());

            // 删除临时文件
            tempFile.delete();
        }catch (Exception e){
           log.error("MINIO-uploadText--->,{}",e);
        }

        return oid;
    }

    /**
     * 从MINIO读取文本
     * @param oid  文件OID
     * @date 2023-11-10 10:30:03
     */
    public static String readText(String oid){
        // 客户端
        MinioClient client = buildClient();

        try {

            // 临时文件
            File tempFile = FileUtil.newFile(oid);

            // 下载文件
            client.downloadObject(DownloadObjectArgs.builder().bucket(EnvCfgUtil.getValByCfgName("TEXT_BUCKET")).object(oid).filename(tempFile.getName()).build());

            // 读取文件内容
            String text = FileUtil.readString(tempFile, StandardCharsets.UTF_8);

            // 删除临时文件
            tempFile.delete();

            return text;
        } catch (Exception e) {
            log.error("MINIO-readText--->,{}",e);
        }

        return "";
    }

  //==================================================文本部分 end========================================================================

    //================================================文件部分============================================================================

    /**
     * 创建bucket
     */
    public static void createBucket(String bucketName) throws Exception {
        // 客户端
        MinioClient client = buildClient();
        if (!client.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build())) {
            client.makeBucket(MakeBucketArgs.builder().bucket(bucketName).build());
        }
    }

    /**
     * 上传文件
     */
    public static FileUploadResponse uploadFile(MultipartFile file, String bucketName) throws Exception {
        // 客户端
        MinioClient client = buildClient();
        //判断文件是否为空
        if (null == file || 0 == file.getSize()) {
            return null;
        }
        //判断存储桶是否存在  不存在则创建
        createBucket(bucketName);
        //文件名
        String originalFilename = file.getOriginalFilename();
        //新的文件名 = 存储桶文件名_时间戳.后缀名
        assert originalFilename != null;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String fileName = bucketName + "_" +
                System.currentTimeMillis() + "_" + format.format(new Date()) + "_" + new Random().nextInt(1000) +
                originalFilename.substring(originalFilename.lastIndexOf("."));
        //开始上传
        client.putObject(
                PutObjectArgs.builder().bucket(bucketName).object(fileName).stream(
                        file.getInputStream(), file.getSize(), -1)
                        .contentType(file.getContentType())
                        .build());
        String url = EnvCfgUtil.getValByCfgName("MINIO_API") + "/" + bucketName + "/" + fileName;
        String urlHost = EnvCfgUtil.getValByCfgName("MINIO_DOMAIN") + "/" + bucketName + "/" + fileName;
        log.info("上传文件成功url ：[{}], urlHost ：[{}]", url, urlHost);
        return new FileUploadResponse(url, urlHost);
    }

    /**
     * 获取全部bucket
     *
     * @return
     */
    public List<Bucket> getAllBuckets() throws Exception {
        // 客户端
        MinioClient client = buildClient();
        return client.listBuckets();
    }

    /**
     * 根据bucketName获取信息
     *
     * @param bucketName bucket名称
     */
    public  Optional<Bucket> getBucket(String bucketName) throws IOException, InvalidKeyException, NoSuchAlgorithmException, InsufficientDataException, InvalidResponseException, InternalException, ErrorResponseException, ServerException, XmlParserException {
        // 客户端
        MinioClient client = buildClient();
        return client.listBuckets().stream().filter(b -> b.name().equals(bucketName)).findFirst();
    }

    /**
     * 根据bucketName删除信息
     *
     * @param bucketName bucket名称
     */
    public  void removeBucket(String bucketName) throws Exception {
        // 客户端
        MinioClient client = buildClient();
        client.removeBucket(RemoveBucketArgs.builder().bucket(bucketName).build());
    }

    /**
     * 获取文件外链
     *
     * @param bucketName bucket名称
     * @param objectName 文件名称
     * @param expires    过期时间 <=7
     * @return url
     */
    public static String getObjectURL(String bucketName, String objectName, Integer expires) throws Exception {
        // 客户端
        MinioClient client = buildClient();
        return client.getPresignedObjectUrl(GetPresignedObjectUrlArgs.builder().bucket(bucketName).object(objectName).expiry(expires).build());
    }

    /**
     * 获取文件
     *
     * @param bucketName bucket名称
     * @param objectName 文件名称
     * @return ⼆进制流
     */
    public static InputStream getObject(String bucketName, String objectName) throws Exception {
        // 客户端
        MinioClient client = buildClient();
        return client.getObject(GetObjectArgs.builder().bucket(bucketName).object(objectName).build());
    }

    /**
     * 上传文件
     *
     * @param bucketName bucket名称
     * @param objectName 文件名称
     * @param stream     文件流
     * @throws Exception https://docs.minio.io/cn/java-client-api-reference.html#putObject
     */
    public static void putObject(String bucketName, String objectName, InputStream stream) throws
            Exception {
        // 客户端
        MinioClient client = buildClient();
        client.putObject(PutObjectArgs.builder().bucket(bucketName).object(objectName).stream(stream, stream.available(), -1).contentType(objectName.substring(objectName.lastIndexOf("."))).build());
    }

    /**
     * 上传文件
     *
     * @param bucketName  bucket名称
     * @param objectName  文件名称
     * @param stream      文件流
     * @param size        ⼤⼩
     * @param contextType 类型
     * @throws Exception https://docs.minio.io/cn/java-client-api-reference.html#putObject
     */
    public static void putObject(String bucketName, String objectName, InputStream stream, long
            size, String contextType) throws Exception {
        // 客户端
        MinioClient client = buildClient();
        client.putObject(PutObjectArgs.builder().bucket(bucketName).object(objectName).stream(stream, size, -1).contentType(contextType).build());
    }

    /**
     * 获取文件信息
     *
     * @param bucketName bucket名称
     * @param objectName 文件名称
     * @throws Exception https://docs.minio.io/cn/java-client-api-reference.html#statObject
     */
    public static StatObjectResponse getObjectInfo(String bucketName, String objectName) throws Exception {
        // 客户端
        MinioClient client = buildClient();
        return client.statObject(StatObjectArgs.builder().bucket(bucketName).object(objectName).build());
    }

    /**
     * 删除文件
     *
     * @param bucketName bucket名称
     * @param objectName 文件名称
     * @throws Exception https://docs.minio.io/cn/java-client-apireference.html#removeObject
     */
    public static void removeObject(String bucketName, String objectName) throws Exception {
        // 客户端
        MinioClient client = buildClient();
        client.removeObject(RemoveObjectArgs.builder().bucket(bucketName).object(objectName).build());
    }
    //================================================文件部分============================================================================
    /**
     * 获取访问MINIO的客户端对象
     * @date 2023-11-10 10:20:48
     */
    public static MinioClient buildClient(){
        MinioClient minioClient =
                MinioClient.builder()
                        .endpoint(EnvCfgUtil.getValByCfgName("MINIO_API"))//publish
//                        .endpoint("http://node3:9000")//local
                        .credentials(EnvCfgUtil.getValByCfgName("MINIO_ACCESS_KEY"), EnvCfgUtil.getValByCfgName("MINIO_SECRET_KEY"))
                        .build();

        return minioClient;
    }


    /**
     * 上传本地图片 并把映射关系写入mysql
     * @param bucketName
     * @param sourceDir
     * @throws Exception
     */
    public static void uploadFiles(String bucketName, String sourceDir) throws Exception {
        File directory = new File(sourceDir);
        File[] files = directory.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    String newSourceDir = sourceDir + File.separator + file.getName();
                    uploadFiles(bucketName,newSourceDir);
                } else {
                    String filePath = file.getAbsolutePath();

                    try  {
                        InputStream inputStream = new FileInputStream(filePath);
                        MultipartFile multipartFile = new MockMultipartFile(file.getName(), file.getName(), MediaType.IMAGE_JPEG_VALUE,inputStream);

                        FileUploadResponse fileUploadResponse = uploadFile(multipartFile, bucketName);

                        String minioUrl = fileUploadResponse.getUrlPath();

                        //写入mysql
                        String upsertSQL = "INSERT INTO tmp_path_mapping(fromPath, toPath) VALUES(?, ?)";
                        NPQueryRunner.insert(upsertSQL,filePath,minioUrl);

                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }
        }
    }


    public static void main(String[] args) {
        //读取本地文件 上传到minio

//        String oid = uploadText("测试hi测试hi测试hi测试hi测试hi测试hi测试hi测试hi测试hi测试hi测试hi测试hi测试hi测试hi测试hi测试hi测试hi");
//        System.out.println("已上传:" + oid);
//        System.out.println("读取:" + readText(oid));

        //上传目录本地图片 并把映射关系写入mysql
        try {
            uploadFiles("pic","C:\\Users\\Bruce\\Pictures\\A");
        } catch (Exception e) {
            e.printStackTrace();
        }

        //上传一个单一文件到minio
//        try {
//            File file = new File("C:\\Users\\Bruce\\Pictures\\A\\1111.jpg");
//            InputStream inputStream = new FileInputStream(file);
//            MultipartFile multipartFile = new MockMultipartFile(file.getName(), file.getName(), MediaType.IMAGE_JPEG_VALUE,inputStream);
//
//            uploadFile(multipartFile,"pic");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }


    }
}
