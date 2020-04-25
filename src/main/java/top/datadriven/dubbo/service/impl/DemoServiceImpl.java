package top.datadriven.dubbo.service.impl;

import org.springframework.stereotype.Service;
import top.datadriven.dubbo.service.DemoService;

/**
 * @description: 服务提供者接口实现
 * @author: jiayancheng
 * @email: jiayancheng@foxmail.com
 * @datetime: 2020/4/25 3:58 下午
 * @version: 1.0.0
 */
@Service(value = "demoService")
public class DemoServiceImpl implements DemoService {

    @Override
    public String sayHello(String name) {
        return "Hello " + name;
    }

}