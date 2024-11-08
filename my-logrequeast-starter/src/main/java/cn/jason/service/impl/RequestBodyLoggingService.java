package cn.jason.service.impl;

import cn.jason.service.ParamLoggingService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;

public class RequestBodyLoggingService implements ParamLoggingService {

    private final ObjectMapper objectMapper;

    public RequestBodyLoggingService(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public String formatLog(HttpServletRequest request, Object[] args) {
        try {
            String body = objectMapper.writeValueAsString(args[0]);
            return String.format("Request Method: %s - Request URL: %s - Body入参: %s",request.getMethod(), request.getRequestURI(), body);
        } catch (Exception e) {
            return "Error serializing body";
        }
    }
}
