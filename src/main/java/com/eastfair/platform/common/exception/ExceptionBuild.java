package com.eastfair.platform.common.exception;

import org.slf4j.helpers.MessageFormatter;

import com.eastfair.platform.common.result.Result;

public class ExceptionBuild {

    private String code;
    private String message;

    public ExceptionBuild() {
    }

    public ExceptionBuild(String code, String desc) {
        this.code = code;
        this.message = desc;
    }

    public static ExceptionBuild build(Result<?> errcode, String errrmsg, String... msg) {
        ExceptionBuild exceptionBuild = new ExceptionBuild();
        exceptionBuild.setCode(errcode.getCode());
        exceptionBuild.setMessage(MessageFormatter.arrayFormat(errrmsg, msg).getMessage());
        return exceptionBuild;
    }

    public static ExceptionBuild build(Result<?> errcode) {
        return build(errcode, "", "");
    }

    public ExceptionBuild throwBusinessException() {
        throw new BusinessException(this.code, this.message);
    }

    public static void buildAndThrow(Result<?> errcode, String errrmsg, String... msg) {
        ExceptionBuild exceptionBuild = build(errcode, errrmsg, msg);
        throw new BusinessException(exceptionBuild);
    }

    public static void buildAndThrow(Result<?> errcode) {
        buildAndThrow(errcode, "", "");
    }

    @Override
    public String toString() {
        return "ExceptionBuild{code='" + this.code + '\'' + ", message='" + this.message + '\'' + '}';
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
