package cn.northpark.utils;

import java.io.FileInputStream;
import java.io.InputStream;
import java.security.MessageDigest;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

public class MD5Utils {

    private static final Logger LOGGER = LoggerFactory
            .getLogger(MD5Utils.class);

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
    private static final char hexChar[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E',
            'F'};

    private static final String HASH_MD5 = "MD5";


    /**
     * 长链转短链
     *
     * @param url
     * @return
     */
    public static String getShortURL(String url) {
        String[] aResult = convertUrl(url);//将产生4组6位字符串  
        Random random = new Random();
        //随机取一个作为短链  
        int j = random.nextInt(4);//产成4以内随机数
        LOGGER.info("短链接：" + aResult[j] + ";长链接：" + url);
        return aResult[j];
    }

    private static String[] convertUrl(String url) {
        // 可以自定义生成 MD5 加密字符传前的混合 KEY  
        String key = "qiuyouzone";
        // 要使用生成 URL 的字符  
        String[] chars = new String[]{"a", "b", "c", "d", "e", "f", "g", "h",
                "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t",
                "u", "v", "w", "x", "y", "z", "0", "1", "2", "3", "4", "5",
                "6", "7", "8", "9", "A", "B", "C", "D", "E", "F", "G", "H",
                "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T",
                "U", "V", "W", "X", "Y", "Z"
        };
        // 对传入网址进行 MD5 加密  
        String hex = md5ByHex(key + url);

        String[] resUrl = new String[4];
        for (int i = 0; i < 4; i++) {

            // 把加密字符按照 8 位一组 16 进制与 0x3FFFFFFF 进行位与运算  
            String sTempSubString = hex.substring(i * 8, i * 8 + 8);

            // 这里需要使用 long 型来转换，因为 Inteper .parseInt() 只能处理 31 位 , 首位为符号位 ,  
            // 如果不用long ，则会越界  
            long lHexLong = 0x3FFFFFFF & Long.parseLong(sTempSubString, 16);
            String outChars = "";
            for (int j = 0; j < 6; j++) {
                // 把得到的值与 0x0000003D 进行位与运算，取得字符数组 chars 索引  
                long index = 0x0000003D & lHexLong;
                // 把取得的字符相加  
                outChars += chars[(int) index];
                // 每次循环按位右移 5 位  
                lHexLong = lHexLong >> 5;
            }
            // 把字符串存入对应索引的输出数组  
            resUrl[i] = outChars;
        }
        return resUrl;
    }

    /**
     * MD5加密(32位大写)
     *
     * @param src
     * @return
     */
    private static String md5ByHex(String src) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] b = src.getBytes();
            md.reset();
            md.update(b);
            byte[] hash = md.digest();
            String hs = "";
            String stmp = "";
            for (int i = 0; i < hash.length; i++) {
                stmp = Integer.toHexString(hash[i] & 0xFF);
                if (stmp.length() == 1)
                    hs = hs + "0" + stmp;
                else {
                    hs = hs + stmp;
                }
            }
            return hs.toUpperCase();
        } catch (Exception e) {
            return "";
        }
    }


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
            LOGGER.error("Md5Utils------->", e);
            ;
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
        LOGGER.info(MD5Utils.encoding("bruce134"));
    }
}
