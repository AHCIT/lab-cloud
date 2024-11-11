package com.cit.platform.good.service;

import com.cit.platform.good.api.DemoGoodService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;

@Slf4j
@DubboService
public class DemoGoodServiceImpl implements DemoGoodService {

    @Override
    public String sayHello(String name) {
        log.info("good say hello to {}", name);
        return "Hello " + name;
    }
}