# my-logrequeast
#### 项目描述
************************
该项目作用于springbootweb项目，通过框架注解实现web每次请求的入参和请求结束的出参，可指定注解的``paramType``属性来选择你记录参数的类型。
用户只需完成引入依赖，简单配置，使用注解。
#### 应用环境
************************
```
        <spring-boot.version>3.1.3</spring-boot.version>
        <java.version>17</java.version>
```
#### 日志配置application.yml
************************
```
spring:
  log-boot:
    log-file-name: "application.log"  # 指定日志文件名
    log-path: "E:/IDEA/idea/mulu/my-logrequest"   # 指定日志文件路径
    log-pattern: "%d{yyyy-MM-dd HH:mm:ss} [%thread] %-5level %logger{15} - %msg%n"   # 指定日志输出格式
    log-level: "INFO"  # 指定日志级别
    // 配置属性均为字符串
```
### 示例
```
2024-11-08 18:12:11 [http-nio-8080-exec-4] INFO  c.j.s.LogRequestAspect - Request Method: GET - Request URL: /demo/test - param入参: param0=John
2024-11-08 18:12:11 [http-nio-8080-exec-4] INFO  c.j.s.LogRequestAspect - Request URL: /demo/test - 返回参数: "John"
2024-11-08 18:13:17 [http-nio-8080-exec-6] INFO  c.j.s.LogRequestAspect - Request Method: POST - Request URL: /demo/test2 - Body入参: {"name":"jason","age":21,"sex":"男"}
2024-11-08 18:13:17 [http-nio-8080-exec-6] INFO  c.j.s.LogRequestAspect - Request URL: /demo/test2 - 返回参数: {"name":"jason","age":21,"sex":"男"}
2024-11-08 18:13:59 [http-nio-8080-exec-7] INFO  c.j.s.LogRequestAspect - Request Method: GET - Request URL: /demo/test3/lihua - variable入参: variable0=lihua
2024-11-08 18:13:59 [http-nio-8080-exec-7] INFO  c.j.s.LogRequestAspect - Request URL: /demo/test3/lihua - 返回参数: "lihua"
```
#### 注解使用示例 ``@LogRequest(paramType = PARAM\BODY\VARIABLE)``
