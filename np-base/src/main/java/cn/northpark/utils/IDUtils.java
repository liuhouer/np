package cn.northpark.utils;

import lombok.extern.slf4j.Slf4j;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * @author bruce
 * @实现了uuid的一些常见方法
 */

@Slf4j
public class IDUtils {

    private IDUtils() {

    }

    private static class InstanceHolder {
        private static final IDUtils INSTANCE = new IDUtils();
    }

    /**
     * 尽管在 Java 5+ 中使用 volatile 关键字可以防止指令重排，但这种模式存在一些问题。更好的方法是使用静态内部类实现单例模式。
     * @return
     */
	public static IDUtils getInstance() {
		return InstanceHolder.INSTANCE;
	}
	
	private static final String ALL_CHAR = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ"; 
	private static final String LETTER_CHAR = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ"; 
	private static final String NUMBER_CHAR = "0123456789";

    /**
     * 生成6位随机数-String
     *
     * @return
     */
    public String geneInt() {
        Random random = new Random();
        int i = random.nextInt(1000000);
        String rs = String.valueOf(i);
        if (rs.length() == 5) {
            rs += "0";
        }
        return rs;
    }

    /**
     * 生成一个随机不重复的情景id数字
     *
     * @return
     */
    public int getUniqueSceneid() {
        int i = 0;
        Random random = new Random();
        Calendar time = Calendar.getInstance();
        time.set(Calendar.HOUR_OF_DAY, 0);
        time.set(Calendar.MINUTE, 0);
        time.set(Calendar.SECOND, 0);
        time.set(Calendar.MILLISECOND, 0);

        i = (int) (System.currentTimeMillis() - time.getTimeInMillis() + random.nextInt(10000));

        return i;
    }
    
    
    /**
     * @return
     * @desc 随机取出一个数【size 为  10 ，取得类似0-9的区间数】
     */
    public Integer getRandomOne(List<?> list) {


        Random ramdom = new Random();
        int number = -1;
        int max = list.size();

        //size 为  10 ，取得类似0-9的区间数
        number = Math.abs(ramdom.nextInt() % max);

        return number;

    }
    
    /**
     * 获取定长的随机数，包含大小写、数字
     * @autor:bruce
     * @date:2014年8月11日
     *
     * @param length
     * 				随机数长度
     * @return
     */
    public String generateString(int length) { 
        StringBuffer sb = new StringBuffer(); 
        Random random = new Random(); 
        for (int i = 0; i < length; i++) { 
                sb.append(ALL_CHAR.charAt(random.nextInt(ALL_CHAR.length()))); 
        } 
        return sb.toString(); 
    } 
    
    /**
     * 获取定长的随机数，包含大小写字母
     * @autor:bruce
     * @date:2014年8月11日
     *
     * @param length
     * 				随机数长度
     * @return
     */
    public String generateMixString(int length) { 
        StringBuffer sb = new StringBuffer(); 
        Random random = new Random(); 
        for (int i = 0; i < length; i++) { 
                sb.append(LETTER_CHAR.charAt(random.nextInt(LETTER_CHAR.length()))); 
        } 
        return sb.toString(); 
    } 
    
    /**
     * 获取定长的随机数，只包含小写字母
     * @autor:bruce
     * @date:2014年8月11日
     *
     * @param length	
     * 				随机数长度
     * @return
     */
    public String generateLowerString(int length) { 
        return generateMixString(length).toLowerCase(); 
    } 
    
    /**
     * 获取定长的随机数，只包含大写字母
     * @autor:bruce
     * @date:2014年8月11日
     *
     * @param length
     * 				随机数长度
     * @return
     */
    public String generateUpperString(int length) { 
        return generateMixString(length).toUpperCase(); 
    } 
    
    /**
     * 获取定长的随机数，只包含数字
     * @autor:bruce
     * @date:2014年8月11日
     *
     * @param length
     * 				随机数长度
     * @return
     */
    public String generateNumberString(int length){
    	StringBuffer sb = new StringBuffer(); 
        Random random = new Random(); 
        for (int i = 0; i < length; i++) { 
                sb.append(NUMBER_CHAR.charAt(random.nextInt(NUMBER_CHAR.length()))); 
        } 
        return sb.toString(); 
    }

    /**
     * 生成一个随机不重复的情景id 字符串
     *
     * @return
     */
    public String getUniqueSceneStr() {
        return String.valueOf(IDUtils.getInstance().getUniqueSceneid());
    }


    /**
     * 生成一个结合年月日时分秒的单号
     * @return
     */
    public String generateOrderNumber() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        String timestamp = dateFormat.format(new Date());

        Random random = new Random();
        String randomString = String.format("%04d", random.nextInt(10000));

        return timestamp + randomString;
    }
    
    
    public static void main(String[] args) {
    	for (int i = 0; i < 10; i++) {
			
    		log.info("{}",IDUtils.getInstance().generateOrderNumber());
		}
	}
}
