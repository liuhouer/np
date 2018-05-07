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

import cn.northpark.weixin.dao.AstroMapper;
import cn.northpark.weixin.entity.Astro;
import cn.northpark.weixin.service.AstroServiceI;

@Service
public class AstroServiceImpl implements  AstroServiceI{
	
	@Autowired
	private AstroMapper astroMapper; // astroMapper

	@Override
	public int insertAstro(Astro record) {
		// TODO Auto-generated method stub
		return astroMapper.insert(record);
	}

	@Override
	public List<Astro> selectAstroByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Astro> selectAllAstro() {
		// TODO Auto-generated method stub
		return astroMapper.selectAll();
	}

	@Override
	public List<Astro> selectAstrosByEmail(Long emailid) {
		// TODO Auto-generated method stub
//		return astroMapper.selectAstrosByEmail(emailid);
		return null;
	}

	@Override
	public int updateAstro(Astro record) {
		// TODO Auto-generated method stub
		return astroMapper.updateByPrimaryKey(record);
	}

	@Override
	public int deleteAstroByAstroName(String tablename) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteAstro(Long tableid) {
		// TODO Auto-generated method stub
//		return astroMapper.deleteByPrimaryKey(tableid);
		return 0;
	}


}
