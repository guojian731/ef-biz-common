package com.eastfair.platform.common.aspect;

import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import com.eastfair.platform.common.constant.Constant;
import com.eastfair.platform.common.utils.AuthUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Okawa
 * @since 2019/11/13 4:39 下午
 */
@Aspect
@Component
@Order(1)
@Slf4j
public class ReqUserAspect {

    @Pointcut("execution(public * com.eastfair.platform..*.controller.*.*(..))")
    public void conReq() {
    }

    @Before("conReq()")
    public void doBefore() {
        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (null == attributes) {
            return;
        }
        HttpServletRequest request = attributes.getRequest();
        String authorization = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (authorization == null || !authorization.startsWith(Constant.BEARER_TYPE)) {
            return;
        }
        //这里使用用户名称对称加密从新设置了TOKEN
        try {
            String name = SecureUtil.aes(Constant.ACC_TOKEN_AES_KEY.getBytes())
                    .decryptStr(authorization.replace(Constant.BEARER_TYPE, StrUtil.EMPTY).trim());
            AuthUtils.add(name);
        } catch (Exception e) {
            //无需处理
        }
        //放入
    }

    @After("conReq()")
    public void doAfter() {
        //清空
        AuthUtils.remove();
    }
}
