package com.eastfair.platform.common.result;

import lombok.*;

import java.io.Serializable;

/**
 * 统一API响应结果封装
 * 说明:
 *  当状态码是成功时候子业务码可能没有,
 *  当状态码是失败时候子业务码一定存在
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Result<T> implements Serializable {

    private static final long serialVersionUID = 7782876884534928711L;
    /**
     * 状态码(是必填)
     */
    private String code;
    /**
     * 状态描述(是必填)
     */
    private String msg;
    /**
     * 子业务码(否必填)
     */
    private String subCode;
    /**
     * 子业务描述(否必填)
     */
    private String subMsg;
    /**
     * 数据(否必填)
     */
    private T      data;


    public static <T> Result<T> genSuccess() {
        return Result.<T> builder().code(ResultCodeEnum.SUCCESS.getCode()).msg(ResultCodeEnum.SUCCESS.getMessage())
                .build();
    }

    public static <T> Result<T> genSuccess(T data) {
        return Result.<T> builder().code(ResultCodeEnum.SUCCESS.getCode()).msg(ResultCodeEnum.SUCCESS.getMessage())
                .data(data).build();
    }

    public static Result genResult(ResultCodeEnum code) {
        return Result.builder().code(code.getCode()).msg(code.getMessage()).build();
    }

    public static Result genResult(ResultCodeEnum code, String msg) {
        return Result.builder().code(code.getCode()).msg(msg).build();
    }

    public static Result genResult(String code, String msg) {
        return Result.builder().code(code).msg(msg).build();
    }

    public static Result genFailResult(ResultCodeEnum code,String subCode, String subMsg) {
        return Result.builder().code(code.getCode()).msg(code.getMessage()).subCode(subCode).subMsg(subMsg).build();
    }

    public static Boolean checkResultSuccess(Result result){
        if (null==result){
            return false;
        }
        if (ResultCodeEnum.SUCCESS.getCode().equals(result.getCode())){
            return true;
        }
        return false;
    }


}
