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
  FileName: ResultUitl.java
  Author: 张洋       Version : 1.0         Date:2018年4月27日
  Description:     // 模块描述      
  Version:         // 版本信息
  History:         // 历史修改记录
      <author>  <time>   <version >   <desc>
***********************************************************/
package cn.northpark.exception;


public class ResultUitl {
	
	public static Result success(Object object) {
		Result result=new Result();
		result.setCode(0);
		result.setMsg("成功");
		result.setData(object);
		return result;
	}
	
	public static Result success() {
		return success(null);
	}
	
	public static Result error(Integer code,String mes) {
		Result result=new Result();
		result.setCode(code);
		result.setMsg(mes);
		return result;
	}
	
//	抛出异常
//	 public void getAge(Integer id) throws Exception{
//        Girl girl = girlRepository.findOne(id);
//        Integer age = girl.getAge();
//        if (age < 10) {
//            //返回"你还在上小学吧" code=100
//            throw new GirlException(ResultEnum.PRIMARY_SCHOOL);
//        }else if (age > 10 && age < 16) {
//            //返回"你可能在上初中" code=101
//            throw new GirlException(ResultEnum.MIDDLE_SCHOOL);
//        }
//
//        //如果>16岁,加钱
//        //...
//    }
	
//	 /**
//     * 添加一个女生
//     * @return
//     */
//    @PostMapping(value = "/girls")
//    public Result<Girl> girlAdd(@Valid Girl girl, BindingResult bindingResult) {
//        if (bindingResult.hasErrors()) {
//            return ResultUtil.error(1, bindingResult.getFieldError().getDefaultMessage());
//        }
//
//        girl.setCupSize(girl.getCupSize());
//        girl.setAge(girl.getAge());
//
//        return ResultUtil.success(girlRepository.save(girl));
//    }

	
	
}
