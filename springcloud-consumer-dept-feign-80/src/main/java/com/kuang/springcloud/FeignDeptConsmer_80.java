package com.kuang.springcloud;

import com.kuang.myrule.LxyRule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;


//Riddon 和 Eureka 整合之后 ，客户端可以直接访问  就不需要再关心ip地址

@SpringBootApplication
@EnableEurekaClient //客户端
@EnableFeignClients(basePackages = {"com.kuang.springcloud"})
@ComponentScan("com.kuang.springcloud")
//@RibbonClient(name = "SPRINGCLOUD-PROVIDER-DEPT", configuration = LxyRule.class)
public class FeignDeptConsmer_80 {
    public static void main(String[] args) {
        SpringApplication.run(FeignDeptConsmer_80.class,args);
    }
}
