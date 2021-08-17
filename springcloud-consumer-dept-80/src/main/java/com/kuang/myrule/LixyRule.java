package com.kuang.myrule;//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//


import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.AbstractLoadBalancerRule;
import com.netflix.loadbalancer.ILoadBalancer;
import com.netflix.loadbalancer.Server;
import lombok.val;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class LixyRule extends AbstractLoadBalancerRule {
    //每个服务访问5次   换下一个服务 （3个）

    private  int total; // 被掉用的次数
    private  int currentIndex = 0; //当前是谁在提供服务


//    @SuppressWarnings({"RCN_REDUNDANT_NULLCHECK_OF_NULL_VALUE"})
    public Server choose(ILoadBalancer lb, Object key) {
        if (lb == null) {
            return null;
        } else {
            Server server = null;

            while(server == null) {
                if (Thread.interrupted()) {
                    return null;
                }

                List<Server> upList = lb.getReachableServers();//获取活着的服务
                List<Server> allList = lb.getAllServers();  // 获取全部的服务
                int serverCount = allList.size();
                if (serverCount == 0) {
                    return null;
                }

//                int index = this.chooseRandomInt(serverCount); // 生成区间随机数
//                server = (Server)upList.get(index); // 从活着的服务中，随机获取一个
                // ----------------------------------------------------


                //total 默认=0  如果为5  我们指向下一个服务点
                //index = 0  默认 0 如果total = 5 index +1；
                if(total < 5){
                    server = upList.get(currentIndex);
                    total++;
                }else{
                    total = 0;
                    currentIndex++;
                    if (currentIndex > upList.size()){
                        currentIndex = 0;
                    }
                }





                // ----------------------------------------------------





                if (server == null) {
                    Thread.yield();
                } else {
                    if (server.isAlive()) {
                        return server;
                    }

                    server = null;
                    Thread.yield();
                }
            }

            return server;
        }
    }

    protected int chooseRandomInt(int serverCount) {
        return ThreadLocalRandom.current().nextInt(serverCount);
    }

    @Override
    public Server choose(Object key) {
        return this.choose(this.getLoadBalancer(), key);
    }

    @Override
    public void initWithNiwsConfig(IClientConfig clientConfig) {
    }
}
