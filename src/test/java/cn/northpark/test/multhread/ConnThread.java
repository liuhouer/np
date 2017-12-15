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

import java.sql.Connection;

public class ConnThread implements Runnable {

	
	public  int threadNo;
//	public  CountDownLatch latch;
	
	//设置线程的编号
	public ConnThread(int threadNo){
		this.threadNo = threadNo;
//		this.latch = latch;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		// 建立连接，并且请求存储过程
		Connection cc = null;
		try {
			cc = db.getConnection();
			
			
			//每个线程查询一次
			db.callProcedureYY(cc,"cms.sp_get_fundasset",threadNo);
			//存放结果
			
			
			System.out.println("线程"+threadNo+"执行完毕");
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			db.close(cc);
		}

	}

}
