package com.kuang.springcloud.controller;

import com.kuang.springcloud.pojo.Dept;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
public class DeptConsumerController {

//    消费者，不应该有sever层
//    RestTemplate      提供我们直接调用就可以了   注册到  spring 中
    // 参数  （url,实体  map , Class<T> responseType）
    @Autowired
    private RestTemplate restTemplate; //提供多种快捷访问远程http服务的方法，简单的RestFull服务模板

    //Ribbon  我们这离的地址  应该是一个变量 通过服务名访问
//    private static final String REST_URL_PREFIX="http://localhost:8001";
    private static final String REST_URL_PREFIX="http://SPRINGCLOUD-PROVIDER-DEPT";


    //要从这个地址获取数据   http://localhost:8001/dept/get/1
    @RequestMapping("/consumer/dept/get/{id}")
    public Dept get(@PathVariable("id") Long id){
        return restTemplate.getForObject(REST_URL_PREFIX+"/dept/get/"+id,Dept.class);
    }


    @RequestMapping("/consumer/dept/list")
    public List<Dept> queryAll(){
        return restTemplate.getForObject(REST_URL_PREFIX+"/dept/list",List.class);
    }


    @RequestMapping("/consumer/dept/add")
    public Boolean add(Dept dept){
        return restTemplate.postForObject(REST_URL_PREFIX + "/dept/add", dept ,Boolean.class);
    }
}
