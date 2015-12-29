package com.bruce.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
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
	  
	 //加密机制  密码+000000 base64加密
	 //解密机制  base64解密 除去000000
	 public static void main(String[] args) throws UnsupportedEncodingException {
		 Base64Util bs = new Base64Util();
		 System.out.println(bs.JIAMI("13483724051"));
//		    String s = "yang521xiao1314";
//	        System.out.println("加密前：" + s);
//	        System.out.println("加密后：" + JIAMI(s));
//	        System.out.println("解密后：" + JIEMI(JIAMI(s)));
		 
//		 String a = "H4sIAAAAAAAAA6VRybKiQBC88xXvPofpBhE4zKEaBRFRG2xAbjQNiGwaLghfPxgx"+
//					"fzB5qIrKyKglK1WypZrJhraQNVQIZCh5rqcGL/Q01wTiPylKkY6KhZwvC6xwJZXF"+
//					"nJUCaQoyVCF+loYuVJxiQ+gplnGWqzpeGIbQuIbwcqlLf/4TUsU+6WmAvXnp3fUK"+
//					"SgoNwFAfPAADvrCSxj+IKGGnDsxfmJzQlwznQHb+b09KYmickXxEFE7Cbt688148"+
//					"oq5ZeVfH2pRQgmOW/WdXEZbarDxH6iTkcHQ2/tuxPzcpadljLjwoP5i3jZzE2ykJ"+
//					"hq+wduxwD3HtEApTGqndcejHuZHDlazkkfXKA9JJWWs9s5G8k4pMXAkfs2iaRSVF"+
//					"xoEii7lr/yJs9vK77ZuzZ5PH4p21tHdqYYcjmaRkXimGHkhA6nNcV26g2lzZPs/x"+
//					"V4T3iU1nbiivQ3KYJ3fnryu2/+SBc3MjUkhWRVw+0GJlkpANN58FZJO1Bs5Wveve"+
//					"gUBFXiLCVeSLG1TO3X3To3n1Ri8gR+9EsbQ3iemdiAWr9RHM+u7WtR2Ws4l20843"+
//					"rmhIGEUXkgGvrXLoaJA9nLV1YFhYrLr4khfU/Y7C6AYIPIX6awr/MP/4L1driBiI"+
//					"AgAA";
//		 try {
//			
//			 byte[] bb = Base64.decode(a);
//			 byte[] rs = unGzip(bb);
//			 
//			for (int i = 0; i < 70; i++) {
//				
//				int aaa = bytesToInt(rs, i);
//				String res =  String.valueOf(aaa);
//				System.out.println(i+" ----> "+ res);
//			}
//
//			 
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//		}
		 
//		 try {
//			
//			 byte b1[] = intToBytes(168515078);
//			 byte b2[] = gzip(b1);
//			 String result = Base64.encode(b2);
//			 System.out.println(result);
//			 
//			 byte b3[] = Base64.decode("H4sIAAAAAAAAAOPiDmMDAH6PLkcEAAAA");
//			 byte b4[] = unGzip(b3);
//			 int i5 =  bytesToInt2(b4, 4);
//			 System.out.println(i5);
//		} catch (Exception e) {
//			e.printStackTrace();
//			// TODO: handle exception
//		}
		 

	 }
}
