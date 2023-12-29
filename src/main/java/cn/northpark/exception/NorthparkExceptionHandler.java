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

import cn.northpark.result.ResultEnum;
import cn.northpark.result.ResultGenerator;
import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@Slf4j
public class NorthparkExceptionHandler {
	
	
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

		if (e instanceof NorthParkException) {
			NorthParkException ex = (NorthParkException) e;
			return ResultGenerator.genErrorResult(ex.getCode(), ex.getMessage() + ex.getExtendMsg());
		} else {
			return ResultGenerator.genErrorResult(ResultEnum.SERVER_ERROR.getCode(),e.getMessage());
		}


	}
	
	
	
	
	
	
	
}
