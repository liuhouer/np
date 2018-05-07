package cn.northpark.weixin.result;

/**
 * Created by kaenry on 2016/9/20.
 * ErrorCode
 */
public enum ResultErrorCode {

    ILLEGAL_PARAMS("ILLEGAL_PARAMS", "request params invalid"),
    SERVER_ERROR("SERVER_ERROR", "server is busy");

    ResultErrorCode(String code, String message) {
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
