package cn.northpark.test.jobtask;

import java.util.Date;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;


public class testjob {

	//将每隔20秒执行一次 
	public static String cronExpression = "0/20 * * * * ?";
	
	

	public static void main(String[] args) throws Exception { 
		//CronTrigger用于处理quartz表达式任务 比如每天的几点执行
		//SimpleTrigger   主要用于处理格时间重复调度

		// 首先，必需要取得一个Scheduler的引用 
		Scheduler scheduler = new StdSchedulerFactory().getScheduler();
		scheduler.start();

		//定义一个job
		JobKey jobKey  = new JobKey("test", "test-1");
		JobDetail jobDetail = JobBuilder.newJob(myjob.class).withIdentity(jobKey).build();

		//定义一个重复触发器 //2秒一次
		Trigger trigger = TriggerBuilder.newTrigger()
				.withIdentity("test","test")
				.startAt(new Date(System.currentTimeMillis()+1000))
				.withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(2).repeatForever())
				.build();




		//定义一个job
		JobKey jobKey2  = new JobKey("test2", "test-2");
		JobDetail jobDetail2 = JobBuilder.newJob(myjob.class).withIdentity(jobKey2).build();
		//定义一个quartz表达式任务 比如每天的几点执行
		Trigger crontrigger = TriggerBuilder.newTrigger()
				.withIdentity("test2","test2")
				.startAt(new Date(System.currentTimeMillis()+1000))
				.withSchedule(CronScheduleBuilder.cronSchedule(cronExpression))
				.build();

		scheduler.scheduleJob(jobDetail, trigger);
		scheduler.scheduleJob(jobDetail2, crontrigger);

		//Thread.sleep(5000); 


	}

}