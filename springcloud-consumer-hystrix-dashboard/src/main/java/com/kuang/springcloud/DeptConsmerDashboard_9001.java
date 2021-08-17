package com.kuang.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;


//Riddon 和 Eureka 整合之后 ，客户端可以直接访问  就不需要再关心ip地址

@SpringBootApplication
@EnableHystrixDashboard   //开启配置
public class DeptConsmerDashboard_9001 {
    public static void main(String[] args) {
        SpringApplication.run(DeptConsmerDashboard_9001.class,args);
    }
}
