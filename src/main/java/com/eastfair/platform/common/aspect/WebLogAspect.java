package com.eastfair.platform.common.aspect;

import com.eastfair.platform.common.constant.Constant;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.MDC;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.UUID;

/**
 * 对外接口出入参切面
 * @author Okawa
 * @since 2019/9/25 10:46 上午
 */
@Aspect
@Component
@Order(-1)
@Slf4j
public class WebLogAspect {

    private ThreadLocal<Long> startTime = new ThreadLocal<>();

    @Pointcut("execution(public * com.eastfair.platform..*.apiImpl.*.*(..))")
    public void apiLog() {
    }

    @Pointcut("execution(public * com.eastfair.platform..*.controller.*.*(..))")
    public void conLog() {
    }

    @Pointcut("apiLog()||conLog()")
    public void webLog() {
    }

    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint) {

        try {
            // 业务日志MDC处理
            MDC.put(Constant.MDC_REQNO, UUID.randomUUID().toString());
        } catch (IllegalArgumentException e) {
            log.error(e.getMessage(), e);
        }

        startTime.set(System.currentTimeMillis());
        log.info("CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "."
                + joinPoint.getSignature().getName());
        log.info("ARGS : " + Arrays.toString(joinPoint.getArgs()));
    }

    @AfterReturning(returning = "ret", pointcut = "webLog()")
    public void doAfterReturning(Object ret) {

        // 处理完请求，返回内容
        log.info("RESPONSE : " + ret);
        log.info("SPEND TIME : " + (System.currentTimeMillis() - startTime.get()));
        startTime.remove();

        try {
            //清空MDC处理
            MDC.remove(Constant.MDC_REQNO);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }
}
