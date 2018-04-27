package cn.northpark.exception;

/**
 * Created by bruce
 * 2018-4-27
 */
public enum ResultEnum {
    UNKONW_ERROR(-1, "未知错误"),
    SUCCESS(0, "成功"),
    zidingyi(-5,"自定義的錯誤"),
    kuozhan(-6,"扩展錯誤"),
    ;

    private Integer code;

    private String msg;

    ResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
