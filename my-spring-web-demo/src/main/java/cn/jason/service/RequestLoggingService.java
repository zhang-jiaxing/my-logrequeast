package cn.jason.service;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;

import java.util.Arrays;
/**
 * @describe: 用于自定义日志逻辑
 * @Author JasonZhang
 * @Date 2024/11/8
**/
@Service
public class RequestLoggingService implements ParamLoggingService {
    @Override
    public String formatLog(HttpServletRequest request, Object[] args) {
        // 开发者自定义日志格式
        return String.format("Custom Params - Request URL: %s - Custom Body Params: %s",
                request.getRequestURI(), Arrays.toString(args));
    }
}
