package cn.northpark.constant;

public class BC_Constant {

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
        SHIZI("狮子座", 5), CHUNV("处女座",6), TIANCHENG("天秤座", 7), TIANXIE("天蝎座", 8),
        SHESHOU("射手座", 9), MOJIE("摩羯座", 10), SHUIPING("水瓶座", 11), SHUANGYU("双鱼座", 12)
        
        ;
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
     * 爬虫美剧资源
     */
    public static final String RET_meiju = "http://m.soshy.cn/movie_bt_series/meiju/page/";
    /**
     * 爬虫电影资源
     */
    public static final String RET_dianying = "http://m.soshy.cn/movie_bt_series/movie/page/";
    /**
     * 爬虫动漫源
     */
    public static final String RET_dongman = "http://m.soshy.cn/movie_bt_series/dongman/page/";

    /**
     * 爬虫国产剧
     */
    public static final String RET_guochanju = "http://m.soshy.cn/movie_bt_series/guochanju/page/";


    /**
     * xiao 7博客
     */
    public static final String Coupon_VPS_7 = "https://www.xqblog.com/category/vps/page/";

    /**
     * 如有乐享
     */
//    public static final String Coupon_VPS_ruyo = "http://51.ruyo.net/page/";

    /**
     * Leonn 的博客
     */
//    public static final String Coupon_VPS_Leonn = "https://liyuans.com/page/";

    
    
    
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

}
