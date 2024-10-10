package com.example.shopping.service;

import com.example.shopping.model.UserDto;
import com.example.shopping.model.UserMq;
import com.example.shopping.repository.UserRedisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements com.example.shopping.api.UserService {

    /**
     * 消息传递通道
     */
    @Autowired
    StreamBridge streamBridge;

    /**
     * 用户服务降级
     */
    @Autowired
    private UserRemoteClient userRemoteClient;

    /**
     * 用户缓存数据库
     */
    @Autowired
    private UserRedisRepository userRedisRepository;

    @Override
    public List<UserDto> findAll(Pageable pageable) {
        return userRemoteClient.findAll(pageable);
    }

    @Override
    public UserDto detail(Long id) {
        UserDto user = userRedisRepository.findUser(id);
        if (user == null) {
            // 无缓存
            user = userRemoteClient.detail(id);
            userRedisRepository.saveUser(user);
        }
        return user;
    }

    @Override
    public UserDto update(Long id, UserDto userDto) {
        streamBridge.send("user", UserMq.updateUser(userDto));
        userRedisRepository.deleteUser(id);
        return userDto;
    }

    @Override
    public boolean delete(Long id) {
        streamBridge.send("user", UserMq.deleteUser(id));
        userRedisRepository.deleteUser(id);
        return true;
    }
}
