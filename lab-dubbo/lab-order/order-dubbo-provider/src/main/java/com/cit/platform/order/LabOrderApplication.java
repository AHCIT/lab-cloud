package com.cit.platform.order;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableDubbo
public class LabOrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(LabOrderApplication.class, args);
    }

}
