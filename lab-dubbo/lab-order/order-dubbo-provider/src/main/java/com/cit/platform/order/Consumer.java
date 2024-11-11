package com.cit.platform.order;

import com.cit.platform.order.api.DemoOrderService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Consumer implements CommandLineRunner {
    @DubboReference
    private DemoOrderService demoOrderService;

    @Override
    public void run(String... args) throws Exception {

        String result = demoOrderService.sayHello("world");
        System.out.println("Receive result ======> " + result);
    }
}
