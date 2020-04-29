package top.datadriven.dubbo;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ReferenceConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import top.datadriven.dubbo.service.DemoService;

/**
 * @description: 费者启动类 参考：http://dubbo.apache.org/zh-cn/docs/user/configuration/api.html
 * @author: jiayancheng
 * @email: jiayancheng@foxmail.com
 * @datetime: 2020/4/25 3:59 下午
 * @version: 1.0.0
 */
public class DemoServiceConsumerTest {
    public static void main(String[] args) throws InterruptedException {
        // 当前应用配置
        ApplicationConfig application = new ApplicationConfig();
        application.setName("dubbo-api-demo-consumer");

        // 注意：ReferenceConfig为重对象，内部封装了与注册中心的连接，以及与服务提供方的连接
        // 引用远程服务
        // 此实例很重，封装了与注册中心的连接以及与提供者的连接，请自行缓存，否则可能造成内存和连接泄漏
        ReferenceConfig<DemoService> reference = new ReferenceConfig<DemoService>();
        reference.setApplication(application);
        // 多个注册中心可以用setRegistries()
        reference.setInterface(DemoService.class);
        reference.setUrl("dubbo://localhost:20881");


        int i = 1;
        while (true) {
            // 和本地bean一样使用xxxService
            // 注意：此代理对象内部封装了所有通讯细节，对象较重，请缓存复用
            DemoService demoService = reference.get();
            String hello = demoService.sayHello("jyc" + i);
            i++;
            System.out.println(hello);
            Thread.sleep(2000);
        }
    }
}