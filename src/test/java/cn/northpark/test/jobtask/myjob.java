package cn.northpark.test.jobtask;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import cn.northpark.utils.TimeUtils;

public class myjob implements Job {



	public myjob() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		// TODO Auto-generated method stub
		System.out.println(TimeUtils.nowTime());
	}

}