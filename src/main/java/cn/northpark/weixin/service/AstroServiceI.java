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
package cn.northpark.weixin.service;

import java.util.List;

import cn.northpark.weixin.entity.Astro;

public interface AstroServiceI {
	
	/**
     * 插入附件的数据
     * 
     */
    int insertAstro(Astro record);
    
    
    /**
     * 	根据附件的表名搜索
     */
    List<Astro> selectAstroByName(String  name);
    
    /**
     * 查询全部附件的数据
     * 
     */
    List<Astro> selectAllAstro();
    
    
    /**
     * 查询某个email下的附件
     * 
     */
    List<Astro> selectAstrosByEmail(Long emailid);
    
    /**
     * 更新附件数据
     * 
     */
    int updateAstro(Astro record);
    
    /**
     * 删除table数据 -根据tablename删除
     * 
     */
    int deleteAstroByAstroName(String tablename);
    
    /**
     * 删除table数据 -根据tableid删除
     * 
     */
    int deleteAstro(Long tableid);

}
