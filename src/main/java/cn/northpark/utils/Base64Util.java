package cn.northpark.utils;

import java.io.UnsupportedEncodingException;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.codec.binary.Base64;

import lombok.extern.slf4j.Slf4j;

/**
 * @author zhangyang
 */
@Slf4j
public class Base64Util {


	/**
	 * The Base64 algorithm converts 3 8-bit bytes into 4 6-bit bytes. The Base 64 alphabet consists
	 * of 65 6-bit values, all of which fall within the US-ASCII (7-bit) character encoding.
	 * See http://www.freesoft.org/CIE/RFC/2065/56.htm for details.
	 * <p/>
	 * All Java implementations are required to support this encoding.
	 * See java.lang documentation for details.
	 */
	public static final String ENCODING = "US-ASCII";

	public static String encode( byte[] bytes ){
		try{
			return new String(Base64.encodeBase64( bytes ), ENCODING );
		}
		catch( UnsupportedEncodingException e ){
			throw new RuntimeException( "Could not convert bytes to a String using the " + ENCODING + " encoding", e );
		}
	}

	public static byte[] encodeAsBytes( byte[] bytes ){
		return Base64.encodeBase64( bytes );
	}

	public static byte[] decode( String encodedString ){
		try{
			return Base64.decodeBase64( encodedString.getBytes( ENCODING ) );
		}
		catch( UnsupportedEncodingException e ){
			throw new RuntimeException( "Could not convert String to bytes using the " + ENCODING + " encoding", e );
		}
	}

	@SuppressWarnings("deprecation")
	public static boolean isBase64( String s ){
		byte[] bytes;
		try{
			bytes = s.getBytes( ENCODING );
		}
		catch( UnsupportedEncodingException e ){
			throw new RuntimeException( "Could not convert String to bytes using the " + ENCODING + " encoding", e );
		}
		return Base64.isArrayByteBase64( bytes );
	}

	/**
	 * Converts the specified byte array to a String using UTF-8.
	 * @param bytes the bytes to convert
	 * @return the resulting string
	 */
	public static String toString(byte[] bytes) {
		try {
			return new String(bytes, "UTF-8");
		}
		catch (UnsupportedEncodingException ex) {
			throw new RuntimeException(ex); // shouldn't happen since UTF-8 is supported by all JVMs per spec
		}
	}










	public static Set<Integer> m = new HashSet<Integer>();

	/**
	 * 生成6位int  ID
	 *
	 * @return
	 */
	public static int getInt6(Integer min) {
		int a = 0;
		do {
			a = (int) (Math.random() * 10000 + min);
		} while (m.contains(a));
		m.add(a);
		log.info(""+a);
		return a;
	}


	public static String JIAMI(String str) {
		str = str + "000000";
		String encodes = encode(str.getBytes());
		return encodes;
	}

	public static String JIEMI(String str) {
		String decodes = new String(decode(str));
		decodes = decodes.substring(0, decodes.length() - 6);
		return decodes;
	}

	//加密机制  密码+000000 base64加密
	//解密机制  base64解密 除去000000
	public static void main(String[] args) throws UnsupportedEncodingException {
		//		 log.info(bs.JIAMI("bruce134"));
		String s = "123456";
		log.info("加密前：" + s);
		log.info("加密后：" + JIAMI(s));
		log.info("解密后：" + JIEMI(JIAMI(s)));


		String s2 = "Bruce134";
		log.info("加密前：" + s2);
		log.info("加密后：" + encode(s2.getBytes()));
		log.info("解密后：" + new String(decode(encode(s2.getBytes()))));

		//        log.info(JIEMI("em91Z3VvMTk5ODAwMDAwMA=="));

	}
}
