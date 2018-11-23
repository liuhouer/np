package cn.northpark.exception;


public class NorthParkException extends RuntimeException{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	private String code;
	private String extendMsg;
	/**
	 * @author 自定义异常
	 * @param resultEnum自定义错误枚举
	 * 
	 */
	public NorthParkException(ResultCode resultEnum) {
		super(resultEnum.getMessage());
		this.code=resultEnum.getCode();
	}
	
	/**
	 * @author 自定义异常
	 * @param resultEnum自定义错误枚举 +自定义消息 带拼接
	 * 
	 */
	public NorthParkException(ResultCode resultEnum,String extendMsg) {
		super(resultEnum.getMessage());
		this.code = resultEnum.getCode();
		this.extendMsg =  extendMsg;
	}
	
	public String getExtendMsg() {
		return extendMsg;
	}
	public void setExtendMsg(String extendMsg) {
		this.extendMsg = extendMsg;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
}
