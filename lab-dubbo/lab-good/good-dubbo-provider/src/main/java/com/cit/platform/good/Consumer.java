package com.cit.platform.good;

import com.cit.platform.good.api.DemoGoodService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Consumer implements CommandLineRunner {
    @DubboReference
    private DemoGoodService demoGoodService;

    @Override
    public void run(String... args) throws Exception {

        String result = demoGoodService.sayHello("world");
        System.out.println("Receive result ======> " + result);
    }
}
