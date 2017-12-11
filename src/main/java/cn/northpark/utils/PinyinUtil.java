package cn.northpark.utils;

import java.util.Date;
import java.util.Random;

import org.apache.log4j.Logger;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;


/**
 * @author zhangyang
 *
 */
public class PinyinUtil {
	

	private static final Logger LOGGER = Logger
            .getLogger(PinyinUtil.class);
	/**
	 * 获得汉语拼音的输出格式
	 * 
	 * @return
	 */
	protected static HanyuPinyinOutputFormat getFormat() 
	{
		HanyuPinyinOutputFormat result = new HanyuPinyinOutputFormat();
		result.setVCharType(HanyuPinyinVCharType.WITH_V);
		result.setCaseType(HanyuPinyinCaseType.UPPERCASE);
		result.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
		return result;
	}

	/**
	 * 转换一个汉字字符成拼音的字符串
	 * 
	 * @param c
	 * @return
	 */
	public static String paraseCharToPinyin(char c) 
	{
		String result = null;

		String[] pinyinArray = PinyinHelper.toHanyuPinyinStringArray(c);

		try 
		{
			pinyinArray = PinyinHelper.toHanyuPinyinStringArray(c, getFormat());
		} catch (BadHanyuPinyinOutputFormatCombination e) 
		{
			pinyinArray = PinyinHelper.toHanyuPinyinStringArray(c);
		}

		result = (null == pinyinArray || pinyinArray.length == 0) ? 
					Character.toString(c) : pinyinArray[0];
		
		return result;
	}

	/**
	 * 转换一个串成为汉语拼音
	 * 
	 * @param str
	 * @return
	 */
	public static String paraseStringToPinyin(String str) {
		if(null==str||str.trim().length()==0)
		{
			return str;
		}
		
		StringBuffer result = new StringBuffer();
		
		for (int i = 0; i < str.length(); i++) 
		{
			result.append(paraseCharToPinyin(str.charAt(i)));
		}
		
		return result.toString();
	}
	
     
    
    /**
     * 获取中文
     * @param seed
     * @return
     * @throws Exception
     */
    public static String getChinese() throws Exception { 
    	String str = null; 
    	try {
			
    		int highPos, lowPos; 
    		long seed = new Date().getTime(); 
    		Random random = new Random(seed); 
    		
    		highPos = (176 + Math.abs(random.nextInt(39))); 
    		lowPos = 161 + Math.abs(random.nextInt(93)); 
    		byte[] b = new byte[2]; 
    		b[0] = (new Integer(highPos)).byteValue(); 
    		b[1] = (new Integer(lowPos)).byteValue(); 
    		str = new String(b, "GB2312"); 
		} catch (Exception e) {
			// TODO: handle exception
			LOGGER.error("pinyinUtils------->", e);;
		}
    	return str; 
    } 
    
    /**
     * 取得4字名字
     * @return
     * @throws Exception
     */
    public static String get4Hanzi() throws Exception { 
    	StringBuilder sb = new StringBuilder();
    	sb.append(getChinese());
    	sb.append(getChinese());
    	sb.append(getChinese());
    	return sb.toString();
    }
    
    /**
     * 取得随机字母
     * @return
     * @throws Exception
     */
    public static String getEnglish(){ 
    	 String chars = "abcdefghijklmnopqrstuvwxyz";
    	 String str = "";;
    	 
    	for (int i = 0; i < 8; i++) {
			str+= String.valueOf(chars.charAt((int)(Math.random() * 26)));
		}
    	 LOGGER.info("english--"+str);
    	return str;
    }
    
    
    /**
     * 取得随机一个字母
     * @return
     * @throws Exception
     */
    public static String getZIMU(){ 
    	 String chars = "abcdefghijklmnopqrstuvwxyz";
    	 String str = "";;
    	 
			str= String.valueOf(chars.charAt((int)(Math.random() * 26)));
    	 LOGGER.info("zimu--"+str);
    	return str;
    }
    
    /**
     * 取得中文翻译的字母
     * @return
     * @throws Exception
     */
    public static String getFanyi() throws Exception { 
    	String str = "";
    	char c[] = get4Hanzi().toCharArray();
    	for (int i = 0; i < c.length; i++) {
			
    		str+=paraseCharToPinyin(c[i]);
		}
    	str = str.toLowerCase();
    	LOGGER.info("汉语pinyin"+str);
    	return str;
    }
    
    
    /**
     * 取得中文翻译的首字母
     * @return
     * @throws Exception
     */
    public static String getFanyi1(String ZW)  { 
    	String str = "";
    	char c[] = ZW.toCharArray();
    	for (int i = 0; i < c.length; i++) {
			
    		str+=paraseCharToPinyin(c[i]);
		}
    	str = str.toLowerCase();
    	LOGGER.info("汉语_____>"+str);
    	return str.substring(0, 1);
    }
    
    /**
     * 取得中文翻译的邮箱
     * @return
     * @throws Exception
     */
    public static String getFanyiEmail() throws Exception { 
    	String str = "";
    	char c[] = get4Hanzi().toCharArray();
    	for (int i = 0; i < c.length; i++) {
			
    		str+=paraseCharToPinyin(c[i]);
		}
    	str = str.toLowerCase()+"@qq.com";
    	LOGGER.info("email----"+str);
    	return str;
    }
    
    public static void main(String[] args) throws Exception{
    	
		
//    	getFanyiEmail();
    	
    	getFanyi1("香草天空");
	}
}
