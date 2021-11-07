package com.radzik.michal.shop.product.controller;


import com.radzik.michal.shop.common.dto.ProductDto;

import com.radzik.michal.shop.product.mapper.ProductMapper;
import com.radzik.michal.shop.product.service.ProductService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
@Slf4j
public class ProductController {

    private final ProductService productService;

    private final ProductMapper productMapper;

    @PostMapping
    @PreAuthorize("isAuthenticated()")
    public ProductDto saveProduct(@RequestBody ProductDto product) {

        return productMapper.toDto(productService.save(productMapper.toDao(product)));
    }

    @GetMapping("/{id}")
    public ProductDto getProductById(@PathVariable Long id) {
        log.info("product id {}", id);
        return productMapper.toDto(productService.findProductById(id));
    }

    @GetMapping
    public Page<ProductDto> getProductPage(@RequestParam int page, @RequestParam int size) {

        return productService.getPage(PageRequest.of(page, size)).map(productMapper::toDto);
    }

    @PutMapping("/{id}")
    @PreAuthorize("isAuthenticated() && (hasRole('ADMIN') || @securityService.hasAccesToProduct(#id))")
    public ProductDto updateProduct(@RequestBody ProductDto product, @PathVariable Long id) {

        return productMapper.toDto(productService.update(productMapper.toDao(product), id));
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        productService.deleteById(id);
    }

    @GetMapping("/user/{userId}")
    public List<ProductDto> getProductByUserId(@PathVariable Long userId) {
        return productMapper.toDtos(productService.findProductByUserId(userId));
    }
}

