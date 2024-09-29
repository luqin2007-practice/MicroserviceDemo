package com.example.springbootproject.user.repository;

import com.example.springbootproject.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long>, UserRepositoryEx {
}
