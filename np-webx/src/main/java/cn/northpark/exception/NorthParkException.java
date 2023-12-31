package cn.northpark.exception;

import cn.northpark.result.ResultEnum;
import lombok.Getter;

@Getter
public class NorthParkException extends RuntimeException{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	private Integer code;
	private String extendMsg;
	/**
	 * @author 自定义异常
	 * @param resultEnum 自定义错误枚举
	 * 
	 */
	public NorthParkException(ResultEnum resultEnum) {
		super(resultEnum.getMessage());
		this.code=resultEnum.getCode();
	}
	
	/**
	 * @author 自定义异常
	 * @param resultEnum 自定义错误枚举 +自定义消息 带拼接
	 * 
	 */
	public NorthParkException(ResultEnum resultEnum,String extendMsg) {
		super(resultEnum.getMessage());
		this.code = resultEnum.getCode();
		this.extendMsg =  extendMsg;
	}
	
}
