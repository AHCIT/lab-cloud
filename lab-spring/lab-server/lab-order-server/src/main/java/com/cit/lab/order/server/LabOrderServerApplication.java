package com.cit.lab.order.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients(basePackages = {"com.cit.lab.openapi.*"})
public class LabOrderServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(LabOrderServerApplication.class, args);
    }

}
