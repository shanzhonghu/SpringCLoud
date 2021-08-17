package com.kuang.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
@EnableZuulProxy //开启
public class SpringCloud_Zuul9527 {
    public static void main(String[] args) {
        SpringApplication.run(SpringCloud_Zuul9527.class,args);
    }
}
