package cn.jason.config;

import cn.jason.annotation.RequestParamType;
import cn.jason.service.LogRequestAspect;
import cn.jason.service.ParamLoggingService;
import cn.jason.service.impl.PathVariableLoggingService;
import cn.jason.service.impl.RequestBodyLoggingService;
import cn.jason.service.impl.RequestParamLoggingService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;


@Configuration
@ConditionalOnWebApplication
public class LogRequestAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public RequestParamLoggingService requestParamLoggingService() {
        return new RequestParamLoggingService();
    }

    @Bean
    @ConditionalOnMissingBean
    public PathVariableLoggingService pathVariableLoggingService() {
        return new PathVariableLoggingService();
    }

    @Bean
    @ConditionalOnMissingBean
    public RequestBodyLoggingService requestBodyLoggingService(ObjectMapper objectMapper) {
        return new RequestBodyLoggingService(objectMapper);
    }

    @Bean
    @ConditionalOnMissingBean
    public LogRequestAspect logRequestAspect(
            HttpServletRequest request,
            Map<RequestParamType, ParamLoggingService> loggingServices) {

        return new LogRequestAspect(request, loggingServices);
    }
    @Bean
    public Map<RequestParamType, ParamLoggingService> loggingServices(
            RequestParamLoggingService requestParamLoggingService,
            PathVariableLoggingService pathVariableLoggingService,
            RequestBodyLoggingService requestBodyLoggingService) {
        Map<RequestParamType, ParamLoggingService> services = new HashMap<>();
        services.put(RequestParamType.PARAM, requestParamLoggingService);
        services.put(RequestParamType.VARIABLE, pathVariableLoggingService);
        services.put(RequestParamType.BODY, requestBodyLoggingService);
        return services;
    }
}

