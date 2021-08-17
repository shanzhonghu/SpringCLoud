package com.kuang.springcloud.controller;

import com.kuang.springcloud.pojo.Dept;
import com.kuang.springcloud.service.DeptService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

//提供Restful 服务
@RestController
public class DeptController {
    @Autowired
    private DeptService deptService;



    @Autowired
    private DiscoveryClient client;

    @GetMapping ("/dept/get/{id}")
    @HystrixCommand(fallbackMethod = "Hystrixget")   //熔断机制
    public Dept get(@PathVariable("id") Long id){
        Dept dept = deptService.queryById(id);
        if (dept == null){
            throw new RuntimeException("id ->"+id+",不存在该用户，或者信息无法找到");
        }
        return dept;
    }

    //备选方案
    public Dept Hystrixget(@PathVariable("id") Long id){
        Dept dept = new Dept()
                .setDeptno(id)
                .setDname("id ->" + id + ",不存在该用户，为 null ")
                .setDb_source("no this database in MySQL");
        return dept;
    }

    @GetMapping("/dept/list")
    public List<Dept> queryAll(){
        return deptService.queryAll();
    }


    @PostMapping ("/dept/add")
    public Boolean addDept(Dept dept){
        return deptService.addDept(dept);
    }

//    注册进来的微服务   获取一些消息
    @GetMapping("/dept/discovery")
    public Object discovery(){
        //获取微服务列表的清单
        List<String> services = client.getServices();
        System.out.println("discovery=>service :"+services);

        //得到一个具体的微服务信息  通过就提的微服务id
        List<ServiceInstance> instances = client.getInstances("SPRINGCLOUD-PROVIDER-DEPT");
        for (ServiceInstance instance : instances) {
            System.out.println(
                    instance.getHost()+"\t"+
                            instance.getPort()+"\t"+
                            instance.getUri()+"\t"+
                            instance.getServiceId()
            );
        }
        return this.client;
    }
}
