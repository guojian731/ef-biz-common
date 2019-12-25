package com.eastfair.platform.common.result;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 响应码枚举，参考HTTP状态码的语义
 * @author Okawa
 * @since 2019/9/25 11:19 上午
 */
@Getter
@AllArgsConstructor
public enum ResultCodeEnum {

    //状态码
    SUCCESS("0000", "成功"),
    FAIL("1111", "失败"),

    //系统
    SYSTEM_ERROR("9999", "系统异常 "),
    INVALID_REQUEST_PARAMETE("4000", "请求参数无效");

    //业务码

    private String code;
    private String message;

}
