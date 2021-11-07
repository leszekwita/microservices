package com.radzik.michal.shop.product.service.impl;



import com.radzik.michal.shop.product.domain.dao.Product;
import com.radzik.michal.shop.product.repository.ProductRepository;
import com.radzik.michal.shop.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product findProductById(Long id) {
        log.info("Object not in cash {}", id);
        return productRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Product does not exist"));
    }

    @Override
    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public Page<Product> getPage(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    @Override
    public Product update(Product product, Long id) {
        Product productDb = findProductById(id);
        productDb.setName(product.getName());
        productDb.setAmount(product.getAmount());
        productDb.setPrice(product.getPrice());
        return productRepository.save(productDb);
    }

    @Override
    public List<Product> findProductByUserId(Long id) {
        return productRepository.findProductByUserId(id);
    }

    @Override
    public Product getProductByUserIdAndProductId(Long userId, Long productId) {
        return productRepository.findByIdAndUserId(productId, userId).orElseThrow(() -> new EntityNotFoundException("Product not found"));
    }


}
