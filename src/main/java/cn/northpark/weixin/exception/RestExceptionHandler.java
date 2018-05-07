package cn.northpark.weixin.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import cn.northpark.weixin.result.ResultErrorCode;
import cn.northpark.weixin.result.Result;
import cn.northpark.weixin.result.ResultGenerator;

/**
 * 
 * RestExceptionHandler
 */
@ControllerAdvice
public class RestExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(RestExceptionHandler.class);

    
    
    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    private <T> Result<T> runtimeExceptionHandler(Exception e) {
        LOGGER.error("---------> 500 error!", e);
        return ResultGenerator.genErrorResult(ResultErrorCode.SERVER_ERROR);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    private <T> Result<T> illegalParamsExceptionHandler(MethodArgumentNotValidException e) {
        LOGGER.error("---------> invalid request!", e);
        return ResultGenerator.genErrorResult(ResultErrorCode.ILLEGAL_PARAMS);
    }
    

}
