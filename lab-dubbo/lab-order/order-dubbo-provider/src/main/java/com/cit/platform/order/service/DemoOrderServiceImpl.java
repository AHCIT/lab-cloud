package com.cit.platform.order.service;

import com.cit.platform.order.api.DemoOrderService;
import org.apache.dubbo.config.annotation.DubboService;

@DubboService
public class DemoOrderServiceImpl implements DemoOrderService {

    @Override
    public String sayHello(String name) {
        return "Hello " + name;
    }
}