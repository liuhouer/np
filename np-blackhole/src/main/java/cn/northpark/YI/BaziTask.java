package cn.northpark.YI;

import cn.northpark.utils.TimeUtils;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author zhangyang
 * @date 2023年09月23日 20:23:35
 */
@Component
public class BaziTask {

    @Scheduled(cron = "0 0 7 * * ?", zone = "GMT+8")
    public void executeBaziTask() {
        try {
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
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}