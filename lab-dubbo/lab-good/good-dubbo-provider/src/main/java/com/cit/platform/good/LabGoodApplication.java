package com.cit.platform.good;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableDubbo
public class LabGoodApplication {

    public static void main(String[] args) {
        SpringApplication.run(LabGoodApplication.class, args);
    }

}
