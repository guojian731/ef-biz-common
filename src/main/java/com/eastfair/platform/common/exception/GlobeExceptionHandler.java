package com.eastfair.platform.common.exception;

import cn.hutool.core.util.StrUtil;
import com.eastfair.platform.common.result.Result;
import com.eastfair.platform.common.result.ResultCodeEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

/**
 * 统一异常处理切面
 * @author Okawa
 * @since 2019/9/25 10:46 上午
 */
@Slf4j
@RestControllerAdvice
public class GlobeExceptionHandler {

    /**
     * 全局异常处理
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    public Result exceptionHandlerHtml(Exception e) {
        log.error("全局异常信息:{}", e.getMessage(), e);
        return  Result.genResult(ResultCodeEnum.SYSTEM_ERROR, StrUtil.sub(e.getMessage(),0,32));
    }

    /**
     * 参数校验异常信息
     * @param exception
     * @return
     */
    @ExceptionHandler({ MethodArgumentNotValidException.class, BindException.class })
    public Result bodyValidExceptionHandler(MethodArgumentNotValidException exception) {
        List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
        log.warn("请求异常:{}", fieldErrors.get(0).getDefaultMessage());
        return Result.genResult(ResultCodeEnum.INVALID_REQUEST_PARAMETE,
                fieldErrors.get(0).getDefaultMessage());
    }

    /**
     * 全局异常处理
     * @param e
     * @return
     */
    @ExceptionHandler(BusinessException.class)
    public Result exceptionHandlerHtml(BusinessException e) {
        log.info("业务异常信息:{}", e.getMessage(), e);
        return Result.genResult(e.getCode(), e.getMessage());
    }

}
