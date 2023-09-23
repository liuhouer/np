package cn.northpark.YI;

import cn.northpark.utils.TimeUtils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 本工具原本是用于安卓日历小程序的，现在工程暂时搁置了，就把这个生成八字的方法发出来。
 * 测试类
 *
 */
public class demo {
	
	public static void main(String[] args) throws Exception {
		// 获取当前日期时间
		String prefixDay = TimeUtils.nowdate();

		// 创建格式化类，设定格式为 yyyy-MM-dd HH:mm:ss
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		// 解析日期时间为字符串
		String currentDateTimeString =  prefixDay+" 21:15:00";

		// 输出当前日期时间
		System.out.println("当前日期时间: " + currentDateTimeString);

		// 创建Bazi对象，传入当前日期时间参数
		Date currentDate = sdf.parse(currentDateTimeString);
		Bazi test = new Bazi(currentDate);

		// 输出完整八字
		System.out.println(test.getTimeTG());
	}
	
}
