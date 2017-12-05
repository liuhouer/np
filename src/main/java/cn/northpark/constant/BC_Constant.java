package cn.northpark.constant;


public class BC_Constant {
	
	public static final String Domain = "northpark.cn";
	
	/**
	 * 爬虫电视剧资源
	 */
	public static final String RET_dianshiju = "http://www.vip588660.com/category/dianshiju/page/";
	/**
	 * 爬虫电影资源
	 */
	public static final String RET_dianying = "http://www.vip588660.com/category/movie/page/";
	/**
	 * 爬虫动漫源
	 */
	public static final String RET_dongman = "http://www.vip588660.com/category/dongman/page/";
	
	
	
	/**
	 * xiao 7博客
	 */
	public static final String Coupon_VPS_7 = "https://www.xqblog.com/category/vps/page/";
	
	/**
	 * 如有乐享
	 */
	public static final String Coupon_VPS_ruyo = "http://51.ruyo.net/page/";
	
	/**
	 * Leonn 的博客
	 */
	public static final String Coupon_VPS_Leonn = "https://liyuans.com/page/";
	
	
	/** 是否启用*/
	public static enum Enabled {
		ENABLED("是",1),DISENABLED("否",0);
	    
	    private final String key;  
	    private final int value;  
	    private Enabled(String key, int value) {  
	        this.key = key;  
	        this.value = value;  
	    }  
	    public static String getKey(int value) {  
	        for (Pubilc c : Pubilc.values()) {  
	            if (c.getValue() == value) {  
	                return c.key;  
	            }  
	        }  
	        return null;  
	    }  
	    public String getKey() {  
	        return key;  
	    }  
	    public int getValue() {  
	        return value;  
	    }  
	}
	
	
	/** 公共状态 */
	public static enum Pubilc {
		ENABLED("启用",1),DISABLE("禁用",0);
	    
	    private final String key;  
	    private final int value;  
	    private Pubilc(String key, int value) {  
	        this.key = key;  
	        this.value = value;  
	    }  
	    public static String getKey(int value) {  
	        for (Pubilc c : Pubilc.values()) {  
	            if (c.getValue() == value) {  
	                return c.key;  
	            }  
	        }  
	        return null;  
	    }  
		public int getValue() {
			return value;
		}
		public String getKey() {
			return key;
		}  
	}
	
	
}
