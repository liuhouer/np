package com.bruce.utils;

import java.io.UnsupportedEncodingException;
public class Base64Util{
	 public static String getBASE64(byte[] b) {
	  String s = null;
	  if (b != null) {
	   s = new sun.misc.BASE64Encoder().encode(b);
	  }
	  return s;
	 }
	 public static byte[] getFromBASE64(String s) {
	  byte[] b = null;
	  if (s != null) {
		  sun.misc.BASE64Decoder decoder = new sun.misc.BASE64Decoder();
	   try {
	    b = decoder.decodeBuffer(s);
	    return b;
	   } catch (Exception e) {
	    e.printStackTrace();
	   }
	  }
	  return b;
	 }
	 
	 public static String JIAMI(String str ) {
		  str = str +"000000";
		  String encodes = getBASE64(str.getBytes());
		  return encodes;
     }
	 
	 public static String JIEMI(String str) {
		  String decodes = new String(getFromBASE64(str));
		  decodes = decodes.substring(0, decodes.length()-6);
		  return decodes;
    }
	 
	 //加密机制  密码+000000 base64加密
	 //解密机制  base64解密 除去000000
	 public static void main(String[] args) throws UnsupportedEncodingException {
		 Base64Util bs = new Base64Util();
		    String s = "yang521xiao1314";
	        System.out.println("加密前：" + s);
	        System.out.println("加密后：" + JIAMI(s));
	        System.out.println("解密后：" + JIEMI(JIAMI(s)));
	 }
}
