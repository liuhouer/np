package cn.northpark.result;

import lombok.Getter;

/**
 *
 * @author  zhangyang.z@iCloud.com
 * 定义异常错误码和消息
 */
@Getter
public enum  ResultEnum {
	OK(200,"OK"),//请求正常处理完毕
	MOVED(301,"Moved Permanently"),//永久重定向，资源已永久分配新URI
	FORBIDDEN(403,"Forbidden"),//请求资源被拒绝
    ILLEGAL_PARAMS(400, "request params invalid"),
    NOT_FOUND(404, "404 not found"),
    SERVER_ERROR(500, "server is busy"),//服务器故障或Web应用故障
    UNAVAILABLE(503, "Service Unavailable"),//服务器超负载或停机维护

    //自定义的一些业务错误码追加==============================================================================
	Movie_Tag_Not_Match(-7,"{电影标签中英文数量不匹配，对应不上}{The Movie Tags With Tagscode Not Match}"),
	Param_Not_Valid(-8,"管理员相关方法竟然参数没有传递正常！需要记录IP，防止是恶意扫描。"),
	Lyrics_Param_Not_Valid(-9,"添加主题的参数不正确"),
	Login_Fail(1,"用户名密码错误"),
	Login_Email_Validate_Fail(2,"邮箱未通过验证，请重试或者联系站长解决登陆问题"),
	Login_Success(3,"登陆成功"), 
	REG_Fail_Repeat(4,"该账户已经注册"), 
	REG_SUCCESS(5,"success"), 
	
	;

    private Integer code;

    private String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

}
