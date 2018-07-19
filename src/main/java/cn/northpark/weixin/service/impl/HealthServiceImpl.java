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
  FileName: ComplianceServiceI.java
  Author: 李云鹏        Version : 1.0         Date:2017年10月16日
  Description:     // 模块描述      
  Version:         // 版本信息
  History:         // 历史修改记录
      <author>  <time>   <version >   <desc>
***********************************************************/
package cn.northpark.weixin.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.northpark.weixin.dao.HealthMapper;
import cn.northpark.weixin.entity.Health;
import cn.northpark.weixin.service.HealthServiceI;

@Service
public class HealthServiceImpl implements  HealthServiceI{
	

	@Autowired
	private HealthMapper healthMapper;
	
	@Override
	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return healthMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(Health record) {
		// TODO Auto-generated method stub
		return healthMapper.insert(record);
	}

	@Override
	public Health selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return healthMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<Health> selectAll() {
		// TODO Auto-generated method stub
		return healthMapper.selectAll();
	}

	@Override
	public int updateByPrimaryKey(Health record) {
		// TODO Auto-generated method stub
		return healthMapper.updateByPrimaryKey(record);
	}


}
