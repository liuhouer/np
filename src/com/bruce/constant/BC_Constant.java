package com.bruce.constant;



public class BC_Constant {
	
	/** 课程分类  */
	public static enum CourseType {
		TUIJIAN("推荐课程",0),BENXIAO("本校课程",1),GONGGONG("公共课程",2);
	    
	    private final String key;  
	    private final int value;  
	    private CourseType(String key, int value) {  
	        this.key = key;  
	        this.value = value;  
	    }  
	   
	    public String getKey() {  
	        return key;  
	    }  
	    public int getValue() {  
	        return value;  
	    }  
	}
	
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
