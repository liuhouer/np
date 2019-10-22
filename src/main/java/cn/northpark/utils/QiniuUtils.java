package cn.northpark.utils;

import java.io.IOException;
import java.util.HashMap;

import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import com.qiniu.util.StringMap;

import lombok.extern.slf4j.Slf4j;
@Slf4j
public class QiniuUtils {

	private static final String ACCESS_KEY = "HYyj45Ad7f68EsFWVhZuc6qvu1ydgd-CEzFiyabI"; // 你的access_key
	private static final String SECRET_KEY = "uQqRoiPSk4414gIAdCJdXynjDHYRAT8AecVjubyh"; // 你的secret_key
	private static final String BUCKET_NAME = "soft"; // 你的空間名稱

    private static final String BUCKET_HOST_NAME = "http://qiniupic.memorymaple.com/";
    
    
    /**
     * 私有构造函数
     */
    private QiniuUtils(){
    	
    }
    
    /**
     * @author w_zhangyang
     * 枚举模式的单例模式，归并高并发的风险
     */
    public enum Singleton {
    	INSTANCE;
    	private QiniuUtils singleton;
    	
    	/**
    	 * 构造函数 
    	 */
    	Singleton() {
    		singleton = new QiniuUtils();
    	}
    	
    	
    	public QiniuUtils getInstance() {
            return singleton;
        }
    }
    
    
   
    /**
     * 获取一个唯一的实例
     * @return
     */
    public static QiniuUtils getInstance() {
        return Singleton.INSTANCE.getInstance();
    }
    
    

    /*
     * try {
     *
     * // 要求url可公网正常访问BucketManager.fetch(url, bucketName, key); // @param url
     * 网络上一个资源文件的URL // @param bucketName 空间名称 // @param key 空间内文件的key[唯一的]
     * DefaultPutRet putret = bucketManager.fetch(originalUrl, BUCKET_NAME,
     * "testimage");
     *
     * log.info(putret.key);
     * log.info("succeed upload image"); } catch (QiniuException e1) {
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


   
    
    /**
     *  //覆盖上传
     * @param key
     * @return
     */
    public String getUpToken(String key) {
    	//insertOnly 如果希望只能上传指定key的文件，并且不允许修改，那么可以将下面的 insertOnly 属性值设为 1
        return auth.uploadToken(BUCKET_NAME, key, 3600, new StringMap().put("insertOnly", 0));
    }

    public String upload(String FilePath, String key) throws IOException {
        String rs = "";
        try {
        	String token = getUpToken(key);//获取 token
        	log.info("token--->",token);
            //调用put方法上传
            Response res = uploadManager.put(FilePath, key, token);
            //打印返回的信息
            log.info(res.bodyString());
            rs = BUCKET_HOST_NAME + (String) res.jsonToMap().get("key");
            log.info("rs===" + rs);
        } catch (QiniuException e) {
            Response r = e.response;
            // 请求失败时打印的异常的信息
            log.error(r.toString());
            try {
                //响应的文本信息
                log.error(r.bodyString());
            } catch (QiniuException e1) {
                //ignore
                log.error(""+e1);
            }
        }
        return rs;
    }


    public static void main(String[] args) throws IOException {

        //web图片上传到七牛

        //-------------开始--------------------------------

		HashMap<String, String> map = HTMLParserUtil.webPic2Disk("http://www.sdifenzhou.com/wp-content/uploads/2016/02/Fantastical2.jpg", "D:\\BZ\\soft\\","20190724");
		
		QiniuUtils.getInstance().upload(map.get("localpath"), map.get("key"));

        //-------------结束--------------------------------


    }
}
