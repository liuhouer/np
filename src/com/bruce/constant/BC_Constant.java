package com.bruce.constant;



public class BC_Constant {
	
	
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
