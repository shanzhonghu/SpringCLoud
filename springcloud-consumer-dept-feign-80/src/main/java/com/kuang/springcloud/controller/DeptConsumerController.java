package com.kuang.springcloud.controller;

import com.kuang.springcloud.pojo.Dept;
import com.kuang.springcloud.service.DeptClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
public class DeptConsumerController {

    @Autowired
    private DeptClientService deptClientService;

    @RequestMapping("/consumer/dept/get/{id}")
    public Dept get(@PathVariable("id") Long id){
        return deptClientService.queryById(id);
    }


    @RequestMapping("/consumer/dept/list")
    public List<Dept> queryAll(){
        return deptClientService.queryAll();
    }


    @RequestMapping("/consumer/dept/add")
    public Boolean add(Dept dept){
        return deptClientService.add(dept);
    }
}
