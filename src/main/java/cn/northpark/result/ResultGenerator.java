package cn.northpark.result;

import lombok.extern.slf4j.Slf4j;

/**
 * @author zhangyang.z@iCloud.com
 *	生成通用结果的类
 */
@Slf4j
public class ResultGenerator {


    /**
     * 生成通用结果 --成功/失败
     * @param success
     * @param data
     * @param message
     * @param <T>
     * @return
     */
    public static <T> Result<T> genResult(boolean flag, T data, String message) {
        Result<T> result = Result.newInstance();
        result.setResult(flag);
        result.setData(data);
        result.setMessage(message);
        if (log.isDebugEnabled()) {
            log.debug("generate rest result:{}", result);
        }
        return result;
    }
    
    /**
     * 生成通用结果 --成功/失败
     * @param success
     * @param data
     * @param integer
     * @param message
     * @param <T>
     * @return
     */
    public static <T> Result<T> genResult(boolean flag, T data, Integer code,String message) {
        Result<T> result = Result.newInstance();
        result.setResult(flag);
        result.setData(data);
        result.setMessage(message);
        result.setCode(code);
        if (log.isDebugEnabled()) {
            log.debug("generate rest result:{}", result);
        }
        return result;
    }
    
    /**
     * 生成通用结果 --成功/失败
     * @param success
     * @param data
     * @param code
     * @param message
     * @param <T>
     * @return
     */
    public static <T> Result<T> genResult(boolean flag, T data, ResultEnum enums) {
        Result<T> result = Result.newInstance();
        result.setResult(flag);
        result.setData(data);
        result.setMessage(enums.getMessage());
        result.setCode(enums.getCode());
        if (log.isDebugEnabled()) {
            log.debug("generate rest result:{}", result);
        }
        return result;
    }


    /**
     * 生成成功的消息 --带数据
     * @param data
     * @param <T>
     * @return
     */
    public static <T> Result<T> genSuccessResult(T data) {

        return genResult(true, data, 200, "ok");
    }
    
    /**
     * success no message
     * 生成成功的消息 -不带数据
     * @return
     */
    public static Result<?> genSuccessResult() {
        return genSuccessResult(null);
    }

    /**
     * 生成错误消息
     * @param 错误消息内容Str
     * @param <T>
     * @return
     */
    public static <T> Result<T> genErrorResult(Integer integer,String message) {

        return genResult(false, null, integer, message);
    }

    /**
     * 生成错误消息
     * @param 错误消息枚举码
     * @param <T>
     * @return
     */
    public static <T> Result<T> genErrorResult(ResultEnum error) {

        return genErrorResult(error.getCode(),error.getMessage());
    }


    
}
