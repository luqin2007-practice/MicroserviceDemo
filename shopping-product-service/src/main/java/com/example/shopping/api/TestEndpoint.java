package com.example.shopping.api;

import com.example.shopping.model.UserDto;
import com.example.shopping.model.UserMq;
import com.example.shopping.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/product-test")
public class TestEndpoint {

    @Value("${gateway.base-url}")
    private String baseUrl;

    @Autowired
    private StreamBridge bridge;

    @Autowired
    private UserService userService;

    @GetMapping("/update/{id}/{name}")
    public String updateUser(@PathVariable Long id, @PathVariable String name) {
        UserDto user = userService.detail(id);
        bridge.send("user-topic", UserMq.updateUser(new UserDto(user.id(), name, user.avatar(), user.port())));
        return "redirect:" + baseUrl + "/userservice/users/" + id;
    }
}
