/***************************************************************************/
/*                                                                         */
/* 			Copyright (c) TAIKANG ASSET MANAGEMENT CO., LTD.               */
/*                 泰康资产管理有限责任公司    版权所有		                   */
/*                                                                         */
/* PROPRIETARY RIGHTS of TAIKANG ASSET MANAGEMENT CO.,LTD. are involved in */
/* the subject matter of this material.  All manufacturing, reproduction,  */
/* use, and sales rights pertaining to this subject matter are governed by */
/* the license agreement.  The recipient of this software implicitly  	   */
/* accept the terms of the license.                                        */
/* 本软件文档资料是泰康资产管理有限责任公司的资产,任何人士阅读使用必须获得         */
/* 相应的书面授权,承担保密责任和接受相应的法律约束.                           */
/*                                                                         */
/***************************************************************************/
/************************************************************
  Copyright (C), TAIKANG ASSET MANAGEMENT. Co., Ltd.
  FileName: ConnThread.java
  Author: 张洋       Version : 1.0         Date:2017年11月8日
  Description:     // 模块描述      
  Version:         // 版本信息
  History:         // 历史修改记录
      <author>  <time>   <version >   <desc>
***********************************************************/
package cn.northpark.test.multhread;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import cn.northpark.utils.TimeUtils;

public class test {

	
	
	
	public static final int CONN_COUNT  = 1500;
	/***
	 * 
	 * 张洋、胡彦伟，你们好： 请在流动性平台测试环境cmstest实现下述功能，用以测试： 1、
	 * 胡彦伟：参考恒生函数trade.sf_avg_price_new实现流动性平台函数cms.sf_avg_price_new 2、
	 * 胡彦伟：参考恒生存储过程trade.sp_refresh_fundasset，实现流动性平台存储过程cms.sp_get_fundasset，输入参数是l_fund_id，
	 * 返回当前最新计算的账户净值，注意函数实现逻辑中，返回的净值不需要更新表cms.t_fund_asset，也不要设置从表直接取数的间隔时间（即始终是强制刷新）；
	 * 3、 张洋：实现一个java
	 * standalone应用，开辟静态HashMap（500），创建500个线程，序号从1到500，每个线程都会单独建立数据库连接访问流动性平台测试环境cmstest，
	 * 调用数据库函数cms.sp_get_fundasset，传入线程序号作为参数。数据库函数返回的账户净值按序号写入对应的静态HashMap。统计500个线程全部结束后消耗的时间总长；
	 * 
	 * 流动性平台测试环境cmstest数据库信息： 用户名/密码: cms/cmsdb newcmstest = (DESCRIPTION =
	 * (ADDRESS_LIST = (ADDRESS = (PROTOCOL = TCP)(HOST = 10.88.147.6)(PORT =
	 * 1521)) ) (CONNECT_DATA = (SERVICE_NAME = cmstest) ) )
	 * 
	 * 
	 */

	
	
	
	public static void main(String[] args) {
		
		ExecutorService executorService = Executors.newFixedThreadPool(200);
		try {
			//清空已有信息
			
			String start = TimeUtils.nowTime();
			System.out.println("开始时间："+start);
			
			ArrayList<Runnable> threads = new ArrayList<Runnable>();
			
			
			for (int i = 1; i <= CONN_COUNT; i++) {
				threads.add(new ConnThread(i));
			}
			
//			//开启线程
			//测试线程池
			for (Runnable r:threads) {
				executorService.execute(r);
			}
			
			
			executorService.shutdown();
			
			while(!executorService.isTerminated()){
				
			}
			
			
			executorService.shutdownNow();
			
			String end = TimeUtils.nowTime();
			System.out.println("结束时间："+end);
			
//			System.out.println(map.size()+">>>>>"+map);
			System.out.println(TimeUtils.getPastTime(end, start));
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		
	}
}
