package cn.northpark.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashSet;
import java.util.Set;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

/**
 * @author zhangyang
 *
 */
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
	 
	 
	 /** 
	  * Member cache 文件解压处理 
	  *  
	  * @param buf 
	  * @return 
	  * @throws IOException 
	  */  
	 public static byte[] unGzip(byte[] buf) throws IOException {  
	  GZIPInputStream gzi = null;  
	  ByteArrayOutputStream bos = null;  
	  try {  
	   gzi = new GZIPInputStream(new ByteArrayInputStream(buf));  
	   bos = new ByteArrayOutputStream(buf.length);  
	   int count = 0;  
	   byte[] tmp = new byte[2048];  
	   while ((count = gzi.read(tmp)) != -1) {  
	    bos.write(tmp, 0, count);  
	   }  
	   buf = bos.toByteArray();  
	  } finally {  
	   if (bos != null) {  
	    bos.flush();  
	    bos.close();  
	   }  
	   if (gzi != null)  
	    gzi.close();  
	  }  
	  return buf;  
	 }  
	 
	 /**
	  * 
	  * @param value
	  * @return
	  */
	 public static byte[] intToBytes(int value)   
	 {   
	     byte[] src = new byte[4];  
	     src[0] = (byte) ((value>>24) & 0xFF);  
	     src[1] = (byte) ((value>>16)& 0xFF);  
	     src[2] = (byte) ((value>>8)&0xFF);    
	     src[3] = (byte) (value & 0xFF);       
	     return src;  
	 }  
	 
	 public static int bytesToInt(byte[] src, int offset) {  
		    int value;    
		    value = (int) ((src[offset] & 0xFF)   
		            | ((src[offset+1] & 0xFF)<<8)   
		            | ((src[offset+2] & 0xFF)<<16)   
		            | ((src[offset+3] & 0xFF)<<24));  
		    return value;  
	}  
	 
	 /** 
	  * Member cache 文件压缩处理 
	  *  
	  * @param val 
	  * @return 
	  * @throws IOException 
	  */  
	 public static byte[] gzip(byte[] val) throws IOException {  
	  ByteArrayOutputStream bos = new ByteArrayOutputStream(val.length);  
	  GZIPOutputStream gos = null;  
	  try {  
	   gos = new GZIPOutputStream(bos);  
	   gos.write(val, 0, val.length);  
	   gos.finish();  
	   gos.flush();  
	   bos.flush();  
	   val = bos.toByteArray();  
	  } finally {  
	   if (gos != null)  
	    gos.close();  
	   if (bos != null)  
	    bos.close();  
	  }  
	  return val;  
	 }  
	 
	 /**  
	    * byte数组中取int数值，本方法适用于(低位在后，高位在前)的顺序。和intToBytes2（）配套使用 
	    */  
	public static int bytesToInt2(byte[] src, int offset) {  
	    int value;    
	    value = (int) ( ((src[offset] & 0xFF)<<24)  
	            |((src[offset+1] & 0xFF)<<16)  
	            |((src[offset+2] & 0xFF)<<8)  
	            |(src[offset+3] & 0xFF));  
	    return value;  
	}  
	
	
	
	public static Set<Integer> m = new HashSet<Integer>();
	/**
	 * 生成6位int  ID
	 * @return
	 */
	public static int getInt6(Integer min) {
		int a = 0;
			do {
				a = (int) (Math.random() * 10000+min);
			} while (m.contains(a));
			m.add(a);
			System.out.println(a);
		return a;
	}
	  
	 //加密机制  密码+000000 base64加密
	 //解密机制  base64解密 除去000000
	 public static void main(String[] args) throws UnsupportedEncodingException {
		 Base64Util bs = new Base64Util();
//		 System.out.println(bs.JIAMI("bruce134"));
		    String s = "UFI5NDMzNDMwMDAwMDA=";
	        System.out.println("加密前：" + s);
	        System.out.println("加密后：" + JIAMI(s));
	        System.out.println("解密后：" + JIEMI(s));

	 }
}
