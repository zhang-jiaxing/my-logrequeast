package cn.jason;

import org.springframework.boot.ConfigurableBootstrapContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringApplicationRunListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

import java.time.Duration;

public class SpringStarter implements SpringApplicationRunListener {
    private final SpringApplication application;
    private final String[] args;


    public SpringStarter(SpringApplication application, String[] args) {
        this.application = application;
        this.args = args;
    }
    // 实现启动监听器的starting方法,用于执行在 Spring 应用启动前的预处理逻辑
    @Override
    public void starting(ConfigurableBootstrapContext bootstrapContext) {
        System.out.println("SpringStarter starting");
    }
    // ApplicationContext 还没有创建，此方法用于对 Environment 进行进一步的调整或配置。
    @Override
    public void environmentPrepared(ConfigurableBootstrapContext bootstrapContext, ConfigurableEnvironment environment) {
        System.out.println("environmentPrepared 在这里可以获取和修改一些系统变量、配置文件属性等");
    }
    // ApplicationContext 已经创建，在这个阶段注册一些属性源或配置文件源.
    @Override
    public void contextPrepared(ConfigurableApplicationContext context) {
        System.out.println("contextPrepared 在这个阶段注册一些属性源或配置文件源,如 PropertySource 加载器、Web 配置等。");
    }
    //ApplicationContext 已经加载完成，所有的 Bean 已经被定义，但还未进行初始化。
    @Override
    public void contextLoaded(ConfigurableApplicationContext context) {
        System.out.println("contextLoaded 在这个阶段，所有 Bean 已经被定义，但还未进行初始化。");
    }
    //ApplicationContext 已经启动完成，所有的 Bean 都已经初始化完成，可以进行使用。
    @Override
    public void started(ConfigurableApplicationContext context, Duration timeTaken) {
        System.out.println("started 在这个阶段，所有的 Bean 都已经初始化完成，可以进行使用。");
    }
    //ready 方法用于在 Spring 应用程序启动完成后执行一些操作，例如打印启动完成的消息。
    @Override
    public void ready(ConfigurableApplicationContext context, Duration timeTaken) {
        System.out.println("ready 在这里可以做一些准备工作，比如：打印启动完成的消息");
    }
    //failed 方法用于在 Spring 应用程序启动失败时执行一些操作，例如打印错误信息。
    @Override
    public void failed(ConfigurableApplicationContext context, Throwable exception) {
        System.out.println("failed 在这里可以做一些启动异常的准备工作，比如：打印错误信息");
    }
}
