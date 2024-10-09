package com.example.shopping.service;

import com.example.shopping.api.UserService;
import io.micrometer.observation.annotation.Observed;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "SHOPPING-USER-SERVICE",
        url = "${gateway.base-url}/userservice",
        fallbackFactory = UserFallbackFactory.class)
@Observed
public interface UserRemoteClient extends UserService {
}
