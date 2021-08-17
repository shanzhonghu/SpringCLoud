package com.kuang.springcloud;

import com.kuang.myrule.LxyRule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;


//Riddon 和 Eureka 整合之后 ，客户端可以直接访问  就不需要再关心ip地址
@SpringBootApplication
@EnableEurekaClient //客户端
//在微服务启动的时候就能去加载我们定义的Ribbon类
@RibbonClient(name = "SPRINGCLOUD-PROVIDER-DEPT", configuration = LxyRule.class)
public class DeptConsmer_80 {
    public static void main(String[] args) {
        SpringApplication.run(DeptConsmer_80.class,args);
    }
}
