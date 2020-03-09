package cn.northpark.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Locale;
import java.util.Random;
import java.util.Set;

import com.google.common.collect.Lists;

import lombok.extern.slf4j.Slf4j;

/**
 * 时间工具类
 *
 * @author zhangyang
 * @since 2014-05-23
 */
@Slf4j
public class TimeUtils {
    private static final long ONE_MINUTE = 60L;
    private static final long ONE_HOUR = 3600L;
    private static final long ONE_DAY = 86400L;
    private static final long ONE_MONTH = 2592000L;
    private static final long ONE_YEAR = 31104000L;

    private static final String ONE_SECOND_AGO = "秒前";
    private static final String ONE_MINUTE_AGO = "分钟前";
    private static final String ONE_HOUR_AGO = "小时前";
    private static final String ONE_DAY_AGO = "天前";
    private static final String ONE_MONTH_AGO = "月前";
    private static final String ONE_YEAR_AGO = "年前";


    //格式化时间串成为  几天前 几秒前 几小时前  几分钟前 几年前sth.....
    public static String formatToNear(String str) {

        try {
            SimpleDateFormat format = null;
            if (str.length() > 10) {
                format = new SimpleDateFormat("yyyy-MM-dd HH:m:s");
            } else {
                format = new SimpleDateFormat("yyyy-MM-dd");
            }
            Date date;
            date = format.parse(str);
            str = TimeUtils.format(date);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            log.error("TimeUtils------->", e);
            ;
        }

        return str;

    }

    //格式化时间串成为  几天前 几秒前 几小时前  几分钟前 几年前sth.....

    /**
     * 距离今天多久
     *
     * @param date
     * @return
     */
    public static String fromToday(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        long time = date.getTime() / 1000;
        long now = new Date().getTime() / 1000;
        long ago = now - time;
        if (ago <= ONE_HOUR)
            return ago / ONE_MINUTE + "分钟前";
        else if (ago <= ONE_DAY)
            return ago / ONE_HOUR + "小时" + (ago % ONE_HOUR / ONE_MINUTE)
                    + "分钟前";
        else if (ago <= ONE_DAY * 2)
            return "昨天" + calendar.get(Calendar.HOUR_OF_DAY) + "点"
                    + calendar.get(Calendar.MINUTE) + "分";
        else if (ago <= ONE_DAY * 3)
            return "前天" + calendar.get(Calendar.HOUR_OF_DAY) + "点"
                    + calendar.get(Calendar.MINUTE) + "分";
        else if (ago <= ONE_MONTH) {
            long day = ago / ONE_DAY;
            return day + "天前" + calendar.get(Calendar.HOUR_OF_DAY) + "点"
                    + calendar.get(Calendar.MINUTE) + "分";
        } else if (ago <= ONE_YEAR) {
            long month = ago / ONE_MONTH;
            long day = ago % ONE_MONTH / ONE_DAY;
            return month + "个月" + day + "天前"
                    + calendar.get(Calendar.HOUR_OF_DAY) + "点"
                    + calendar.get(Calendar.MINUTE) + "分";
        } else {
            long year = ago / ONE_YEAR;
            int month = calendar.get(Calendar.MONTH) + 1;// JANUARY which is 0 so month+1
            return year + "年前" + month + "月" + calendar.get(Calendar.DATE)
                    + "日";
        }

    }


    /**
     * 距离今天多久  简短的  只包含 年|月|日|小时|分 前的一种
     *
     * @param date
     * @return
     */
    public static String format(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        long time = date.getTime() / 1000;
        long now = new Date().getTime() / 1000;
        long ago = now - time;
        if (ago <= ONE_MINUTE)
            return ago + ONE_SECOND_AGO;
        else if (ago <= ONE_HOUR)
            return ago / ONE_MINUTE + ONE_MINUTE_AGO;
        else if (ago <= ONE_DAY)
            return ago / ONE_HOUR + ONE_HOUR_AGO;
        else if (ago <= ONE_DAY * 2)
            return "昨天";
        else if (ago <= ONE_DAY * 3)
            return "前天";
        else if (ago <= ONE_MONTH) {
            long day = ago / ONE_DAY;
            return day + ONE_DAY_AGO;
        } else if (ago <= ONE_YEAR) {
            long month = ago / ONE_MONTH;
            return month + ONE_MONTH_AGO;
        } else {
            long year = ago / ONE_YEAR;
            return year + ONE_YEAR_AGO;
        }

    }

