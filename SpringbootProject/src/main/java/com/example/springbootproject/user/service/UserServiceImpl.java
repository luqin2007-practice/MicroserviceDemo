package com.example.springbootproject.user.service;

import com.example.springbootproject.user.dto.UserDto;
import com.example.springbootproject.user.entity.User;
import com.example.springbootproject.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public Page<User> getPage(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    public User load(Long id) {
        return userRepository.findById(id).orElseThrow();
    }

    @Override
    @Transactional
    public User save(UserDto userDto) {
        User user = userRepository
                .findById(userDto.id())
                .orElseGet(User::new);
        user.setAvatar(userDto.avatar());
        user.setNickname(userDto.nickname());
        return userRepository.save(user);
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }
}
