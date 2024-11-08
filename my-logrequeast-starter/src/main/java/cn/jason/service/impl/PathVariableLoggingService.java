package cn.jason.service.impl;

import cn.jason.service.ParamLoggingService;
import jakarta.servlet.http.HttpServletRequest;
import java.util.stream.Collectors;
import java.util.stream.IntStream;



public class PathVariableLoggingService implements ParamLoggingService {
    @Override
    public String formatLog(HttpServletRequest request, Object[] args) {
        String variables = IntStream.range(0, args.length)
                .mapToObj(i -> "variable" + i + "=" + args[i])
                .collect(Collectors.joining(", "));
        return String.format("Request Method: %s - Request URL: %s - variable入参: %s",request.getMethod(), request.getRequestURI(), variables);
    }
}
