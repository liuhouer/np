package cn.northpark.utils;

import java.nio.charset.Charset;

public class DeEnCode {
	 private static final String key0 = "FECOI()*&<MNCXZPKL";  
	    private static final Charset charset = Charset.forName("UTF-8");  
	    private static byte[] keyBytes = key0.getBytes(charset);  
	      
	    public static String encode(String enc){  
	        byte[] b = enc.getBytes(charset);  
	        for(int i=0,size=b.length;i<size;i++){  
	            for(byte keyBytes0:keyBytes){  
	                b[i] = (byte) (b[i]^keyBytes0);  
	            }  
	        }  
	        return new String(b);  
	    }  
	      
	    public static String decode(String dec){  
	        byte[] e = dec.getBytes(charset);  
	        byte[] dee = e;  
	        for(int i=0,size=e.length;i<size;i++){  
	            for(byte keyBytes0:keyBytes){  
	                e[i] = (byte) (dee[i]^keyBytes0);  
	            }  
	        }  
	        return new String(e);  
	    }  
	  
	    public static void main(String[] args) {  
	        String s="13483724051";  
	        String enc = encode(s);  
	        String dec = decode(enc);  
	        System.out.println(enc);  
	        System.out.println(dec);  
	    }  
}
