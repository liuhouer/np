package cn.northpark.utils;

import java.util.Calendar;
import java.util.List;
import java.util.Random;

/**
 * @author bruce
 * @实现了uuid的一些常见方法
 */

public class IDUtils {

    /**
     * 生成6伪随机数-String
     *
     * @return
     */
    public static String geneInt() {
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
    public static synchronized int getUniqueSceneid() {
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
    public static Integer getRandomOne(List<?> list) {


        Random ramdom = new Random();
        int number = -1;
        int max = list.size();

        //size 为  10 ，取得类似0-9的区间数
        number = Math.abs(ramdom.nextInt() % max);

        return number;

    }

    public static void main(String[] args) {
        System.out.println(geneInt());
    }
}
