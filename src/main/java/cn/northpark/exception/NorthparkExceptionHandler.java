/**
 * @author bruce
 * @date 2017-12-06
 * @email zhangyang226@gmail.com
 * @site http://blog.northpark.cn | http://northpark.cn | orginazation https://github.com/jellyband
 */
package cn.northpark.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;

import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@Slf4j
public class NorthparkExceptionHandler {
	
	public static final String NorthPark_Error_View = "/error";
	public static final String NorthPark_Build_View = "/building";
	
	
	
	/**
	 * 描述：处理所有的异常
	 *
	 * @param reqest
	 * @param response
	 * @param e
	 * @return
	 * @throws Exception
	 */
	@ExceptionHandler(value = Exception.class)
	@ResponseBody
    public Object errorHandler(HttpServletRequest reqest, 
    		HttpServletResponse response, Exception e) throws Exception {
		
		log.error("The Error Found By Northpark=====>{}",e);
    	
    	if (isAjax(reqest)) {
    		
    		if (e instanceof NorthParkException) {
    			NorthParkException ex = (NorthParkException) e;
    			return ResultGenerator.genErrorResult(ex.getCode(), ex.getMessage() + ex.getExtendMsg());
    		} else {
    			return ResultGenerator.genErrorResult(ResultCode.SERVER_ERROR.getCode(),ResultCode.SERVER_ERROR.getMessage()+"-->"+e.getMessage());
    		}
    	} else {
    		String exception = "";
    		if (e instanceof NorthParkException) {
    			NorthParkException ex = (NorthParkException) e;
    			exception =  JSON.toJSONString(ResultGenerator.genErrorResult(ex.getCode(), ex.getMessage() + ex.getExtendMsg()));
    		} else {
    			exception =  JSON.toJSONString(ResultGenerator.genErrorResult(ResultCode.SERVER_ERROR.getCode(),ResultCode.SERVER_ERROR.getMessage()+"-->"+e.getMessage()));
    		}
    		ModelAndView mav = new ModelAndView();
            mav.addObject("exception", exception);
            mav.addObject("url", reqest.getRequestURL());
            mav.setViewName(NorthPark_Error_View);
            return mav;
    	}
    	
		
    }
	
	
	
	/**
	 * 
	 * @Description: 判断是否是ajax请求
	 * Copyright: Copyright (c) 2018
	 * 
	 * @author bruce
	 * @date 2018年5月2日
	 */
	public static boolean isAjax(HttpServletRequest httpRequest){
		return  (httpRequest.getHeader("X-Requested-With") != null  
					&& "XMLHttpRequest"
						.equals( httpRequest.getHeader("X-Requested-With").toString()) );
	}
	
	
	
	
}
