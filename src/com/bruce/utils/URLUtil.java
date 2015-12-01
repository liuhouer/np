package com.bruce.utils;

import java.security.MessageDigest;

/**
时间工具类
@import test Long url To short url
@author zhangyang
*
*/
public class URLUtil {

	private final static String[] hexDigits = {
	      "0", "1", "2", "3", "4", "5", "6", "7", 
	      "8", "9", "a", "b", "c", "d", "e", "f"}; 

	    public static String byteArrayToHexString(byte[] b){
	        StringBuffer resultSb = new StringBuffer();
	        for (int i = 0; i < b.length; i++){
	            resultSb.append(byteToHexString(b[i]));
	        }
	        return resultSb.toString();
	    }

	    private static String byteToHexString(byte b){
	        int n = b;
	        if (n < 0)
	            n = 256 + n;
	        int d1 = n / 16;
	        int d2 = n % 16;
	        return hexDigits[d1] + hexDigits[d2];
	    }

	    public static String MD5Encode(String origin){
	        String resultString = null;
	            try {

	                resultString=new String(origin);
	                MessageDigest md = MessageDigest.getInstance("MD5");

	                resultString.trim();

	                resultString=byteArrayToHexString(md.digest(resultString.getBytes("UTF-8")));
	            }catch (Exception ex){}
	            return resultString;
	        }

	public static void main(String[] args) { 
	    String url = "http://www.mlvzheng.com"; 
	    for (String string : ShortText(url)) { 
	        print(string); 
	    } 
	} 

	public static String[] ShortText(String string){ 
	    String key = "XuLiang";                 //自定义生成MD5加密字符串前的混合KEY 
	    String[] chars = new String[]{          //要使用生成URL的字符 
	        "a","b","c","d","e","f","g","h", 
	        "i","j","k","l","m","n","o","p", 
	        "q","r","s","t","u","v","w","x", 
	        "y","z","0","1","2","3","4","5", 
	        "6","7","8","9","A","B","C","D", 
	        "E","F","G","H","I","J","K","L", 
	        "M","N","O","P","Q","R","S","T", 
	        "U","V","W","X","Y","Z" 
	    }; 

	    String hex = MD5Encode(key + string); 
	    int hexLen = hex.length(); 
	    int subHexLen = hexLen / 8; 
	    String[] ShortStr = new String[4]; 

	    for (int i = 0; i < subHexLen; i++) { 
	        String outChars = ""; 
	        int j = i + 1; 
	        String subHex = hex.substring(i * 8, j * 8); 
	        long idx = Long.valueOf("3FFFFFFF", 16) & Long.valueOf(subHex, 16); 

	        for (int k = 0; k < 6; k++) { 
	            int index = (int) (Long.valueOf("0000003D", 16) & idx); 
	            outChars += chars[index]; 
	            idx = idx >> 5; 
	        } 
	        ShortStr[i] = outChars; 
	    } 

	    return ShortStr; 
	} 

	public static void print(Object messagr){ 
	    System.out.println(messagr);
	} 
}
