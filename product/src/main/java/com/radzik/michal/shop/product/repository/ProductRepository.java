package com.radzik.michal.shop.product.repository;


import com.radzik.michal.shop.product.domain.dao.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product,Long> {
     List<Product> findProductByUserId(Long userId);
     Optional<Product> findByIdAndUserId(Long productId, Long userId);
}
