package cn.northpark.utils;

import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import com.qiniu.util.StringMap;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

@Slf4j
public class QiniuUtils {

    private static final String BUCKET_NAME = "soft"; // 你的空間名稱

    /**
     * 私有构造函数
     */
    private QiniuUtils() {

    }

    /**
     * @author bruce
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
     *
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
    Auth auth = Auth.create(EnvCfgUtil.getValByCfgName("QINIU_ACCESS"), EnvCfgUtil.getValByCfgName("QINIU_SECRET"));

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

    BucketManager bucketManager = new BucketManager(auth, c);


    /**
     * //覆盖上传
     *
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
            log.info("token--->", token);
            //调用put方法上传
            Response res = uploadManager.put(FilePath, key, token);
            //打印返回的信息
            log.info(res.bodyString());
            rs = EnvCfgUtil.getValByCfgName("BUCKET_HOST_NAME") + (String) res.jsonToMap().get("key");
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
                log.error("" + e1);
            }
        }
        return rs;
    }


    /**
     * 删除指定文件
     *
     * @param key
     * @return
     * @throws IOException
     * @author bruce
     */
    public String remove(String key) throws IOException {

        //构造一个带指定Zone对象的配置类

        Configuration cfg = new Configuration(Zone.zone0());

        Auth auth = Auth.create(EnvCfgUtil.getValByCfgName("QINIU_ACCESS"), EnvCfgUtil.getValByCfgName("QINIU_SECRET"));

        BucketManager bucketManager = new BucketManager(auth, cfg);

        try {
            Response delete = bucketManager.delete(BUCKET_NAME, key);

            System.err.println(delete.statusCode + "");

            return delete.statusCode + "";

        } catch (QiniuException ex) {
        //如果遇到异常，说明删除失败

            ex.printStackTrace();

            System.err.println(ex.code());

            System.err.println(ex.response.toString());

        }

        return -1 + "";

    }


