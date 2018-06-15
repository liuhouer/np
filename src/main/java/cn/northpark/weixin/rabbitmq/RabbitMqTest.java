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
  FileName: RabbitMqTest.java
  Author: 张洋       Version : 1.0         Date:2018年6月15日
  Description:     // 模块描述      
  Version:         // 版本信息
  History:         // 历史修改记录
      <author>  <time>   <version >   <desc>
 ***********************************************************/
package cn.northpark.weixin.rabbitmq;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RabbitMqTest {

	@Autowired
	private NpSender helloSender;

	@Test
	public void hello() throws Exception {
		helloSender.send();
	}

}