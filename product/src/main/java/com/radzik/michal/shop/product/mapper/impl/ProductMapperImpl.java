package com.radzik.michal.shop.product.mapper.impl;

import com.radzik.michal.shop.common.dto.ProductDto;

import com.radzik.michal.shop.product.domain.dao.Product;
import com.radzik.michal.shop.product.mapper.ProductMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductMapperImpl implements ProductMapper {

    @Override
    public ProductDto toDto(Product product) {
        return ProductDto.builder()
                .id(product.getId())
                .name(product.getName())
                .amount(product.getAmount())
                .price(product.getPrice())
                .createdDate(product.getCreatedDate())
                .lastModifiedDate(product.getLastModifiedDate())
                .build();
    }

    @Override
    public Product toDao(ProductDto productDto) {
        return Product.builder()
                .id(productDto.getId())
                .name(productDto.getName())
                .amount(productDto.getAmount())
                .price(productDto.getPrice())
                .build();
    }

    @Override
    public List<ProductDto> toDtos(List<Product> products) {

        return products.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }


}
