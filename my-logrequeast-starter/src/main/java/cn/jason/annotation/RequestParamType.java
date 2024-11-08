package cn.jason.annotation;

public enum RequestParamType {
    PARAM,         // 对应 @RequestParam 参数
    VARIABLE,      // 对应 @PathVariable 参数
    BODY,          // 对应 @RequestBody 参数
}
