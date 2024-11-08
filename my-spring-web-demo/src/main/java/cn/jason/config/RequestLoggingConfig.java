package cn.jason.config;

import cn.jason.annotation.RequestParamType;
import cn.jason.service.LogRequestAspect;
import cn.jason.service.ParamLoggingService;
import cn.jason.service.RequestLoggingService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;
/**
 * @describe:配置自定义日志服务
 * @Author JasonZhang
 * @Date 2024/11/6
**/
@Configuration
public class RequestLoggingConfig {
//    @Bean
//    public RequestLoggingService customRequestParamLoggingService() {
//        return new RequestLoggingService();
//    }
//
//    @Bean
//    public LogRequestAspect logRequestAspect(
//            HttpServletRequest request,
//            Map<RequestParamType, ParamLoggingService> loggingServices) {
//
//        // 在这里配置自定义日志服务
//        loggingServices.put(RequestParamType.PARAM, new RequestLoggingService());
//
//        return new LogRequestAspect(request, loggingServices);
//    }
}
