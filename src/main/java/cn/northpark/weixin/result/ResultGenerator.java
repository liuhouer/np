package cn.northpark.weixin.result;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by kaenry on 2016/9/20.
 * RestResultGenerator
 */
public class ResultGenerator {

    private static final Logger LOGGER = LoggerFactory.getLogger(ResultGenerator.class);

    /**
     * normal
     * @param success
     * @param data
     * @param message
     * @param <T>
     * @return
     */
    public static <T> Result<T> genResult(boolean success, T data, String message) {
        Result<T> result = Result.newInstance();
        result.setResult(success);
        result.setData(data);
        result.setMessage(message);
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("generate rest result:{}", result);
        }
        return result;
    }

    /**
     * success
     * @param data
     * @param <T>
     * @return
     */
    public static <T> Result<T> genSuccessResult(T data) {

        return genResult(true, data, null);
    }

    /**
     * error message
     * @param message error message
     * @param <T>
     * @return
     */
    public static <T> Result<T> genErrorResult(String message) {

        return genResult(false, null, message);
    }

    /**
     * error
     * @param error error enum
     * @param <T>
     * @return
     */
    public static <T> Result<T> genErrorResult(ResultErrorCode error) {

        return genErrorResult(error.getMessage());
    }

    /**
     * success no message
     * @return
     */
    public static Result<?> genSuccessResult() {
        return genSuccessResult(null);
    }
}
