/**
 * 
 */
package com.eastfair.platform.common.log;

import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 
 */
public class BizLog {
    private static Logger log = LoggerFactory.getLogger("BUSI.MONITOR");

    /**
     * 接口监控记录
     *
     * @param apiName
     *            接口名称
     * @param methodName
     *            方法名称
     * @param costTime
     *            花费时间（毫秒）
     * @param respCode
     *            返回码
     * @param respMsg
     *            返回信息
     *            
     */
    public static void logApiEvent(String apiName, String methodName, Long costTime, String respCode, String respMsg) {
        logApiEvent(apiName, methodName, costTime, respCode, respMsg, null);
    }

    /**
     * 接口监控记录
     *
     * @param apiName
     *            接口名称
     * @param methodName
     *            方法名称
     * @param costTime
     *            花费时间（毫秒）
     * @param respCode
     *            返回码
     * @param respMsg
     *            返回信息
     *            
     * @param params
     *            扩展信息传入（不要将以上的入参字段作为key，以及ip，monitorType）
     */
    public static void logApiEvent(String apiName, String methodName, Long costTime, String respCode, String respMsg,
                                   Map<String, Object> params) {
        if (params == null) {
            params = new HashMap<>(16);
        }
        params.put("LogType", LogTypeEnum.INTERFACE.name());
        params.put("apiName", apiName);
        params.put("methodName", methodName);
        params.put("costTime", costTime);
        params.put("respCode", respCode);
        params.put("respMsg", respMsg);
        params.put("traceId", MDC.get(LogConstants.LOGBACK_UUID_FLAG));
        params.put("time", DateUtil.formatDateTime(new Date()));
        removeSensitiveField(params);
        log.info(JSON.toJSONString(params));
    }

    private static void removeSensitiveField(Map<String, Object> params) {
        if (params != null) {
            if (params.containsKey("ip")) {
                params.remove("ip");
            }
            if (params.containsKey("appName")) {
                params.remove("appName");
            }
            if (params.containsKey("hostName")) {
                params.remove("hostName");
            }
            if (params.containsKey("agentType")) {
                params.remove("agentType");
            }
            if (params.containsKey("source")) {
                params.remove("source");
            }
            for (String key : params.keySet()) {
                if (params.get(key) instanceof Date) {
                    params.put(key, DateUtil.formatDateTime(new Date()));
                }

            }
        }
    }

    /**
     * 业务监控记录
     *
     * @param busiScene
     *            业务场景
     * @param partnerNo
     *            合作方编号
     * @param busiTime
     *            业务时间
     * @param busiStatus
     *            业务状态
     * @param busiStatusDesc
     *            业务状态描述
     * @param errcode
     *            错误编码
     * @param errMsg
     *            错误信息
     */
    public static void logBusiEvent(String busiScene, String partnerNo, Date busiTime, String busiStatus,
                                    String busiStatusDesc, String errcode, String errMsg) {
        logBusiEvent(busiScene, partnerNo, busiTime, busiStatus, busiStatusDesc, errcode, errMsg, null);
    }

    /**
     * 业务监控记录
     *
     * @param busiScene
     *            业务场景
     * @param partnerNo
     *            合作方编号
     * @param busiTime
     *            业务时间
     * @param busiStatus
     *            业务状态
     * @param busiStatusDesc
     *            业务状态描述
     * @param errcode
     *            错误编码
     * @param errMsg
     *            错误信息
     * @param params
     *            扩展信息
     */
    public static void logBusiEvent(String busiScene, String partnerNo, Date busiTime, String busiStatus,
                                    String busiStatusDesc, String errcode, String errMsg, Map<String, Object> params) {
        if (params == null) {
            params = new HashMap<>(16);
        }
        params.put("LogType", LogTypeEnum.BUSI.name());
        params.put("busiScene", busiScene);
        params.put("partnerNo", partnerNo);
        params.put("busiTime", busiTime);
        params.put("busiStatus", busiStatus);
        params.put("busiStatusDesc", busiStatusDesc);
        params.put("errcode", errcode);
        params.put("errMsg", errMsg);
        params.put("traceId", MDC.get(LogConstants.LOGBACK_UUID_FLAG));

        params.put("time", DateUtil.formatDateTime(new Date()));
        removeSensitiveField(params);
        log.info(JSON.toJSONString(params));
    }

    /**
     * 定时task日志记录
     *
     * @param taskName
     * @param costTime
     * @param exception
     * @param success
     * @param scount 成功个数
     * @param fcount 失败个数
     */
    public static void logTaskEvent(String taskName, Long costTime, String exception, Boolean success, Integer scount,
                                    Integer fcount) {
        logTaskEvent(taskName, costTime, exception, success, scount, fcount, null);
    }

    /**
     * 定时task日志记录
     *
     * @param taskName
     * @param costTime
     * @param exception
     * @param success
     * @param scount 成功个数
     * @param fcount 失败个数
     * @param params
     */
    public static void logTaskEvent(String taskName, Long costTime, String exception, Boolean success, Integer scount,
                                    Integer fcount, Map<String, Object> params) {
        if (params == null) {
            params = new HashMap<>(16);
        }

        params.put("LogType", LogTypeEnum.TASK.name());
        params.put("taskName", taskName);
        params.put("costTime", costTime);
        params.put("exception", exception);
        params.put("success", success);
        params.put("scount", scount);
        params.put("fcount", fcount);
        params.put("traceId", MDC.get(LogConstants.LOGBACK_UUID_FLAG));
        params.put("time", DateUtil.formatDateTime(new Date()));
        removeSensitiveField(params);
        log.info(JSON.toJSONString(params));
    }

    /**
     * 外部调用监控
     * @param serviceName
     * @param methodName
     * @param costTime
     * @param respCode
     * @param success
     */
    public static void logRpcEvent(String serviceName, String methodName, Long costTime, String respCode,
                                   Boolean success) {
        logRpcEvent(serviceName, methodName, costTime, respCode, success, null);
    }

    /**
     * 外部调用监控
     * @param serviceName
     * @param methodName
     * @param costTime
     * @param respCode
     * @param success
     * @param params
     */
    public static void logRpcEvent(String serviceName, String methodName, Long costTime, String respCode,
                                   Boolean success, Map<String, Object> params) {
        if (params == null) {
            params = new HashMap<>(16);
        }

        params.put("LogType", LogTypeEnum.RPC.name());
        params.put("serviceName", serviceName);
        params.put("methodName", methodName);
        params.put("costTime", costTime);
        params.put("success", success);
        params.put("respCode", respCode);
        params.put("traceId", MDC.get(LogConstants.LOGBACK_UUID_FLAG));
        params.put("time", DateUtil.formatDateTime(new Date()));
        removeSensitiveField(params);
        log.info(JSON.toJSONString(params));
    }

}
