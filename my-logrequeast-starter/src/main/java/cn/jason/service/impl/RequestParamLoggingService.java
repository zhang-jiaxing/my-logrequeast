package cn.jason.service.impl;

import cn.jason.service.ParamLoggingService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class RequestParamLoggingService implements ParamLoggingService {
    @Override
    public String formatLog(HttpServletRequest request, Object[] args) {
        String params = IntStream.range(0, args.length)
                .mapToObj(i -> "param" + i + "=" + args[i])
                .collect(Collectors.joining(", "));
        return String.format("Request Method: %s - Request URL: %s - param入参: %s",request.getMethod(), request.getRequestURI(), params);
    }
}
