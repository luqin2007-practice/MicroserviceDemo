package com.example.springbootproject.user.repository;

import com.example.springbootproject.user.entity.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

import java.util.List;

public class UserRepositoryImpl implements UserRepositoryEx {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<User> findTopUser(int count) {
        Query query = em.createQuery("SELECT u FROM User u");
        query.setMaxResults(count);
        return query.getResultList();
    }
}
