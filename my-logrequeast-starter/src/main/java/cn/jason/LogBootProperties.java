package cn.jason;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;


@Data
@ConfigurationProperties("spring.log-boot")
public class LogBootProperties {
    private  String logPath = "E:/IDEA/idea/mulu/my-logrequest";         // 日志文件存放路径
    private  String logFileName = "application.log";  // 日志文件名
    private  String logPattern = "%d{yyyy-MM-dd HH:mm:ss} [%thread] %-5level %logger{15} - %msg%n"; // 日志格式
    private  String logLevel = "INFO";        // 日志级别
}
