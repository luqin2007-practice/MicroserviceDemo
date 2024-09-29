package com.example.helloworldserviceconsumer.service;

import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

@Component
public class HelloServiceFallback implements FallbackFactory<HelloService> {

    @Override
    public HelloService create(Throwable cause) {

        /*
         * hello 服务降级服务对象
         */
        return name -> "Hello " + name + ", I'm fallback!";
    }
}
