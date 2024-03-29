package cn.northpark.constant;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;

import java.util.Properties;
import java.util.ResourceBundle;

public class BC_Constant {

    public static ImmutableMap specColorMap =
            ImmutableMap.builder()
                    .put("《", "")
                    .put("》", "")
                    .put("<", "")
                    .put("#", "")
                    .put("【", "")
                    .put("】", "")
                    .put("[", "")
                    .put("]", "")
                    .put("“", "")
                    .put("”", "")
                    .put("?", "")
                    .put(".", "")
                    .put("(", "")
                    .put(")", "")
                    .put("�", "")
                    .put("|", "")
                    .put("^", "")
                    .put("{", "")
                    .put("}", "")
                    .put("⑹", "")
                    .put("@", "")
                    .put("`", "")
                    .put("_", "")
                    .put("~", "")
                    .put("ε", "")
                    .put("*","")
                    .put("-", "")
                    .put("/", "")
                    .put("ｂ", "b")
                    .put("ｄ", "d")
                    .put("ｍ", "m")
                    .put("ｏ", "o")
                    .put("ρ","s")
                    .put("σ","s")
                    .put("χ","s")
                    .put("д","s")
                    .put("т","s")
                    .put("∀","s")
                    .put("─","s")
                    .put("「","s")
                    .put("あ","s")
                    .put("い","s")
                    .put("う","s")
                    .put("お","s")
                    .put("か","s")
                    .put("き","s")
                    .put("ぎ","s")
                    .put("く","s")
                    .put("け","s")
                    .put("こ","s")
                    .put("さ","s")
                    .put("す","s")
                    .put("せ","s")
                    .put("た","s")
                    .put("ち","s")
                    .put("ど","s")
                    .put("な","s")
                    .put("に","s")
                    .put("の","s")
                    .put("は","s")
                    .put("ば","s")
                    .put("ひ","s")
                    .put("ふ","s")
                    .put("ぶ","s")
                    .put("ほ","s")
                    .put("ま","s")
                    .put("も","s")
                    .put("り","s")
                    .put("れ","s")
                    .put("ア","s")
                    .put("イ","s")
                    .put("エ","s")
                    .put("オ","s")
                    .put("カ","s")
                    .put("ガ","s")
                    .put("キ","s")
                    .put("ク","s")
                    .put("コ","s")
                    .put("サ","s")
                    .put("ザ","s")
                    .put("シ","s")
                    .put("セ","s")
                    .put("タ","s")
                    .put("ダ","s")
                    .put("チ","s")
                    .put("ッ","s")
                    .put("ツ","s")
                    .put("テ","s")
                    .put("デ","s")
                    .put("ト","s")
                    .put("ド","s")
                    .put("ヌ","s")
                    .put("ネ","s")
                    .put("ノ","s")
                    .put("ハ","s")
                    .put("バ","s")
                    .put("フ","s")
                    .put("ヘ","s")
                    .put("ボ","s")
                    .put("ポ","s")
                    .put("マ","s")
                    .put("メ","s")
                    .put("モ","s")
                    .put("ヤ","s")
                    .put("ラ","s")
                    .put("リ","s")
                    .put("ル","s")
                    .put("レ","s")
                    .put("凪","s")
                    .put("가","s")
                    .put("굿","s")
                    .put("올","s")
                    .put("왕","s")
                    .put("장","s")
                    .put("し","s")
                    .build();

    public static final String Domain = "northpark.cn";

    /**
     * 用户注册邮件
     */
    public static final String MQ_MAIL_JOIN = "mail.join";

    /**
     * 用户重置邮件
     */
    public static final String MQ_MAIL_RESET = "mail.reset";

    /**
     * 用户重置邮件
     */
    public static final String REDIS_FEEDBACK = "R_FEEDBACK";


    /**
     * redis返回类型自定义
     */
    public enum RedisReturnType {
        map("map"),
        listmap("listmap"),
        string("string"),
        listclass("listclass"),
        ;
        // 成员变量
        private String type;


