package com.radzik.michal.shop.product.repository;


import com.radzik.michal.shop.product.domain.dao.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.history.RevisionRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product,Long>, RevisionRepository<Product, Long, Integer>  {
     List<Product> findProductByUserId(Long userId);
     Optional<Product> findByIdAndUserId(Long productId, Long userId);
}
