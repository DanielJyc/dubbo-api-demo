package top.datadriven.dubbo;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ProtocolConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.dubbo.config.ServiceConfig;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import top.datadriven.dubbo.service.DemoService;

import java.io.IOException;

/**
 * @description: 提供者启动类。参考：http://dubbo.apache.org/zh-cn/docs/user/configuration/api.html
 * @author: jiayancheng
 * @email: jiayancheng@foxmail.com
 * @datetime: 2020/4/25 3:59 下午
 * @version: 1.0.0
 */
public class DemoServiceTest {

    public static void main(String[] args) throws IOException {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
                new String[]{"classpath:spring.xml"}
        );
        context.start();
        // 服务实现
        DemoService demoService = (DemoService) context.getBean("demoService");

        // 当前应用配置
        ApplicationConfig application = new ApplicationConfig();
        application.setName("dubbo-api-demo-provider");

        // 服务提供者协议配置
        ProtocolConfig protocol = new ProtocolConfig();
        protocol.setName("dubbo");
        protocol.setPort(20881);

        // 不使用注册中心
        RegistryConfig registry = new RegistryConfig();
        registry.setRegister(Boolean.FALSE);

        // 注意：ServiceConfig为重对象，内部封装了与注册中心的连接，以及开启服务端口
        // 服务提供者暴露服务配置
        // 此实例很重，封装了与注册中心的连接，请自行缓存，否则可能造成内存和连接泄漏
        ServiceConfig<DemoService> service = new ServiceConfig<>();
        service.setApplication(application);
        // 多个协议可以用setProtocols()
        service.setProtocol(protocol);
        service.setInterface(DemoService.class);
        service.setRef(demoService);
        service.setRegistry(registry);

        // 暴露及注册服务
        service.export();

        //任意键退出
        System.in.read();
    }

}