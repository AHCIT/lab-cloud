package com.cit.platform.tcp.server;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableDubbo
public class DubboTcpApplication {

    public static void main(String[] args) {
        SpringApplication.run(DubboTcpApplication.class, args);
    }

}
