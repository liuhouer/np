package cn.northpark.exception;

/**
 * @author w_zhangyang
 * 事中定义异常错误码和消息
 */
public enum ResultCode {

	
	OK("200","OK"),//请求正常处理完毕
	MOVED("301","Moved Permanently"),//永久重定向，资源已永久分配新URI
	FORBIDDEN("403","Forbidden"),//请求资源被拒绝
    ILLEGAL_PARAMS("400", "request params invalid"),
    NOT_FOUND("404", "404 not found"),
    SERVER_ERROR("500", "server is busy"),//服务器故障或Web应用故障
    UNAVAILABLE("503", "Service Unavailable"),//服务器超负载或停机维护

    //自定义的一些业务错误码追加==============================================================================
    UNKONW_ERROR("-1", "未知错误"),
	DIY("-5","自定義的錯誤"),
	Extend("-6","扩展錯誤");

	ResultCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    private String code;

    private String message;

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
