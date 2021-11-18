package cn.northpark.utils;

import cn.northpark.constant.BC_Constant;
import lombok.extern.slf4j.Slf4j;
import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

import java.util.Date;
import java.util.Random;


/**
 * @author zhangyang
 */
@Slf4j
public class PinyinUtil {



    /**
     * 获得汉语拼音的输出格式
     *
     * @return
     */
    protected static HanyuPinyinOutputFormat getFormat() {
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
    public static String paraseCharToPinyin(char c) {
        String result = null;

        String[] pinyinArray = PinyinHelper.toHanyuPinyinStringArray(c);

        try {
            pinyinArray = PinyinHelper.toHanyuPinyinStringArray(c, getFormat());
        } catch (BadHanyuPinyinOutputFormatCombination e) {
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
        if (null == str || str.trim().length() == 0) {
            return str;
        }

        StringBuffer result = new StringBuffer();

        for (int i = 0; i < str.length(); i++) {
            result.append(paraseCharToPinyin(str.charAt(i)));
        }
        log.info("转换一个串成为汉语拼音------->{}", result.toString());
        return result.toString();
    }
    
    /**
     *  转换一个串成为格式化的汉语拼音
     * @param str
     * @param linkstr：拼接串
     * @return
     */
    public static String paraseStringToFormatPinyin(String str, String linkstr) {
    	if (null == str || str.trim().length() == 0) {
            return str;
        }

        StringBuffer result = new StringBuffer();

        for (int i = 0; i < str.length(); i++) {
            result.append(paraseCharToPinyin(str.charAt(i)));
            if(i<str.length()-1) result.append(linkstr);
        }
        String resultmsg = result.toString().toLowerCase();
		log.info("转换一个串成为格式化的汉语拼音------->{}", resultmsg);
        return resultmsg;
	}


    /**
     * 获取随机中文
     *
     * @param seed
     * @return
     * @throws Exception
     */
    public static String getRandomChinese() throws Exception {
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

            log.error("pinyinUtils------->", e);
            ;
        }
        return str;
    }


    /**
     * 取得随机英文字母 8位
     *
     * @return
     * @throws Exception
     */
    public static String getRandomEnglishWord() {
        String chars = "abcdefghijklmnopqrstuvwxyz";
        String str = "";
        ;

        for (int i = 0; i < 8; i++) {
            str += String.valueOf(chars.charAt((int) (Math.random() * 26)));
        }
        log.info("english--" + str);
        return str;
    }


    /**
     * 取得随机一个字母
     *
     * @return
     * @throws Exception
     */
    public static String getRandomEnglishChar() {
        String chars = "abcdefghijklmnopqrstuvwxyz";
        String str = "";
        ;

        str = String.valueOf(chars.charAt((int) (Math.random() * 26)));
        log.info("zimu--" + str);
        return str;
    }



    /**
     * 取得中文翻译的首字母
     *
     * @return
     * @throws Exception
     */
    public static String getFirstChar(String chinese) {
        String str = "";
        char c[] = chinese.toCharArray();
        for (int i = 0; i < c.length; i++) {

            str += paraseCharToPinyin(c[i]);
        }
        str = str.toLowerCase();
        String substring = str.substring(0, 1);
        log.info("中文的首字母---->{}",substring);
        return substring;
    }



    /**
     * 取得中文字符串的首字母集合
     * <pre>
     * 香草天空 ---> XCTK
     * </pre>
     * @return
     * @throws Exception
     */
    public static String getFirstCharStr(String pianduan) throws Exception {
        StringBuffer sb = new StringBuffer();
        char[] input = pianduan.toCharArray();
        HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
        format.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        if (input != null) {
            for (char c : input) {
                if (c > 128) {
                    try {
                        String[] temp = PinyinHelper.toHanyuPinyinStringArray(c, format);
                        if (temp != null) {
                            sb.append(temp[0].charAt(0));
                        }
                    } catch (BadHanyuPinyinOutputFormatCombination e) {
                        e.printStackTrace();
                    }
                } else {
                    sb.append(c);
                }
            }
        }

        log.info(sb.toString().replaceAll("\\W", "").trim());
        return sb.toString().replaceAll("\\W", "").trim();
    }


    public static void main(String[] args) throws Exception {


//    	getFanyiEmail();
    	
    	 getFirstChar("香草天空");
         

        getFirstCharStr("香草天空");
        
        System.out.println(BC_Constant.FormatSpilt.hengxian.getNamestr());
        paraseStringToPinyin("香草天空");
        
        paraseStringToFormatPinyin("香草天空","-");
    }

	
}
