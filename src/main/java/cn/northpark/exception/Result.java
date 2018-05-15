package cn.northpark.exception;


/**
 * @author w_zhangyang
 * 通用的返回结果
 * @param <T>
 */
public class Result<T> {

    private boolean result;
    
    private String code;

    private String message;

    private T data;

    private Result() {}

    public static <T> Result<T> newInstance() {
        return new Result<>();
    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Override
	public String toString() {
		return "Result [result=" + result + ", code=" + code + ", message=" + message + ", data=" + data + "]";
	}
}
