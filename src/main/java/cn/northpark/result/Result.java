package cn.northpark.result;

import lombok.Data;

/**
 * @author zhangyang.z@iCloud.com
 * 通用的返回结果
 * @param <T>
 */
@Data
public class Result<T> {

    private boolean result;
    
    private Integer code;

    private String message;

    private T data;

    private Result() {}

    public static <T> Result<T> newInstance() {
        return new Result<>();
    }

}
