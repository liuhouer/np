package cn.northpark.utils.encrypt;

import lombok.extern.slf4j.Slf4j;

/**
 * 加解密工具类<br>
 * 工具类包括：MD5加密、SHA加密、Base64加解密、DES加解密、AES加解密<br>
 *
 * @Author : bruce
 */
@Slf4j
public class EnDecryptUtils {
	
	/**
	 * MD5 加密
	 * 
	 * @author : bruce
	 *
	 * @param value
	 * 				待加密字符
	 * @return
	 */
	public static String md5Encrypt(String value){
		String result = null;
		if(value != null && !"".equals(value.trim())){
			result =  MD5Utils.encrypt(value,MD5Utils.MD5_KEY);
		}
		return result;
	}
	
	/**
	 * SHA加密
	 * 
	 * @author : bruce
	 *
	 * @param value		
	 * 					待加密字符
	 * @return	密文
	 */
	public static String shaEncrypt(String value){
		String result = null;
		if(value != null && !"".equals(value.trim())){
			result =  MD5Utils.encrypt(value,MD5Utils.SHA_KEY);
		}
		return result;
	}
	
	/**
	 * BASE64 加密
	 * 
	 * @author : bruce
	 *
	 * @param value
	 * 				待加密字符串
	 * @return
	 */
	public static String base64Encrypt(String value){
		String result = null;
		if(value != null && !"".equals(value.trim())){
			result =  Base64Utils.encrypt(value.getBytes());
		}
		return result;
		
	}
	
	/**
	 * BASE64 解密
	 * 
	 * @author : bruce
	 *
	 * @param value
	 * 				待解密字符串
	 * @return
	 */
	public static String base64Decrypt(String value){
		String result = null;
		try {
			if(value != null && !"".equals(value.trim())){
				byte[] bytes = Base64Utils.decrypt(value);
				result = new String(bytes);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * DES加密<br>
	 * 
	 * @author : bruce
	 *
	 * @param value
	 * 				待加密字符
	 * @param key	
	 * 				若key为空，则使用默认key
	 * @return
	 * 			加密成功返回密文，否则返回null
	 */
	public static String desEncrypt(String value,String key){
		key = key == null ? DESUtils.KEY : key;
		String result = null;
		
		try {
			if(value != null && !"".equals(value.trim())){
				result = DESUtils.encrypt(value, key);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * DES解密
	 * 
	 * @author : bruce
	 *
	 * @param value
	 * 				待解密字符
	 * @param key	
	 * 				若key为空，则使用默认key
	 * @return
	 * @return
	 */
	public static String desDecrypt(String value,String key){
		key = key == null ? DESUtils.KEY : key;
		String result = null;
		
		try {
			if(value != null && !"".equals(value.trim())){
				result =  DESUtils.decrypt(value, key);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * AES加密
	 *
	 * @Author : bruce
	 *
	 * @param value
	 * 					待加密内容
	 * @param key
	 * 					秘钥
	 * @return
	 */
	public static String aesEncrypt(String value,String key ){
		key = key == null ? AESUtils.KEY : key;
		String result = null;
		try {
			if(value != null && !"".equals(value.trim())){		//value is not null
				result = AESUtils.encrypt(value,key);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	/**
	 * AES解密
	 * 
	 * @Author : bruce
	 *
	 * @param value
	 * 				待解密内容
	 * @param key
	 * 				秘钥
	 * @return
	 */
	public static String aesDecrypt(String value , String key){
		key = key == null ? AESUtils.KEY : key;
		String result = null;
		try {
			if(value != null && !"".equals(value.trim())){		//value is not null
				result = AESUtils.decrypt(value,key);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	
	/**
	 * base64 DIY加密算法
	 *
	 * @Author : bruce
	 *
	 * @param key
	 * 					秘钥
	 * @return
	 */
	public static String diyEncrypt(String key ){
		
		return Base64Utils.diyEncrypt(key);
	}
	
	/**
	 * base64 DIY解密算法
	 *
	 * @Author : bruce
	 *
	 * @param value
	 * 					待解密内容
	 * @return
	 */
	public static String diyDecrypt(String value ){
		
		return Base64Utils.diyDecrypt(value);
	}
}
