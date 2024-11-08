package cn.jason.service;

import cn.jason.annotation.LogRequest;
import cn.jason.annotation.RequestParamType;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.Arrays;
import java.util.Map;


@Aspect
public class LogRequestAspect {
    private static final Logger logger = LoggerFactory.getLogger(LogRequestAspect.class);
    private final HttpServletRequest request;
    private final Map<RequestParamType, ParamLoggingService> loggingServices;//存储不同类型参数的日志服务
    @Autowired
    private ObjectMapper objectMapper;


    public LogRequestAspect(HttpServletRequest request, Map<RequestParamType, ParamLoggingService> loggingServices) {
        this.request = request;
        this.loggingServices = loggingServices;
    }

    @Pointcut("@annotation(cn.jason.annotation.LogRequest)")
    public void logRequestPointcut() {}

    @Around("logRequestPointcut() && @annotation(logRequest)")
    public Object logRequestParams(ProceedingJoinPoint joinPoint, LogRequest logRequest) throws Throwable {
        RequestParamType paramType = logRequest.paramType();  // 获取注解中指定的参数类型

        // 动态选择对应的日志服务
        ParamLoggingService loggingService = loggingServices.get(paramType);
        if (loggingService != null) {
            String logMessage = loggingService.formatLog(request, joinPoint.getArgs());  // 生成日志内容
            logger.info(logMessage);  // 将日志写入文件
        }

        // 执行方法并捕获返回值
        Object result;
        try {
            result = joinPoint.proceed();  // 执行目标方法并获取返回值
        } catch (Throwable throwable) {
            logger.error("Error during request processing. Request URL: {} - Params: {}",
                    request.getRequestURI(), Arrays.toString(joinPoint.getArgs()), throwable);
            throw throwable;
        }

        String resultString = (result != null) ? objectMapper.writeValueAsString(result) : "null";
        logger.info("Request URL: {} - 返回参数: {}", request.getRequestURI(), resultString);
        // 返回执行结果
        return result;
    }
}
