package cn.northpark.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author w_zhangyang
 *	生成通用结果的类
 */
public class ResultGenerator {

    private static final Logger LOGGER = LoggerFactory.getLogger(ResultGenerator.class);

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
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("generate rest result:{}", result);
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
    public static <T> Result<T> genResult(boolean flag, T data, String code,String message) {
        Result<T> result = Result.newInstance();
        result.setResult(flag);
        result.setData(data);
        result.setMessage(message);
        result.setCode(code);
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("generate rest result:{}", result);
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
    public static <T> Result<T> genResult(boolean flag, T data, ResultCode enums) {
        Result<T> result = Result.newInstance();
        result.setResult(flag);
        result.setData(data);
        result.setMessage(enums.getMessage());
        result.setCode(enums.getCode());
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("generate rest result:{}", result);
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

        return genResult(true, data, "200", "ok");
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
    public static <T> Result<T> genErrorResult(String code,String message) {

        return genResult(false, null, code, message);
    }

    /**
     * 生成错误消息
     * @param 错误消息枚举码
     * @param <T>
     * @return
     */
    public static <T> Result<T> genErrorResult(ResultCode error) {

        return genErrorResult(error.getCode(),error.getMessage());
    }

    
}
