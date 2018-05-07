package cn.northpark.weixin.result;


/**
 * Created by kaenry on 2016/9/20.
 * RestResult
 */
public class Result<T> {

    private boolean result;

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

    @Override
    public String toString() {
        return "RestResult{" +
                "result=" + result +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
