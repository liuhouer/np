package cn.northpark.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

/**
 时间工具类
 @since 2014-05-23
 @author zhangyang
 *
 */
public class TimeUtils {
	  private static final long ONE_MINUTE = 60000L;  
      private static final long ONE_HOUR = 3600000L;  
      private static final long ONE_DAY = 86400000L;  
      private static final long ONE_WEEK = 604800000L;  
      
      private static final String ONE_SECOND_AGO = "秒前";  
      private static final String ONE_MINUTE_AGO = "分钟前";  
      private static final String ONE_HOUR_AGO = "小时前";  
      private static final String ONE_DAY_AGO = "天前";  
      private static final String ONE_MONTH_AGO = "月前";  
      private static final String ONE_YEAR_AGO = "年前";  
		
		
		//格式化时间串成为  几天前 几秒前 几小时前  几分钟前 几年前sth.....
		public static String formatToNear(String str){
			
				try {
					SimpleDateFormat format  = null;
					if(str.length()>10){
					  format = new SimpleDateFormat("yyyy-MM-dd HH:m:s");  
					}else{
					  format = new SimpleDateFormat("yyyy-MM-dd");  
					}
			        Date date;
					date = format.parse(str);
					str = TimeUtils.format(date);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					str = str;
				}
				
			return str;
			
		}
		
		//格式化时间串成为  几天前 几秒前 几小时前  几分钟前 几年前sth.....
		public static String format(Date date) {  
	        long delta = new Date().getTime() - date.getTime();  
	        if (delta < 1L * ONE_MINUTE) {  
	            long seconds = toSeconds(delta);  
	            return (seconds <= 0 ? 1 : seconds) + ONE_SECOND_AGO;  
	        }  
	        if (delta < 45L * ONE_MINUTE) {  
	            long minutes = toMinutes(delta);  
	            return (minutes <= 0 ? 1 : minutes) + ONE_MINUTE_AGO;  
	        }  
	        if (delta < 24L * ONE_HOUR) {  
	            long hours = toHours(delta);  
	            return (hours <= 0 ? 1 : hours) + ONE_HOUR_AGO;  
	        }  
	        if (delta < 48L * ONE_HOUR) {  
	            return "昨天";  
	        }  
	        if (delta < 30L * ONE_DAY) {  
	            long days = toDays(delta);  
	            return (days <= 0 ? 1 : days) + ONE_DAY_AGO;  
	        }  
	        if (delta < 12L * 4L * ONE_WEEK) {  
	            long months = toMonths(delta);  
	            return (months <= 0 ? 1 : months) + ONE_MONTH_AGO;  
	        } else {  
	            long years = toYears(delta);  
	            return (years <= 0 ? 1 : years) + ONE_YEAR_AGO;  
	        }  
	    }  
	  
	    private static long toSeconds(long date) {  
	        return date / 1000L;  
	    }  
	  
	    private static long toMinutes(long date) {  
	        return toSeconds(date) / 60L;  
	    }  
	  
	    private static long toHours(long date) {  
	        return toMinutes(date) / 60L;  
	    }  
	  
	    private static long toDays(long date) {  
	        return toHours(date) / 24L;  
	    }  
	  
	    private static long toMonths(long date) {  
	        return toDays(date) / 30L;  
	    }  
	  
