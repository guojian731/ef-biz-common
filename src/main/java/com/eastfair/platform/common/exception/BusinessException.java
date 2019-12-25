package com.eastfair.platform.common.exception;

import cn.hutool.core.util.StrUtil;
import com.eastfair.platform.common.result.Result;

import java.text.MessageFormat;

/**
 * 业务异常
 * @author Okawa
 * @since 2019/10/16 5:02 下午
 */
public class BusinessException extends RuntimeException {
    private static final long serialVersionUID = -7333814332541446608L;
    private final String      code;
    private final String      message;

    public BusinessException(Result result, Object... args) {
        super(MessageFormat.format(result.getMsg(), args));
        this.code = result.getCode();
        if (null != args && args.length != 0 && StrUtil.isNotBlank(MessageFormat.format(result.getMsg(), args))) {
            this.message = MessageFormat.format(result.getMsg(), args);
        } else {
            this.message = result.getMsg();
        }

    }

    public BusinessException(String code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }

    public BusinessException(ExceptionBuild exceptionBuild) {
        super(exceptionBuild.getMessage());
        this.code = exceptionBuild.getCode();
        this.message = exceptionBuild.getMessage();
    }

    public String getCode() {
        return this.code;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