    /**
     * 取得系统当前时间(格式：1970年1月1日0时起到当前的毫秒)
     *
     * @return 当前时间
     */
    public static long getCurrentTiem() {
        return System.currentTimeMillis();
    }

    /**
     * 取得当前时间，格式： yyyy-MM-dd hh:mm:ss
     *
     * @return 当前时间
     */
    public static String getNowTime() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(new Date().getTime());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dateFormat.format(calendar.getTime());
    }


    /**
     * 取得英文日期时间，格式： 29 Sep 2011
     *
     * @return 当前时间
     */
    public static String getEnglishDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(new Date().getTime());
        SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy", Locale.ENGLISH);
        return sdf.format(calendar.getTime());
    }

    /**
     * 转化取得英文日期时间，从 2016-07-22 格式： 29 Sep 2011
     *
     * @return 当前时间
     */
    public static String parse2EnglishDate(String date) {

        SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy", Locale.ENGLISH);
        return sdf.format(stringToMillis(date));
    }


    /**
     * 字符串(格式：yyyy-MM-dd hh:mm:ss) --> 毫秒(说明：1970年1月1日0时起到当前字符串时间的毫秒)
     *
     * @return 毫秒数(1970年1月1日0时起到当前字符串时间的毫秒)
     */
    public static long stringToMillis(String source) {
        Date date = null;
        SimpleDateFormat dateFormat = null;
        if (source.length() > 10) {
            dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        } else {
            dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        }
        try {
            date = dateFormat.parse(source);
        } catch (ParseException e) {
            log.error("TimeUtils------->", e);
            ;
            date = new Date();
        }
        if (null == date) return 0;
        return date.getTime();
    }

    /**
     * @author bruce 取得当前时间 格式yyyy-MM-dd hh:mm:ss
     */
    public static String nowTime() {
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(new Date().getTime());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dateFormat.format(c.getTime());
    }

    /**
     * @author bruce 取得时钟表时间 hh:mm:ss
     */
    public static String nowClock() {
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(new Date().getTime());
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        return dateFormat.format(c.getTime());
    }

    /**
     * @author bruce 取得当前日期
     */
    public static String nowdate() {
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(new Date().getTime());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.format(c.getTime());
    }

    /**
     * @author bruce 取得N个月以后的日期
     */
    public static String N_MonthDate(int n) {
        Calendar c = Calendar.getInstance();
        c.add(GregorianCalendar.MONTH, n);
        return "" + c.get(GregorianCalendar.YEAR) + "-" + (c.get(GregorianCalendar.MONTH) + 1) + "-" + c.get(GregorianCalendar.DATE);
    }

    /**
     * @author bruce 取得N年以后的日期
     */
    public static String N_YearDate(int n) {
        Calendar c = Calendar.getInstance();
        c.add(GregorianCalendar.YEAR, n);
        return "" + c.get(GregorianCalendar.YEAR) + "-" + (c.get(GregorianCalendar.MONTH) + 1) + "-" + c.get(GregorianCalendar.DATE);
    }


    /**
     * @author bruce 取得N年以后的日期
     */
    public static String N_YearTime(int n) {
        Calendar c = Calendar.getInstance();
        c.add(GregorianCalendar.YEAR, n);
        return "" + c.get(GregorianCalendar.YEAR) + "-" + (c.get(GregorianCalendar.MONTH) + 1) + "-" + c.get(GregorianCalendar.DATE) + " " + c.get(GregorianCalendar.HOUR) + ":" + c.get(GregorianCalendar.MINUTE) + ":" + c.get(GregorianCalendar.SECOND);
    }

    /**
     * @author bruce
     * @function 取得标准时间 2014-05-23从格式yyyy-MM-dd hh:mm:ss
     */

    public static String getHalfDate(String timeStr) {
        String t = timeStr;
        if (timeStr.contains("-")) {
            t = timeStr.substring(0, 10);
        }
        return t;
    }

    /**
     * @author bruce
     * @function 取得标准时间 2014从格式yyyy-MM-dd hh:mm:ss
     */
    public static String getYear(String timeStr) {
        String t = timeStr;
        if (timeStr.contains("-")) {
            t = timeStr.substring(0, 4);
        }
        return t;
    }

    /**
     * 获取月份
     *
     * @param time 标准时间字符串（yyyy-MM-dd hh:mm:ss）
     * @return 月份
     */
    public static String getMonth(String time) {
        String t = time;
        if (null == time) return null;
        if (time.contains("-")) {
        	 String[] str = time.split("-");
             if (str.length >= 1) {
                 t = str[1];
             }
            return t;
        }
        return null;
    }

    /**
     * @author bruce
     * @function 取得标准时间 2014从格式yyyy-MM-dd hh:mm:ss
     */

    public static String getDay(String timeStr) {
        String t = timeStr;
        if (timeStr.contains("-")) {
            String[] str = timeStr.split("-");
            if (str.length >= 2) {
                t = str[2];
            }
        }
        return t;
    }

    /**
     * @param --例子"2004-01-02 11:30:24"
     * @throws ParseException
     * @author bruce
     * @function 计算两个时间的差：耗时多少
     */

    public static String getPastTime(String nowtime, String oldtime) throws ParseException {//
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        java.util.Date now = df.parse(nowtime);
        java.util.Date date = df.parse(oldtime);
        long l = now.getTime() - date.getTime();
        long day = l / (24 * 60 * 60 * 1000);
        long hour = (l / (60 * 60 * 1000) - day * 24);
        long min = ((l / (60 * 1000)) - day * 24 * 60 - hour * 60);
        long s = (l / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
        String pastTime = "" + day + "天" + hour + "小时" + min + "分" + s + "秒";

        return pastTime;
    }

    /**
     * @throws ParseException
     * @author bruce
     * @function 计算是否过期, true未过期；false过期
     */

    public static boolean isInvalid(String nowtime, String overtime) throws ParseException {//
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        java.util.Date now = df.parse(nowtime);
        java.util.Date over = df.parse(overtime);
        long l = now.getTime() - over.getTime();
        boolean flag = true;
        if (l > 0) {
            flag = false;
        } else {
            flag = true;
        }
        return flag;
    }

    /**
     * @param specifiedDay
     * @return
     * @desc 取得前一天的时间
     */
    public static String getDayBefore(String specifiedDay) {
        // SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        Calendar c = Calendar.getInstance();
        Date date = null;
        try {
            date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(specifiedDay);
        } catch (ParseException e) {
            log.error("TimeUtils------->", e);
            ;
        }
        c.setTime(date);
        int day = c.get(Calendar.DATE);
        c.set(Calendar.DATE, day - 1);

        String dayBefore = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(c.getTime());
        return dayBefore;
    }

    /**
     * @param specifiedDay
     * @return
     * @desc 取得后一天的时间
     */
    public static String getDayAfter(String specifiedDay) {
        Calendar c = Calendar.getInstance();
        Date date = null;
        try {
            date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(specifiedDay);
        } catch (ParseException e) {
            log.error("TimeUtils------->", e);
            ;
        }
        c.setTime(date);
        int day = c.get(Calendar.DATE);
        c.set(Calendar.DATE, day + 1);

        String dayAfter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(c.getTime());
        return dayAfter;
    }


    /**
     * @param specifiedDay
     * @return
     * @author bruce
     * @desc 取得某时间  前后N 天/月/年的时间,N=正负数，type=类型[D:DAY | M:MONTH |Y:YEAR |H:HOUR | MIN:minute | S:second]
     */
    public static String getDateAfterOrBeforeN(String specifiedDay, int N, String type) {
        Calendar c = Calendar.getInstance();
        Date date = null;
        try {
            date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(specifiedDay);
        } catch (ParseException e) {
            log.error("TimeUtils------->", e);
            ;
        }
        c.setTime(date);

        if ("D".equals(type)) {
            int day = c.get(Calendar.DATE);
            c.set(Calendar.DATE, day + N);
        } else if ("M".equals(type)) {
            int month = c.get(Calendar.MONTH);
            c.set(Calendar.MONTH, month + N);
        } else if ("Y".equals(type)) {
            int year = c.get(Calendar.YEAR);
            c.set(Calendar.YEAR, year + N);
        } else if ("H".equals(type)) {
            int hour = c.get(Calendar.HOUR);
            c.set(Calendar.HOUR, hour + N);
        } else if ("MIN".equals(type)) {
            int minute = c.get(Calendar.MINUTE);
            c.set(Calendar.MINUTE, minute + N);
        } else if ("S".equals(type)) {
            int second = c.get(Calendar.SECOND);
            c.set(Calendar.SECOND, second + N);
        }

        String After = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(c.getTime());
        return After;
    }

    /**
     * @param specifiedDay
     * @return
     * @author bruce
     * @desc 取得前后N天的时间, N=正负数
     */
    public static String getDayAfterOrBeforeN(String specifiedDay, int N) {
        Calendar c = Calendar.getInstance();
        Date date = null;
        try {
            date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(specifiedDay);
        } catch (ParseException e) {
            log.error("TimeUtils------->", e);
            ;
        }
        c.setTime(date);
        int day = c.get(Calendar.DATE);
        c.set(Calendar.DATE, day + N);

        String dayAfter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(c.getTime());
        return dayAfter;
    }

    /**
     * @param specifiedDay
     * @return
     * @author bruce
     * @desc 取得前后N分钟后的时间, N=正负数
     */
    public static String getMinuteAfterOrBeforeN(String specifiedDay, int N) {
        Calendar c = Calendar.getInstance();
        Date date = null;
        try {
            date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(specifiedDay);
        } catch (ParseException e) {
            log.error("TimeUtils------->", e);
            ;
        }
        c.setTime(date);
        int minute = c.get(Calendar.MINUTE);
        c.set(Calendar.MINUTE, minute + N);

        String minuteAfter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(c.getTime());
        return minuteAfter;
    }

    /**
     * @param specifiedDay
     * @return
     * @author bruce
     * @desc 取得当前的年月比如： 1407
     */
    public static String getYearMonth() {
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(new Date().getTime());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyMM");
        return dateFormat.format(c.getTime());
    }


    /**
     * @return
     * @author bruce
     * @desc 把long转化成String
     * 将长时间格式字符串转换为字符串 yyyy-MM-dd HH:mm:ss
     */
    public static String longToString(Long time) {
        Date date = new Date(time);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(date);
        //log.info("TIME:::"+dateString);
        return dateString;
    }

    /**
     * @return
     * @author bruce
     * @desc 把long转化成String
     * 将长时间格式字符串转换为字符串 yyyy-MM-dd HH:mm:ss
     */
    public static String longToDateStrng(Long time) {
        Date date = new Date(time);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = formatter.format(date);
        log.info("TIME:::" + dateString);
        return dateString;
    }


    /**
     * @return
     * @desc 随机年07-17
     */
    public static String getRandomYear() {

        String a = "";
        Random random = new Random();
        int max = 17;
        int min = 14;

        int s = random.nextInt(max) % (max - min + 1) + min;


        if (String.valueOf(s).length() == 1) {
            a = "20" + 0 + "" + s;
        } else {
            a = "20" + s + "";
        }

        return a;

    }

    /**
     * @return
     * @desc 随机月01-12
     */
    public static String getRandomMonth() {

        String a = "";
        Random random = new Random();
        int max = 12;
        int min = 01;

        int s = random.nextInt(max) % (max - min + 1) + min;
        if (String.valueOf(s).length() == 1) {
            a = 0 + "" + s;
        } else {
            a = s + "";
        }

        return a;

    }

    /**
     * @return
     * @desc 随机日1-31
     */
    public static String getRandomDay() {

        String a = "";
        Random random = new Random();
        int max = 31;
        int min = 01;

        int s = random.nextInt(max) % (max - min + 1) + min;
        if (String.valueOf(s).length() == 1) {
            a = 0 + "" + s;
        } else {
            a = s + "";
        }
        return a;

    }


    /**
     * @return
     * @desc 随机日期从2007-至今
     */
    public static String getRandomDate() {

        return getRandomYear() + "-" + getRandomMonth() + "-" + getRandomDay();

    }


    /**
     * @param currentDate
     * @param days
     * @return
     * @author bruce
     * 获取N个工作日以后的日期
     */
    public static Date getWorkDate(Date currentDate, int days) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(currentDate);
        int i = 0;
        while (i < days) {
            calendar.add(Calendar.DATE, 1);
            i++;
            if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY ||
                    calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
                i--;
            }
        }
        return calendar.getTime();
    }


    /**
     * @param currentDate
     * @param days
     * @return
     * @author bruce
     * 获取从今天N个工作日以后的具体时间
     */
    public static String getWorkDateTime(int days) {
        Date Ndate = getWorkDate(new Date(), days);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dateFormat.format(Ndate);
    }

    public static String Ten2Two(int number) {
        String result = "";
        int sum = 0;
        for (int i = number; i >= 1; i /= 2) {

            if (i % 2 == 0) {
                sum = 0;
            } else {
                sum = 1;
            }
            result = sum + result;

        }
        log.info(result);
        return result;
    }