    public static void main(String[] args) throws IOException {

        //web图片上传到七牛

        //-------------开始--------------------------------

//		HashMap<String, String> map = HTMLParserUtil.webPic2Disk("http://www.sdifenzhou.com/wp-content/uploads/2016/02/Fantastical2.jpg", "D:\\BZ\\soft\\","20190724");
//
//		QiniuUtils.getInstance().upload(map.get("localpath"), map.get("key"));

        //-------------结束--------------------------------

        Set<String> list = new HashSet<>();
        list.add("movies/2021-02-07/a29a536a48719ac100fafd4cce156257-360x528xxx.jpg");
//        list.add("movies/2021-02-07/447f43f7fe89edd87f26a2aebc499aa0-360x528.jpg");
//        list.add("movies/2021-02-07/a29a536a48719ac100fafd4cce156257-360x528.jpg");
//        list.add("movies/2021-02-07/99756609d6969d1a26edc0a512122a53-360x528.jpg");
//        list.add("movies/2021-02-07/8b69a1a82f6ae0083dc88f066e1a1ead-360x528.jpg");
//        list.add("movies/2018-11-11/ae4bca044c8c243a0415a1d3b920a1a8.jpg");
//        list.add("movies/2021-02-07/ba25a7923551b71ebaea41b02ada3bc6-360x528.jpg");
//        list.add("movies/2021-02-07/p776580857.jpg");
//        list.add("movies/2021-02-07/eef3946b6b6847c1cbaa16497bdc7278-360x528.jpg");
//        list.add("movies/2021-02-07/2b63111277b245062176c9a45725fd25-360x528.jpg");
//        list.add("movies/2021-02-07/cdad4de96b538b1fcc7f92e3d6b76a7d-360x528.jpg");
//        list.add("movies/2021-02-07/4987fe36bc5719e8908d8799bc2e3766-360x528.jpg");
//        list.add("movies/2021-02-07/766b52a2e3e3cd74e84cc052f05b5912-360x528.jpg");
//        list.add("movies/2021-02-07/71be634c271bc640db4e59ca8834cff6-360x528.jpg");
//        list.add("movies/2021-02-07/0d07419f280e6c5d5ac4e55cf5360ae4-360x528.jpg");
//        list.add("movies/2021-02-07/31188f1250a1faa95d92a13ad4ba4365-360x528.jpg");
//        list.add("movies/2021-02-07/456badd4b8aa0d26a.jpg");
//        list.add("movies/2021-02-07/3d2d39879b552845f088bf3e57d0b05b-360x528.jpg");
//        list.add("movies/2021-02-07/a3d563a47d0595c4c6e8927a473a102d-360x528.jpg");
//        list.add("movies/2021-02-07/f62ee3c7c22d9f8dfb698736b02a5c14-360x528.jpg");
//        list.add("movies/2021-02-07/85f57c30cb16aa19fb1b310e45cd5dbd-360x360.jpg");
//        list.add("movies/2021-02-07/e0e341ead536187c5a0f54ebe034006d-360x355.jpg");
//        list.add("movies/2021-02-07/de8b469270fa24e91f9b2f6cdb59e65e-360x528.jpg");
//        list.add("movies/2021-02-07/caeba6a9095f36fc80ac1d60dd2f7c6d-360x528.jpg");
//        list.add("movies/2021-02-07/86519e632b026d24ee33f95a2c1d12d9-360x528.jpg");
//        list.add("movies/2021-02-07/05968c05c07a8be317be74059d26d247-360x528.jpg");
//        list.add("movies/2018-11-16/164647g9vvzbvo3rt8vs81.jpg");
//        list.add("movies/2021-02-07/baca73ca23ec0d509a916d81cd3c9e03-360x528.jpg");
//        list.add("movies/2021-02-07/006xTuHMgw1faq1ioa06gj30c00gotaq.jpg");
//        list.add("movies/2018-11-26/9a0b7990919db85eaa19a3acd86eff89-360x528.jpg");
//        list.add("movies/2021-02-07/156d70401a3cdcf6a445212402330fe2-360x528.jpg");
//        list.add("movies/2021-02-07/005GAtUNgw1exu8zw2dgbj30bs0goq4q.jpg");
//        list.add("movies/2021-02-07/e74cee3e8b7361483cdb73ec5ee4b950-360x528.jpg");
//        list.add("movies/2021-02-07/c045a73b84181f6c940503132124f381-360x528.jpg");
//        list.add("movies/2019-01-08/13ab2728222a336e8b0814e61893bd1b-360x528.jpg");
//        list.add("movies/2021-02-07/7a51666d6805a8f589b1d87c2a4692df-360x528.jpg");
//        list.add("movies/2021-02-07/ad4843c7138f151399346d066f569145-360x528.jpg");
//        list.add("movies/2021-02-07/dg54qm.png");
//        list.add("movies/2021-02-07/e6f73b3f28183b5baf6ec660a524e531-360x528.jpg");
//        list.add("movies/2021-02-07/5d7e6b15aa9b55f17c30b9d72d963e1d-360x528.jpg");
//        list.add("movies/2021-02-07/p2622495484.webp");
//        list.add("movies/2021-02-07/006xTuHMgw1f8e8ahz2quj30bt0gon0k.jpg");
//        list.add("movies/2021-02-07/006MsaXHgy1fqxcpssqyaj30b40goabu.jpg");
//        list.add("movies/2021-02-07/22d86313297de7a11.jpg");
//        list.add("movies/2018-11-26/57e482b40846da444da88eaddf076ec3-360x528.jpg");
//        list.add("movies/2021-02-07/9e706288gw1fawl6rx0vqj20b40fyabu.jpg");
//        list.add("movies/2019-10-17/5c539b1deb930ff97bed9ce288ad5997-360x528.jpg");
//        list.add("movies/2021-02-07/005GAtUNgw1ew5an4l41tj30bn0gojvi.jpg");
//        list.add("movies/2021-02-07/c322183116b1843a0a49fd3dfc517f05-360x528.jpg");
//        list.add("movies/2021-02-07/006SudKXgy1flbvptxphhj30bz0gojuj.jpg");
//        list.add("movies/2021-02-07/d2bcf5a5beb5c7e7eb8c7ece8ce5daf9-360x528.jpg");
//        list.add("movies/2018-11-26/9a000b9ddbe351ad8a860622c5a4b845-360x528.jpg");
//        list.add("movies/2021-02-07/7e68f3e14effbadf620d79a14ec32289-360x528.jpg");
//        list.add("movies/2018-11-18/57e482b40846da444da88eaddf076ec3-360x528.jpg");
//        list.add("movies/2018-11-18/a68d1bfa72de16238f0428863f2a966b.jpg");
//        list.add("movies/2021-02-07/006xTuHMgw1f9n5gqg6lej30d60gojtw.jpg");
//        list.add("movies/2021-02-07/b4a11cfd0afcf3f7870196c01c4c4f05-360x528.jpg");
//        list.add("movies/2021-02-07/e7122c8d0c7490-360x528.png");
//        list.add("movies/2021-02-07/2cf926d95740226241987f21c40e113d-360x528.jpg");
//        list.add("movies/2021-02-07/dad46b9c161aed760bbd2d5c33564971-300x169.jpg");
//        list.add("movies/2018-11-16/8d46594d8d1fb03fbdee2796b30c79c0.jpg");
//        list.add("movies/2021-02-07/20680e00819fcda17c41e59d8705b052-360x528.jpg");
//        list.add("movies/2021-02-07/b1915777c6a636295c1000f531824ca2-360x528.jpg");
//        list.add("movies/2021-02-07/a8d71d2cac951018f1ad10330bc06ecc-360x528.jpg");
//        list.add("movies/2021-02-07/135bdd4fd1ca27faa8cd93c15f433beb-360x528.jpg");
//        list.add("movies/2019-10-17/4850ab1aa07828a8566564c8250b0983-360x528.jpg");
//        list.add("movies/2021-02-07/496b0421d492c1f124fa0ce95e6de1c4-360x528.jpg");
//        list.add("movies/2021-02-07/6bfe8c40aa9d75d54973668c52fbffa7-360x528.jpg");
//        list.add("movies/2019-10-17/006xTuHMgw1f8byjccmdpj30bm0gogox.jpg");
//        list.add("movies/2021-02-07/90ee7bcd5dacbe19cd86f81a9d6fe9df-360x528.jpg");
//        list.add("movies/2018-11-26/5fcbad413ed8e93ff704e9f339e0d09b-360x528.jpg");
//        list.add("movies/2021-02-07/365eefb094d6bc2570300ee8d353ea88-360x528.jpg");
//        list.add("movies/2021-02-07/af9fb2862e138bc69b6fd9870ab265de-360x528.jpg");
//        list.add("movies/2021-02-07/c67290736c3f573958c8fe9d27192500-360x528.jpg");
//        list.add("movies/2021-02-07/7f8d9fcc9a584932b032ddc4dcee36c8-360x528.jpg");
//        list.add("movies/2021-02-07/9e706288gy1fekvbzghxtj20bj0goajl.jpg");
//        list.add("movies/2019-07-24/9e706288gw1f75behazj1j20fa0o7dh6.jpg");
//        list.add("movies/2021-02-07/2f4ca60bf5eb76b4f0693cdcd9c0e57a-360x528.jpg");
//        list.add("movies/2018-11-26/9db3f6afeee454-360x528.jpg");
//        list.add("movies/2021-02-07/a561b538ly1fy2skhnwdzj20td15o7fb.jpg");
//        list.add("movies/2021-02-07/e9dab2f166591bb9a25958812c0b8699-360x528.jpg");
//        list.add("movies/2019-10-17/b493d706361710e6d140ddf4380cc193-360x528.jpg");
//        list.add("movies/2021-02-07/6a82e6b26dd5102d8d9fe4efeda9514d-360x528.jpg");
//        list.add("movies/2021-02-07/5dc2e0c9aecc92f9eded81feb76cd2e2-360x528.jpg");
//        list.add("movies/2018-11-18/40d77a72b64751863aeeacebbe317652-360x528.jpg");
//        list.add("movies/2021-02-07/9e1597458e3682e513eb7fc627cfab1f-360x528.jpg");
//        list.add("movies/2021-02-07/47705636343f3d6c28ea4ede5afd51ad-360x528.jpg");
//        list.add("movies/2021-02-07/f4e0ae6ededa9443a7c08b7399733965-360x528.jpg");
//        list.add("movies/2018-11-18/0505758ac7f736efec9681d3bacad1c0-360x528.jpg");
//        list.add("movies/2021-02-07/8fe6bb7e3f4510-360x528.jpg");
//        list.add("movies/2021-02-07/1fa14a17d78aa1ab6cf74eb641a6c7c9-360x528.jpg");
//        list.add("movies/2018-11-26/9d9713604a60f1a4e5a573236754a9a3.jpg");
//        list.add("movies/2021-02-07/f988729b15236ee4dce9f8bdd0d82b88-360x528.jpg");
//        list.add("movies/2021-02-07/3016fcbfc32e2782c.jpg");
//        list.add("movies/2021-02-07/98a75f77f38aa3bb766b5fbcc83d8318-360x528.jpg");
//        list.add("movies/2021-02-07/08e86abfd7682f590c62a6538264c2d2-360x528.jpg");
//        list.add("movies/2021-02-07/314adcd67b2f36864a081391f86e0626-360x528.jpg");
//        list.add("movies/2018-11-16/7b573f26e5e056e8ccd00b4a54b80ea2.jpg");
//        list.add("movies/2021-02-07/605c3c5dc4fc0b9e17d85d3d811d7f37-360x528.jpg");
//        list.add("movies/2021-02-07/63ff157a3e6abdfbf584735827080140-360x528.jpg");
//        list.add("movies/2018-11-18/d90a54f472cfdfd59d5b08008752221b.jpg");
//        list.add("movies/2021-02-07/8dbc02e286c63b44c9ebca61ea5269e9-360x528.jpg");
//        list.add("movies/2021-02-07/0064rQEAgw1faagz4dfigj308c0cidh4.jpg");
//        list.add("movies/2021-02-07/5f7030bef072c9a5b8cd3745539022a7-360x528.jpg");
//        list.add("movies/2018-11-11/5fcbad413ed8e93ff704e9f339e0d09b-360x528.jpg");
//        list.add("movies/2021-02-07/b36899a93d9375f6caafd3e5a04ec3cf-360x528.jpg");
//        list.add("movies/2021-02-07/7740d78fd1dde2e3f18b3c00ba09317d-360x528.jpg");
//        list.add("movies/2021-02-07/9e706288gw1fay92wlhbzj20by0go0ww.jpg");
//        list.add("movies/2021-02-07/ece3a265ba7ed9e9880bb253217d4283-360x528.jpg");
//        list.add("movies/2021-02-07/044ae390c6436cb124612d571bfd91ba-360x528.jpg");
//        list.add("movies/2021-02-07/5867167b190a16c82356cbde496179e1-300x169.jpg");
//        list.add("movies/2019-07-24/timg");
//        list.add("movies/2018-11-26/9a19a3523de907900a1229dcd820ec6a.jpg");
//        list.add("movies/2021-02-07/a7627328dd4a1-360x528.jpg");
//        list.add("movies/2018-11-18/a5d0c4280a6628efe6799b3a8bd9a03a.jpg");
//        list.add("movies/2021-02-07/9e706288gw1fayrk2inm5j20bo0gojvi.jpg");
//        list.add("movies/2018-11-11/fd0c9ee56d920dc85080d5495d8be846.jpg");
//        list.add("movies/2021-02-07/57b2d3419ff9a49edc11a8d9fa531115-360x528.jpg");
//        list.add("movies/2021-02-07/43c84f53ca8b542d159b771753198af0-360x528.jpg");
//        list.add("movies/2019-01-08/78ba655b6944c609d702c6df90d72d3e-360x528.jpg");
//        list.add("movies/2018-11-26/0505758ac7f736efec9681d3bacad1c0-360x528.jpg");
//        list.add("movies/2021-02-07/5d291652b397362270741b6cdd3baf98-360x528.jpg");
//        list.add("movies/2021-02-07/e7ac32417ee0c072cc4838cdf05cb8a0-360x528.jpg");
//        list.add("movies/2021-02-07/c181b764ad88e5781b377c1276f3c5a0-360x528.jpg");
//        list.add("movies/2021-02-07/0b0347657e1e95ec72010bda38b6faed-360x528.jpg");
//        list.add("movies/2021-02-07/9e706288gy1fbx8qnlpcjj207g0am0t4.jpg");
//        list.add("movies/2018-11-11/6aa149f91720f61ee23ee96daa9d5bf2.jpg");
//        list.add("movies/2019-07-24/17736b8cc1aa1ee15a3a336501b644f3-360x528.jpg");
//        list.add("movies/2021-02-26/50fe2ce066a8df8abe9686e5d6d01da7-360x528.jpg");
//        list.add("movies/2021-02-07/006xTuHMgw1f88cbp94mgj30cy0go410.jpg");
//        list.add("movies/2021-02-07/c3a4b0b51bafa01ee4d4c1eab407ff07-360x528.jpg");
//        list.add("movies/2021-02-07/027f6be222564f441162fc9cbd58f46f-360x528.jpg");
//        list.add("movies/2021-02-07/2799fcb0e51d0417ff4ff386015dcfce-360x528.jpg");
//        list.add("movies/2018-11-12/d408a585b093b5ac988a1bb27f3c2d48-360x528.jpg");
//        list.add("movies/2021-02-07/21d801fee7f1146595e10584171a3495-360x528.jpg");
//        list.add("movies/2021-02-07/587be9159e37afed0653989a4a70125e-360x528.jpg");
//        list.add("movies/2021-02-07/006qnv8Ggw1f3zlrgmuobj30bp0f70ud.jpg");
//        list.add("movies/2021-02-07/04b599c2e7903f0cb27e8a2e2dce98f4-360x405.jpg");
//        list.add("movies/2021-02-07/bfbff8b5b0bf4fe9e5b24157cd0e2de5-360x528.jpg");
//        list.add("movies/2018-11-26/100913vlt8yr9brrs8udbl.jpg");
//        list.add("movies/2021-02-07/006SudKXgy1fks2dni4duj30ko0tqjuy.jpg");
//        list.add("movies/2021-02-07/006MsaXHgy1fveyl1fw1nj30dw0kuglz.jpg");
//        list.add("movies/2021-02-07/9e706288gy1fcc7901gkpj20b30gogo4.jpg");
//        list.add("movies/2021-02-07/46c5e58a0b40880006f125df29bee428-360x528.jpg");
//        list.add("movies/2021-02-07/4850ab1aa07828a8566564c8250b0983-360x528.jpg");
//        list.add("movies/2021-02-07/095ca52aa36034-360x528.jpg");
//        list.add("movies/2021-02-07/a92b56e913bd03dafd6ff254f283b7ee-360x528.jpg");
//        list.add("movies/2021-02-07/a795d6c49edb9315ef96c6c9afa1be18-360x528.jpg");
//        list.add("movies/2021-02-07/e0fab72df0d72a48a9dd3c7175d9bcac-360x528.jpg");
//        list.add("movies/2021-02-07/ff7128642895831a92364e05ebc8d10b-360x528.jpg");
//        list.add("movies/2021-02-07/f1f402bcb4e9bd296e8312a1790aae52-360x528.jpg");
//        list.add("movies/2021-02-07/53e58c127cf56ac11f2b9a0142f547b2-360x528.jpg");
//        list.add("movies/2018-11-18/001813aua16k48622qg628.jpg");
//        list.add("movies/2018-11-26/1fac55e406117dd30214c89e93e5ffda-360x528.jpg");
//        list.add("movies/2021-02-07/9e706288gw1fbg2mehch2j20bq0gojua.jpg");
//        list.add("movies/2021-02-07/ef84a0676f3eed2b615bfa1cf316f75e-360x528.jpg");
//        list.add("movies/2018-11-26/d408a585b093b5ac988a1bb27f3c2d48-360x528.jpg");
//        list.add("movies/2021-02-07/e3b6a7b2a73821f35161dfeae96a9333-360x528.jpg");
//        list.add("movies/2018-11-12/e4da3b7fbbce2345d7772b0674a318d5-1.jpg");
//        list.add("movies/2021-02-07/7bacb7da73662bb08e00307606fafd14-360x528.jpg");
//        list.add("movies/2021-02-07/4bb6def0abe27ffae42b6355b86ec227-360x528.jpg");
//        list.add("movies/2021-02-07/392033f969ebcb46eb0d70b2166c903a-360x528.jpg");
//        list.add("movies/2021-02-07/950db2ea014ea1ab355ea88455b7cbde-360x528.jpg");
//        list.add("movies/2019-01-21/4f95d18b88fda1faa4c6ae58547f6c74-360x528.jpg");
//        list.add("movies/2021-02-07/0064rQEAgw1fakhhh9dcmj308c0cbdgv.jpg");
//        list.add("movies/2021-02-07/131eecc5375d7c4bd77b76a42d790a30-360x528.jpg");
//        list.add("movies/2021-02-07/6ed3a25d329b366f27922bb5b308c46e-360x528.jpg");
//        list.add("movies/2021-02-07/d05f743d99449a5eb999571ba68e7acd-360x528.jpg");
//        list.add("movies/2021-02-07/189431a1dabaa4a72c8cc7c2777926e1-360x528.jpg");
//        list.add("movies/2021-02-07/9e60fc92c5520f764457255ff2b01fdc-360x528.jpg");
//        list.add("movies/2021-02-07/f2893dd04e8e2576cee50b862cbd4faa-360x528.jpg");
//        list.add("movies/2018-11-11/ec89edaa1e688e19fc053929083fb74d.jpg");
//        list.add("movies/2021-02-07/87e7e8123630e96a479a65aea6420571-300x169.jpg");
//        list.add("movies/2021-02-07/05185a2267462d00006ed5af7be0f1f9-360x528.jpg");
//        list.add("movies/2021-02-07/006MsaXHgy1fqnvz76hrmj30bs0godje.jpg");
//        list.add("movies/2018-11-18/001816tmj2hy20b20yzqbl.jpg");
//        list.add("movies/2018-11-11/4074c58a54b54b850956868c73301c1d.jpg");
//        list.add("movies/2018-11-12/1679091c5a880faf6fb5e6087eb1b2dc-1.jpg");
//        list.add("movies/2021-02-07/7fdadc7446e57c23fa4a3b045b3a868f-360x528.jpg");
//        list.add("movies/2021-02-07/ccb6894de781840a2b86f29e75329786-360x528.jpg");
//        list.add("movies/2021-02-07/13ab2728222a336e8b0814e61893bd1b-360x528.jpg");
//        list.add("movies/2021-02-07/9e380f1e42484408ff203f5a40f8d9f3-360x528.jpg");
//        list.add("movies/2018-11-16/1fac55e406117dd30214c89e93e5ffda-360x528.jpg");
//        list.add("movies/2019-10-17/006xTuHMgy1fgkrye2hcwj30dw0ipdh5.jpg");
//        list.add("movies/2018-11-12/eccbc87e4b5ce2fe28308fd9f2a7baf3-1.jpg");
//        list.add("movies/2021-02-07/007kZ47kly1gc6ojfd51fj30ku0txkh1.jpg");
//        list.add("movies/2019-10-17/7bacb7da73662bb08e00307606fafd14-360x528.jpg");
//        list.add("movies/2021-02-07/eef26774c5b67d478039979e049cca80-360x528.jpg");
//        list.add("movies/2021-02-07/NjI5T1JJUVpVL2dyOTI5ZURkSjZHcUx6SFlpYWVmTHRjaDFwMjAwSHZ6M0E0TXFPQmNZSWlRPT0.jpg");
//        list.add("movies/2018-11-11/fc56b4c207338cc2cf82111ed2509ab1.jpg");
//        list.add("movies/2021-02-07/a8f2a29de7e99ff880c5530ab4fd03ed-360x528.jpg");
//        list.add("movies/2021-02-07/d52a3431bdf6a5418e3e9e899173b078-360x528.jpg");
//        list.add("movies/2018-11-16/144833yqessgu1gpzm5qeg.jpg");
//        list.add("movies/2021-02-07/e62ad74cee8b35de51ba7adec954aa9c-360x528.jpg");
//        list.add("movies/2018-11-26/c5f451bce6ddff194784f42fe05e6987-360x528.jpg");
//        list.add("movies/2021-02-07/d80758ba1a7021398259f11f6156108b-2.jpg");
//        list.add("movies/2019-08-09/65f9cc5b57ff2846323ab35cb0f45909-360x528.jpg");
//        list.add("movies/2021-02-07/abcfeb9c0f125fa809b99a8edc472e2f-360x528.jpg");
//        list.add("movies/2019-07-24/c6141f3042cdfc271425a07486584da6-360x528.jpg");
//        list.add("movies/2021-02-07/f4fb496845b7cbedecba2eb304b6dd10-360x528.jpg");
//        list.add("movies/2018-12-05/20141020023409504263064.jpg");
//        list.add("movies/2021-02-07/12cfef31e18adaccffc001e8780f8ee9-360x528.jpg");
//        list.add("movies/2018-11-26/2419e773ffc834593e6548602eb29dbf-360x528.jpg");
//        list.add("movies/2021-02-07/45f1dc4cefada8494a12daff5122a48b-360x528.jpg");
//        list.add("movies/2021-02-07/a561b538ly1fvvy5xmyu1j20u016uwiz.jpg");
//        list.add("movies/2021-02-07/692f65a5d1b0533c2171bb6a8081219d-360x528.jpg");
//        list.add("movies/2021-03-01/08e86abfd7682f590c62a6538264c2d2-360x528.jpg");
//        list.add("movies/2018-11-26/823da37135236a2255568d326f38a67d.jpg");
//        list.add("movies/2018-11-26/69cd6a3a8da9ac02aa19c1c9055d39e3-360x528.jpg");
//        list.add("movies/2021-02-07/e3618347a474421ebef72c69e09a30a7-360x528.jpg");
//        list.add("movies/2018-11-16/fd4628bf51bf522261e247d80fa91627.jpg");
//        list.add("movies/2021-02-07/e1761490bf68ae6e3880ef96dd740af0-360x528.jpg");
//        list.add("movies/2018-11-12/a87ff679a2f3e71d9181a67b7542122c-1.jpg");
//        list.add("movies/2021-02-07/8f50ac8a403cb53019ccacd5b216b21a-360x528.jpg");
//        list.add("movies/2018-11-26/748647aa3c639d11f2f1071cb4a3e54d-360x528.jpg");
//        list.add("movies/2021-02-07/9e706288gw1f7b2dsaeu7j20bw0gojsx.jpg");
//        list.add("movies/2021-02-07/63f883cc9a6f96f5422f7ade56e62512-300x169.jpg");
//        list.add("movies/2021-02-07/553318dd8dd11e52aa84e747d5636090-360x528.jpg");
//        list.add("movies/2019-02-09/bed53b8364cc48037a61fa7292fa76b7-360x528.jpg");
//        list.add("movies/2019-10-17/baca73ca23ec0d509a916d81cd3c9e03-360x528.jpg");
        for (String key : list) {
            try {
                QiniuUtils.getInstance().remove(key);
            }catch (Exception e){
                e.printStackTrace();
                continue;
            }

        }
    }
}
