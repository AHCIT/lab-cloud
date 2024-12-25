package com.cit.platform.tcp.server.controller;

import com.cit.platform.tcp.server.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: Richard
 * @Description: HelloController
 * @CreateDate: 2024/11/24 15:48
 * @UpdateUser: zhouli
 * @UpdateDate: 2024/11/24 15:48
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
@RestController
@RequestMapping("/tcp")
public class HelloController {
    private final HelloService helloService;

    @Autowired
    public HelloController(HelloService helloService) {
        this.helloService = helloService;
    }

    @GetMapping("/hello/{name}")
    public String sayHello(@PathVariable String name) {
        return helloService.sayHello(name);
    }
}
