package cn.jason.config;


import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.encoder.PatternLayoutEncoder;
import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.ConsoleAppender;
import ch.qos.logback.core.rolling.RollingFileAppender;
import ch.qos.logback.core.rolling.SizeAndTimeBasedRollingPolicy;
import ch.qos.logback.core.util.FileSize;
import cn.jason.LogBootProperties;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import java.nio.file.Paths;

@AutoConfiguration
@EnableConfigurationProperties(LogBootProperties.class)
public class LogConfiguration {
    private static final Logger log = (Logger) LoggerFactory.getLogger(LogConfiguration.class);

    private final LogBootProperties logProperties;

    @Autowired
    public LogConfiguration(LogBootProperties logProperties) {
        this.logProperties = logProperties;
        configureLogging();
    }

    private void configureLogging() {
        log.info("开始配置日志...");
        String logPath = logProperties.getLogPath();  // 获取日志路径
        String logFileName = logProperties.getLogFileName();  // 获取日志文件名
        String logPattern = logProperties.getLogPattern();  // 获取日志输出格式
        String logLevel = logProperties.getLogLevel();  // 获取日志级别

        // 配置 RollingFileAppender
        LoggerContext context = (LoggerContext) LoggerFactory.getILoggerFactory();

        RollingFileAppender<ILoggingEvent> fileAppender = new RollingFileAppender<>();
        fileAppender.setContext(context);
        // 设置日志文件路径
        String logFilePath = Paths.get(logPath, logFileName).toString();
        fileAppender.setFile(logFilePath);

        // 设置 SizeAndTimeBasedRollingPolicy
        SizeAndTimeBasedRollingPolicy<ILoggingEvent> policy = new SizeAndTimeBasedRollingPolicy<>();
        policy.setContext(context);
        policy.setFileNamePattern(logFilePath + ".%d{yyyy-MM-dd}.%i.log");
        policy.setMaxFileSize(FileSize.valueOf("10MB"));
        policy.setMaxHistory(30);
        policy.setTotalSizeCap(FileSize.valueOf("1GB"));
        policy.setParent(fileAppender);
        policy.start(); // 必须调用 start() 方法来启动滚动策略

        fileAppender.setRollingPolicy(policy); // 必须设置 RollingPolicy，否则会报错

        // 配置 PatternLayoutEncoder
        PatternLayoutEncoder encoder = new PatternLayoutEncoder();
        encoder.setContext(context);
        encoder.setPattern(logPattern);
        encoder.start(); // 启动 encoder

        fileAppender.setEncoder(encoder); // 必须使用 PatternLayoutEncoder
        fileAppender.start(); // 启动 RollingFileAppender

        // 配置 ConsoleAppender（可选）
        ConsoleAppender<ILoggingEvent> consoleAppender = new ConsoleAppender<>();
        consoleAppender.setContext(context);
        consoleAppender.setEncoder(encoder); // 使用相同的编码器
        consoleAppender.start();

        // 配置 ROOT Logger，将文件和控制台 appender 添加到 ROOT
        Logger rootLogger = context.getLogger("ROOT");
        rootLogger.setLevel(Level.valueOf(logLevel));
        rootLogger.addAppender(fileAppender);
        rootLogger.addAppender(consoleAppender);

        log.info("日志配置完成，日志文件路径: {}", logFilePath);
    }
}
