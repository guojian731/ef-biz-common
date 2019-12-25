package com.eastfair.platform.common.constant;

/**
 * @author Okawa
 * @since 2019/11/13 4:45 下午
 */
public interface Constant {

    /**
     * 通过TOKEN加密AES的秘钥
     */
    String ACC_TOKEN_AES_KEY = "AMFgs1vgLYa6T0yx";

    /**
     * srpingsecurity配置
     */
    String BEARER_TYPE = "Bearer";

    /**
     * 日志处理MDC符号
     */
    String MDC_REQNO = "REQNO";
}