        private RedisReturnType(String type) {
            this.type = type;
        }


        public String getType() {
            return type;
        }


    }


    /**
     * 格式化分隔符
     */
    public enum FormatSpilt {
        hengxian("-"),
        bolangxian("~"),
        xinghao("*"),
        jinghao("#"),
        ;
        // 成员变量
        private String namestr;


        private FormatSpilt(String namestr) {
            this.namestr = namestr;
        }


        public String getNamestr() {
            return namestr;
        }


    }

    /**
     * 星座
     */
    public enum XINGZUO {
        BAIYANG("白羊座", 1), JINNIU("金牛座", 2), SHUANGZI("双子座", 3), JUXIE("巨蟹座", 4),
        SHIZI("狮子座", 5), CHUNV("处女座", 6), TIANCHENG("天秤座", 7), TIANXIE("天蝎座", 8),
        SHESHOU("射手座", 9), MOJIE("摩羯座", 10), SHUIPING("水瓶座", 11), SHUANGYU("双鱼座", 12);
        // 成员变量
        private String name;
        private int index;

        // 构造方法
        private XINGZUO(String name, int index) {
            this.name = name;
            this.index = index;
        }

        // 普通方法
        public static String getName(int index) {
            for (XINGZUO c : XINGZUO.values()) {
                if (c.getIndex() == index) {
                    return c.name;
                }
            }
            return null;
        }

        // get set 方法
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getIndex() {
            return index;
        }

        public void setIndex(int index) {
            this.index = index;
        }
    }


    /**
     * 河马BT影视站
     */
    public enum HEMA_BT {

        //sq
        SQ("/bttype/93-", "qingse", "情色"),
        //鬼父
        GUIFU("/search.asp?searchword=%B9%ED%B8%B8&searchtype=-1&ordertype=&TypeId=&page=", "qingse,dongman", "情色,动漫"),

        ;
        // 成员变量
        private String name;
        private String tag_code;
        private String tag;

        // 构造方法
        private HEMA_BT(String name) {
            this.name = name;
        }

        private HEMA_BT(String name, String tag_code, String tag) {
            this.name = name;
            this.tag_code = tag_code;
            this.tag = tag;
        }

        public String getName() {
            return name;
        }

        public String gettag_code() {
            return tag_code;
        }

        public String getTag() {
            return tag;
        }
    }

    /**
     * 爬虫美剧资源
     */
    public static final String RET_meiju = "http://m.fangpao.wang/movie_bt_series/meiju/page/";
    /**
     * 爬虫电影资源
     */
    public static final String RET_dianying = "http://m.fangpao.wang/movie_bt_series/movie/page/";


    /**
     * SQ电影
     */
    public static final String RET_SQ_MOVIE = "http://m.fangpao.wang/movie_bt/movie_bt_tags/%e6%83%85%e8%89%b2/page/";

    /**
     * 爬虫动漫源
     */
    public static final String RET_dongman = "http://m.fangpao.wang/movie_bt_series/dongman/page/";

    /**
     * 爬虫国产剧
     */
    public static final String RET_guochanju = "http://m.fangpao.wang/movie_bt_series/guochanju/page/";

    /**
     * 人人电影
     * -迅雷
     * -迅雷网盘
     * -百度网盘
     */
    public static final String RET_RR_MOVIES = "https://www.rrdynb.com/movie/list_2_";

    /**
     * 人人电影
     * -电视剧
     */
    public static final String RET_RR_TV = "https://www.rrdynb.com/dianshiju/list_6_";

    /**
     * 人人电影
     * -老电影
     */
    public static final String RET_RR_OLDMAN = "https://www.rrdynb.com/zongyi/list_10_";

    /**
     * 人人电影
     * -动漫
     */
    public static final String RET_RR_DONGMAN = "https://www.rrdynb.com/dongman/list_13_";


    /**
     * 人人电影 domain
     */
    public static final String RET_RR_BASE = "https://www.rrdynb.com/";


