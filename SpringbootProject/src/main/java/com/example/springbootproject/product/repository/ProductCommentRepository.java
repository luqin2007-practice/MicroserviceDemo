package com.example.springbootproject.product.repository;

import com.example.springbootproject.product.entity.ProductComment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductCommentRepository extends JpaRepository<ProductComment, Long> {

    List<ProductComment> findByProductIdOrderByCreated(Long productId);
}
