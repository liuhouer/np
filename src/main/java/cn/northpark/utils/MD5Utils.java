package cn.northpark.utils;

import java.io.FileInputStream;
import java.io.InputStream;
import java.security.MessageDigest;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

public class MD5Utils {
	// 0的ASCII码
	private static final int ASCII_0 = 48;
	// 9的ASCII码
	private static final int ASCII_9 = 57;
	// A的ASCII码
	private static final int ASCII_A = 65;
	// F的ASCII码
	private static final int ASCII_F = 70;
	// a的ASCII码
	private static final int ASCII_a = 97;
	// f的ASCII码
	private static final int ASCII_f = 102;

	// 可表示16进制数字的字符
	private static final char hexChar[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E',
			'F' };

	private static final String HASH_MD5 = "MD5";

	/**
	 * 获取字节数组MD5码
	 * 
	 * @param bs
	 * @return
	 */
	public final static String encoding(byte[] bs) {

		String encodingStr = null;
		try {
			MessageDigest mdTemp = MessageDigest.getInstance(HASH_MD5);
			mdTemp.update(bs);

			return toHexString(mdTemp.digest());

		} catch (Exception e) {
			e.printStackTrace();
		}

		return encodingStr;
	}

	/**
	 * 获取字符串MD5码
	 * 
	 * @param text
	 * @return
	 */
	public final static String encoding(String text) {
		if (text == null) {
			return null;
		}
		return encoding(text.getBytes());
	}

	public final static String encodeTwice(String text) {
		if (text == null) {
			return null;
		}
		String md5Once = encoding(text.getBytes());
		return encoding(md5Once.getBytes());
	}

	/**
	 * 获取文件内容MD5码
	 * 
	 * @param filePath
	 * @return
	 */
	public final static String encodingFile(String filePath) {
		InputStream fis = null;
		try {
			fis = new FileInputStream(filePath);
			return encoding(fis);
		} catch (Exception ee) {
			return null;
		} finally {
			if (fis != null) {
				try {
					fis.close();
				} catch (Exception ex) {
				}
			}
		}
	}

	/**
	 * 获取输入流MD5码
	 * 
	 * @param fis
	 * @return
	 * @throws Exception
	 */
	public final static String encoding(InputStream fis) throws Exception {
		byte[] buffer = new byte[1024];
		MessageDigest md5 = MessageDigest.getInstance(HASH_MD5);
		int numRead = 0;
		while ((numRead = fis.read(buffer)) > 0) {
			md5.update(buffer, 0, numRead);
		}
		return toHexString(md5.digest());
	}

	/**
	 * 转换为用16进制字符表示的MD5
	 * 
	 * @param b
	 * @return
	 */
	public static String toHexString(byte[] b) {
		StringBuilder sb = new StringBuilder(b.length * 2);
		for (int i = 0; i < b.length; i++) {
			sb.append(hexChar[(b[i] & 0xf0) >>> 4]);
			sb.append(hexChar[b[i] & 0x0f]);
		}
		return sb.toString();
	}

	/**
	 * 检验是否是合法的MD5串
	 * 
	 * @param md5Str
	 * @return
	 */
	public static boolean validate(String md5Str) {
		if (md5Str == null || md5Str.length() != 32) {
			return false;
		}
		byte[] by = md5Str.getBytes();
		for (int i = 0; i < by.length; i++) {
			int asciiValue = (int) by[i];
			if (asciiValue < ASCII_0 || (asciiValue > ASCII_9 && asciiValue < ASCII_A)
					|| (asciiValue > ASCII_F && asciiValue < ASCII_a) || asciiValue > ASCII_f) {
				return false;
			}
		}
		return true;
	}

	public static String decodePwd(String password) {
		String old_pwd = "";
		String enpwd = password.substring(10, password.length() - 10);
		old_pwd = new String(Base64.decode(enpwd));
		return old_pwd;
	}
	
	public static void main(String[] args) {
		MD5Utils.encoding("yang521xiao1314");
	}
}