    /**
     * 忽略的图片地址
     */
    public static ImmutableList<String> ignore_pic_list = ImmutableList.of(
            "https://bbs.djicdn.com/data/attachment/album/202011/18/095815gkkv3xxks13max3c.jpg",
            "https://bbs.djicdn.com/data/attachment/album/202011/18/100025slp1xil1xptpxhml.jpg"

    );

    //northPark 云盘下载-静态图片
    public static final String np_cloud_down = "https://northpark.cn/statics/img/down/cloud.jpg";

    //northPark 迅雷下载-静态图片
    public static final String np_thunder_down = "https://northpark.cn/statics/img/down/thunder.jpg";

    /**
     * 根据不同系统获取：图片配置的开始目录
     */
    public static String getPicStartByOs() {
        ResourceBundle bundle = ResourceBundle.getBundle("env-config");

        if (bundle == null) {
            throw new IllegalArgumentException("env-config.properties!");
        }
        Properties prop = System.getProperties();
        String os = prop.getProperty("os.name");
        if (os.startsWith("win") || os.startsWith("Win")) {// windows操作系统

            return bundle.getString("winPicStart");

        } else if (os.startsWith("mac") || os.startsWith("Mac")) {// mac操作系统

            return bundle.getString("macPicStart");

        } else {  //linux

            return bundle.getString("linuxPicStart");
        }

    }

    /**
     * 根据不同系统获取：文件传输起始路径
     */
    public static String getFileStartByOs() {
        ResourceBundle bundle = ResourceBundle.getBundle("env-config");

        if (bundle == null) {
            throw new IllegalArgumentException("env-config.properties!");
        }
        Properties prop = System.getProperties();
        String os = prop.getProperty("os.name");
        if (os.startsWith("win") || os.startsWith("Win")) {// windows操作系统

            return bundle.getString("winFileStart");

        } else if (os.startsWith("mac") || os.startsWith("Mac")) {// mac操作系统

            return bundle.getString("macFileStart");

        } else {  //linux

            return bundle.getString("linuxFileStart");
        }

    }

    //==========================================================微信==============================================================================================

//    public static String WX_url_menu_create = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";
//
//    public static String WX_url_get_access_token = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
//
//    public static String WX_url_get_media = "https://api.weixin.qq.com/cgi-bin/media/get?access_token=ACCESS_TOKEN&media_id=MEDIA_ID";
//
//    public static String WX_url_get_ticket_jsapi = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=ACCESS_TOKEN&type=jsapi";
//
//    public static String WX_url_base_shouquan_page = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=APP_ID&redirect_uri=REDIRECT_URI&response_type=code&scope=snsapi_base&state=1#wechat_redirect";
//
//    public static String WX_url_oauth2 = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APP_ID&secret=APP_SECRET&code=CODE&grant_type=authorization_code";
//
//    public static String WX_url_getUser = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";
//
//    public static final String weixin_hosts = "http://m.lvzheng.com";//"http://www.lvzheng.com";
//
//    public static final String TOKEN = "his8Zzjzweizhan1025";
//    public static final String bd_prefix = "DHBD";
//    public static final String weixin_app_id = "";
//    public static final String weixin_app_secret_id = "";//"d406ed53dc1d4984323eba33a7f18571";//"07a6dd9789e28772f6de32a2ec057fc0";//
//    public static final String weixin_app_name = "";//"gh_f5c1ef705fad";//"gh_944897e71947";//
//    public static final String coupon_module_id = "";  //"20ZZj0K2uaIYaWD0vA7dMFkacME804FkWlYYiorsyPE"
//
//    public static Map<Integer, Long> scend_id_map = new HashMap<Integer, Long>();

    //==========================================================微信==============================================================================================

    public static void main(String[] args) {
        boolean contains = BC_Constant.ignore_pic_list.contains("https://bbs.djicdn.com/data/attachment/album/202011/18/100025slp1xil1xptpxhml.jpg");
        System.err.println(contains);
    }
}
