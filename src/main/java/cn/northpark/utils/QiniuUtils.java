package cn.northpark.utils;

import java.io.IOException;

import org.apache.log4j.Logger;

import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;

public class QiniuUtils {
    private static final Logger LOGGER = Logger
            .getLogger(QiniuUtils.class);
    public static QiniuUtils getInstance = new QiniuUtils();

    public static final String ACCESS_KEY = "HYyj45Ad7f68EsFWVhZuc6qvu1ydgd-CEzFiyabI"; // 你的access_key
    public static final String SECRET_KEY = "uQqRoiPSk4414gIAdCJdXynjDHYRAT8AecVjubyh"; // 你的secret_key
    public static final String BUCKET_NAME = "soft"; // 你的空間名稱

    private static final String BUCKET_HOST_NAME = "http://ognunbb4i.bkt.clouddn.com/";


    /*
     * try {
     *
     * // 要求url可公网正常访问BucketManager.fetch(url, bucketName, key); // @param url
     * 网络上一个资源文件的URL // @param bucketName 空间名称 // @param key 空间内文件的key[唯一的]
     * DefaultPutRet putret = bucketManager.fetch(originalUrl, BUCKET_NAME,
     * "testimage");
     *
     * LOGGER.info(putret.key);
     * LOGGER.info("succeed upload image"); } catch (QiniuException e1) {
     * e1.printStackTrace(); }
     */

    //上传到七牛后保存的文件名
    String key = "my-java.png";
    //上传文件的路径
    String FilePath = "/.../...";

    //密钥配置
    Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);

    ///////////////////////指定上传的Zone的信息//////////////////
    //第一种方式: 指定具体的要上传的zone
    //注：该具体指定的方式和以下自动识别的方式选择其一即可
    //要上传的空间(bucket)的存储区域为华东时
    // Zone z = Zone.zone0();
    //要上传的空间(bucket)的存储区域为华北时
    // Zone z = Zone.zone1();
    //要上传的空间(bucket)的存储区域为华南时
    // Zone z = Zone.zone2();

    //第二种方式: 自动识别要上传的空间(bucket)的存储区域是华东、华北、华南。
    Zone z = Zone.autoZone();
    Configuration c = new Configuration(z);

    //创建上传对象
    UploadManager uploadManager = new UploadManager(c);


    //简单上传，使用默认策略，只需要设置上传的空间名就可以了
    public String getUpToken() {
        return auth.uploadToken(BUCKET_NAME);
    }

    public String upload(String FilePath, String key) throws IOException {
        String rs = "";
        try {
            //调用put方法上传
            Response res = uploadManager.put(FilePath, key, getUpToken());
            //打印返回的信息
            LOGGER.info(res.bodyString());
            rs = BUCKET_HOST_NAME + (String) res.jsonToMap().get("key");
            LOGGER.info("rs===" + rs);
        } catch (QiniuException e) {
            Response r = e.response;
            // 请求失败时打印的异常的信息
            LOGGER.error(r.toString());
            try {
                //响应的文本信息
                LOGGER.error(r.bodyString());
            } catch (QiniuException e1) {
                //ignore
                LOGGER.error(e1);
            }
        }
        return rs;
    }


    public static void main(String[] args) throws IOException {

        //web图片上传到七牛

        //-------------开始--------------------------------

//		HashMap<String, String> map = HTMLParserUtil.webPic2Disk("http://www.sdifenzhou.com/wp-content/uploads/2016/02/Fantastical2.jpg", "D:\\BZ\\soft\\");
//		
//		QiniuUtils.getInstance.upload(map.get("localpath"), map.get("key"));

        //-------------结束--------------------------------


    }
}
