package com.cit.platform.order;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableDubbo
public class DubboOrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(DubboOrderApplication.class, args);
    }

}