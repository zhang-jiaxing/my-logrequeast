package cn.jason.service;

import jakarta.servlet.http.HttpServletRequest;

public interface ParamLoggingService {
    String formatLog(HttpServletRequest request, Object[] args);
}
