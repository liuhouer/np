
/**
 * @author bruce
 * @date 2017-12-06
 * @email zhangyang226@gmail.com
 * @site http://blog.northpark.cn | http://northpark.cn | orginazation https://github.com/jellyband
 */
package cn.northpark.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class NorthparkExceptionHandler {
	
	public static final String NorthPark_Error_View = "/error";
	public static final String NorthPark_Build_View = "/building";
	
	private final static Logger logger=Logger.getLogger(ExceptionHandler.class);
	
//	
//	/***
//	 *处理异步方法的异常 
//	 */
//	@ExceptionHandler(value = Exception.class)
//	@ResponseBody
//	public String handle(Exception e) {
//		//处理自定义异常
//		if(e instanceof NorthParkException) {
//			NorthParkException ex = (NorthParkException) e;
//			return JsonUtil.object2json(ResultUitl.error(ex.getCode(), ex.getMessage()));
//		}else {
//			logger.error("【系统异常】{}",e);
//			return JsonUtil.object2json(ResultUitl.error(-1, e.getMessage()));
//		}
//	}
	
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
    	
    	
    	if (isAjax(reqest)) {
    		
    		if (e instanceof NorthParkException) {
    			NorthParkException ex = (NorthParkException) e;
    			return ResultGenerator.genErrorResult(ex.getCode(), ex.getMessage() + ex.getExtendMsg());
    		} else {
    			return ResultGenerator.genErrorResult(ResultCode.SERVER_ERROR.getCode(),ResultCode.SERVER_ERROR.getMessage()+"-->"+e.getMessage());
    		}
    	} else {
    		ModelAndView mav = new ModelAndView();
            mav.addObject("exception", e);
            mav.addObject("url", reqest.getRequestURL());
            mav.setViewName(NorthPark_Error_View);
            logger.error(e);
            return mav;
    	}
    	
		
    }
	
	
	/*** 
     * 响应404 错误 
     * @param ex 
     * @param session 
     * @param request 
     * @param response 
     * @return 
     */  
    @ExceptionHandler(org.springframework.web.servlet.NoHandlerFoundException.class)  
//org.springframework.web.servlet.NoHandlerFoundException: No handler found for GET /agent2/follow/query/json, headers={host=[127.0.0.1:8080], connection=[keep-alive], upgrade-insecure-requests=[1]}  
    public Object handleNotFound404Exception2(org.springframework.web.servlet.NoHandlerFoundException ex,  HttpServletRequest request, HttpServletResponse response) {  
    	ModelAndView mav = new ModelAndView();
        mav.addObject("exception", ex);
        mav.addObject("url", request.getRequestURL());
        mav.setViewName(NorthPark_Build_View);
        return mav;
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
