package com.cit.platform.good;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableDubbo
public class DubboGoodApplication {

    public static void main(String[] args) {
        SpringApplication.run(DubboGoodApplication.class, args);
    }

}
