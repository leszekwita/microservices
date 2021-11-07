package com.radzik.michal.shop.product.service;


import com.radzik.michal.shop.product.domain.dao.Product;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {

    @CachePut(cacheNames = "product", key = "#result.id")
    Product save (Product product);

    @Cacheable(cacheNames = "product", key = "#id")
    Product findProductById(Long id);

    @CacheEvict(cacheNames = "product", key="#id")
    void deleteById(Long id);

    Page<Product> getPage(Pageable pageable);

    @CachePut(cacheNames = "product", key = "#result.id")
    Product update(Product product, Long id);

    List<Product> findProductByUserId(Long id);

    Product getProductByUserIdAndProductId(Long userId, Long productId);
}
