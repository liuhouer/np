package cn.northpark.YI;

import cn.northpark.utils.TimeUtils;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

/**
 * 本工具原本是用于安卓日历小程序的，现在工程暂时搁置了，就把这个生成八字的方法发出来。
 * 测试类
 *
 */
public class PredictLuckNumberApp {
	
	public static void main(String[] args) throws Exception {
		// 获取当前日期时间
		// 获取当前日期
		LocalDate currentDate = LocalDate.now();

		// 计算前面10天的日期
		LocalDate startDate = currentDate.minusDays(10);

		// 定义日期格式化器
		DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

		// 格式化日期为 "yyyy-MM-dd" 格式
		for (int i = 0; i < 10; i++) {
			String formattedDate = startDate.plusDays(i).format(dateFormatter);
			System.out.println(formattedDate);

			predictNumbers(formattedDate);
		}



	}

	private static void predictNumbers(String prefixDay) throws Exception {
		// 创建格式化类，设定格式为 yyyy-MM-dd HH:mm:ss
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		// 解析日期时间为字符串
		String currentDateTimeString =  prefixDay +" 21:15:00";

		// 输出当前日期时间
		System.out.println("当前日期时间: " + currentDateTimeString);

		// 创建Bazi对象，传入当前日期时间参数
		Date currentDate = sdf.parse(currentDateTimeString);
		Bazi test = new Bazi(currentDate);

		// 输出时支八字
		String timeTG = test.getTimeTG();
		System.out.println(timeTG);

		// 获取幸运的标题
		TwelveZodiac twelveZodiac = new TwelveZodiac();
		List<String> luckestColByRowName = twelveZodiac.getLuckestColByRowName(timeTG);

		// 获取河图洛书编号
		String heLuoNo = HeTuLuoShu.getHeLuoNo(luckestColByRowName);
		System.out.println("今日幸运数字: " + heLuoNo);

		StringBuilder sb = new StringBuilder();
		sb.append("当前日期时间: " + currentDateTimeString);
		sb.append("\n");
		sb.append("今日幸运数字: " + heLuoNo);
//		String sendEMAIL = EmailUtils.getInstance().sendEMAIL("654714226@qq.com", "今日预测3D幸运数字", sb.toString());
//
//		System.err.println("sendEMAIL flag====="+sendEMAIL);
	}

}
