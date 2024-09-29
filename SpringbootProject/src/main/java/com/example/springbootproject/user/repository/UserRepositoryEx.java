package com.example.springbootproject.user.repository;

import com.example.springbootproject.user.entity.User;

import java.util.List;

public interface UserRepositoryEx {

    List<User> findTopUser(int count);
}