	    private static long toYears(long date) {  
	        return toMonths(date) / 365L;  
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
		 * 字符串(格式：yyyy-MM-dd hh:mm:ss) --> 毫秒(说明：1970年1月1日0时起到当前字符串时间的毫秒)
		 *
		 * @return 毫秒数(1970年1月1日0时起到当前字符串时间的毫秒)
		 */
		public static long stringToMillis(String source) {
			Date date = null;
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			try {
				date = dateFormat.parse(source);
			} catch (ParseException e) {
				e.printStackTrace();
				date = new Date();
			}
			if(null == date) return 0;
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
			c.add(c.MONTH, n);
			return "" + c.get(c.YEAR) + "-" + (c.get(c.MONTH) + 1) + "-" + c.get(c.DATE);
		}
		
		/**
		 * @author bruce 取得N年以后的日期
		 */
		public static String N_YearDate(int n) {
			Calendar c = Calendar.getInstance();
			c.add(c.YEAR, n);
			return "" + c.get(c.YEAR) + "-" + (c.get(c.MONTH) + 1) + "-" + c.get(c.DATE);
		}

		
		/**
		 * @author bruce 取得N年以后的日期
		 */
		public static String N_YearTime(int n) {
			Calendar c = Calendar.getInstance();
			c.add(c.YEAR, n);
			return "" + c.get(c.YEAR) + "-" + (c.get(c.MONTH) + 1) + "-" + c.get(c.DATE)+" "+c.get(c.HOUR)+":"+c.get(c.MINUTE)+":"+c.get(c.SECOND);
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
		 * @param time
		 *            标准时间字符串（yyyy-MM-dd hh:mm:ss）
		 * @return 月份
		 */
		public static String getMonth(String time) {
			if(null == time) return null;
			if (time.contains("-")) {
				return time.substring(5, 7);
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
				if (str.length > 2) {
					t = str[2];
				}
			}
			return t;
		}

		/**
		 * @author bruce
		 * @function 计算两个时间的差：耗时多少
		 * @param --例子"2004-01-02 11:30:24"
		 * @throws ParseException
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

			return String.valueOf(l);
		}

		/**
		 * @author bruce
		 * @function 计算是否过期,true未过期；false过期
		 * @throws ParseException
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
		 * @desc 取得前一天的时间
		 * @param specifiedDay
		 * @return
		 */
		public static String getDayBefore(String specifiedDay) {
			// SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
			Calendar c = Calendar.getInstance();
			Date date = null;
			try {
				date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(specifiedDay);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			c.setTime(date);
			int day = c.get(Calendar.DATE);
			c.set(Calendar.DATE, day - 1);

			String dayBefore = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(c.getTime());
			return dayBefore;
		}

		/**
		 * @desc 取得后一天的时间
		 * @param specifiedDay
		 * @return
		 */
		public static String getDayAfter(String specifiedDay) {
			Calendar c = Calendar.getInstance();
			Date date = null;
			try {
				date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(specifiedDay);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			c.setTime(date);
			int day = c.get(Calendar.DATE);
			c.set(Calendar.DATE, day + 1);

			String dayAfter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(c.getTime());
			return dayAfter;
		}

		/**
		 * @author bruce
		 * @desc 取得前后N天的时间,N=正负数
		 * @param specifiedDay
		 * @return
		 */
		public static String getDayAfterOrBeforeN(String specifiedDay, int N) {
			Calendar c = Calendar.getInstance();
			Date date = null;
			try {
				date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(specifiedDay);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			c.setTime(date);
			int day = c.get(Calendar.DATE);
			c.set(Calendar.DATE, day + N);

			String dayAfter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(c.getTime());
			return dayAfter;
		}

		/**
		 * @author bruce
		 * @desc 取得前后N分钟后的时间,N=正负数
		 * @param specifiedDay
		 * @return
		 */
		public static String getMinuteAfterOrBeforeN(String specifiedDay, int N) {
			Calendar c = Calendar.getInstance();
			Date date = null;
			try {
				date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(specifiedDay);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			c.setTime(date);
			int minute = c.get(Calendar.MINUTE);
			c.set(Calendar.MINUTE, minute + N);

			String minuteAfter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(c.getTime());
			return minuteAfter;
		}

		/**
		 * @author bruce
		 * @desc 取得当前的年月比如： 1407
		 * @param specifiedDay
		 * @return
		 */
		public static String getYearMonth() {
			Calendar c = Calendar.getInstance();
			c.setTimeInMillis(new Date().getTime());
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyMM");
			return dateFormat.format(c.getTime());
		}
		
		
		/** 
		 * @author bruce
		 * @desc 把long转化成String
	     * 将长时间格式字符串转换为字符串 yyyy-MM-dd HH:mm:ss 
	     * @return 
	     */  
	    public static String  longToString(Long time){  
	        Date date= new Date(time);  
	        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
	        String dateString = formatter.format(date);  
	        //System.out.println("TIME:::"+dateString);  
	        return dateString;  
	    }  
	    
	    /** 
		 * @author bruce
		 * @desc 把long转化成String
	     * 将长时间格式字符串转换为字符串 yyyy-MM-dd HH:mm:ss 
	     * @return 
	     */  
	    public static String  longToDateStrng(Long time){  
	        Date date= new Date(time);  
	        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");  
	        String dateString = formatter.format(date);  
	        System.out.println("TIME:::"+dateString);  
	        return dateString;  
	    }  
	    
	    
	    /**
		 * @desc 随机年07-15
		 * @return
		 */
		public static String getRandomYear(){
			
			String a = "";
			Random ramdom =  new Random();
			int number = -1;
			int max = 15;
			
			//size 为  10 ，取得类似0-9的区间数
			number = Math.abs(ramdom.nextInt() % max)+ 7 ;
			
			if(String.valueOf(number).length()==1){
				a = "20"+0+""+number;
			}else{
				a = "20"+number+"";
			}
			
			return a;
	    
		}
		
		/**
		 * @desc 随机月01-12
		 * @return
		 */
		public static String getRandomMonth(){
			
			String a = "";
			Random ramdom =  new Random();
			int number = -1;
			int max = 12;
			
			//size 为  10 ，取得类似0-9的区间数
			number = Math.abs(ramdom.nextInt() % max  );
			if(String.valueOf(number).length()==1){
				a = 0+""+number;
			}else{
				a = number+"";
			}
			
			return a;
	    
		}
		
		/**
		 * @desc 随机日1-31
		 * @return
		 */
		public static String getRandomDay(){
			
			String a = "";
			Random ramdom =  new Random();
			int number = -1;
			int max = 30;
			
			//size 为  10 ，取得类似0-9的区间数
			number = Math.abs(ramdom.nextInt() % max);
			if(String.valueOf(number).length()==1){
				a = 0+""+number;
			}else{
				a = number+"";
			}
			return a;
	    
		}
		
		
		/**
		 * @desc 随机日期从2007-至今
		 * @return
		 */
		public static String getRandomDate(){
			
			return getRandomYear()+"-"+getRandomMonth()+"-"+getRandomDay();
	    
		}
	    public static void main(String[] args) {
	    	System.out.println(nowdate());;
		}
}