//    public static void main(String[] args) {
//        //log.info(getWorkDateTime(11));;
//        String time = 8 + ONE_SECOND_AGO;
////	          String time = 8+ONE_MINUTE_AGO ;  
////	          String time = 8+ONE_HOUR_AGO ;  
////	          String time = 8+ONE_DAY_AGO;  
////	          String time = 8+ONE_MONTH_AGO;  
////	          String time = 8+ONE_YEAR_AGO;  
//        String dateAfterOrBeforeN = getTimeByFanyi(time);
//
//
//        System.out.println(dateAfterOrBeforeN);
//
//
//    }

    /**
     * 根据 几年前|几秒前|几分钱|几天前|几个月前 分析出具体时间啊
     *
     * @param time
     * @return
     */
    public static String getTimeByFanyi(String time) {
        String dateAfterOrBeforeN = "";
        if (time.contains(ONE_YEAR_AGO)) {
            String delta = time.replace(ONE_YEAR_AGO, "");
            System.out.println(delta);
            dateAfterOrBeforeN = TimeUtils.getDateAfterOrBeforeN(TimeUtils.nowTime(), Integer.parseInt("-" + delta), "Y");

        } else if (time.contains(ONE_SECOND_AGO)) {
            String delta = time.replace(ONE_SECOND_AGO, "");
            System.out.println(delta);
            dateAfterOrBeforeN = TimeUtils.getDateAfterOrBeforeN(TimeUtils.nowTime(), Integer.parseInt("-" + delta), "S");

        } else if (time.contains(ONE_MINUTE_AGO)) {
            String delta = time.replace(ONE_MINUTE_AGO, "");
            System.out.println(delta);
            dateAfterOrBeforeN = TimeUtils.getDateAfterOrBeforeN(TimeUtils.nowTime(), Integer.parseInt("-" + delta), "MIN");

        } else if (time.contains(ONE_HOUR_AGO)) {
            String delta = time.replace(ONE_HOUR_AGO, "");
            System.out.println(delta);
            dateAfterOrBeforeN = TimeUtils.getDateAfterOrBeforeN(TimeUtils.nowTime(), Integer.parseInt("-" + delta), "H");

        } else if (time.contains("昨天")) {
            String delta = "1";
            System.out.println(delta);
            dateAfterOrBeforeN = TimeUtils.getDateAfterOrBeforeN(TimeUtils.nowTime(), Integer.parseInt("-" + delta), "D");

        } else if (time.contains(ONE_DAY_AGO)) {
            String delta = time.replace(ONE_DAY_AGO, "");
            System.out.println(delta);
            dateAfterOrBeforeN = TimeUtils.getDateAfterOrBeforeN(TimeUtils.nowTime(), Integer.parseInt("-" + delta), "D");

        } else if (time.contains(ONE_MONTH_AGO)) {
            String delta = time.replace(ONE_MONTH_AGO, "");
            System.out.println(delta);
            dateAfterOrBeforeN = TimeUtils.getDateAfterOrBeforeN(TimeUtils.nowTime(), Integer.parseInt("-" + delta), "M");

        }
        return dateAfterOrBeforeN;
    }


    /**
     * 把2020.01.01 转为 2020-01-01
     * @param date
     * @return
     * @throws ParseException
     */
    public static String pointToSimle(String date) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy.MM.dd");
        Date parse = formatter.parse(date);
        SimpleDateFormat formatter2 = new SimpleDateFormat("yyyy-MM-dd");
        return formatter2.format(parse);
    }

    /**
     * 判断当前时间是否在[startTime, endTime]区间，注意时间格式要一致
     *
     * @param nowTime 当前时间
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @return
     * @author bruce
     */
    public static boolean isEffectiveDate(Date nowTime, Date startTime, Date endTime) {
        if (nowTime.getTime() == startTime.getTime()
                || nowTime.getTime() == endTime.getTime()) {
            return true;
        }

        Calendar date = Calendar.getInstance();
        date.setTime(nowTime);

        Calendar begin = Calendar.getInstance();
        begin.setTime(startTime);

        Calendar end = Calendar.getInstance();
        end.setTime(endTime);

        if (date.after(begin) && date.before(end)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 判断当前时间是否在工作时间点[startTime, endTime]区间，注意时间格式要一致
     *
     * @return
     * @author bruce
     */
    public static boolean isWorkClockTime() {
        boolean flag = false;
        try {
            String format = "HH:mm:ss";
            System.out.println("now clock--"+TimeUtils.nowClock());
            Date nowTime = new SimpleDateFormat(format).parse(TimeUtils.nowClock());
            Date startTime = new SimpleDateFormat(format).parse("09:00:00");
            Date endTime = new SimpleDateFormat(format).parse("19:00:00");
            flag = isEffectiveDate(nowTime, startTime, endTime);
            System.out.println(flag);

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return  flag;
    }


    public static void main(String[] args) throws Exception{
        System.out.println(pointToSimle("2020.01.01"));

    }

}
