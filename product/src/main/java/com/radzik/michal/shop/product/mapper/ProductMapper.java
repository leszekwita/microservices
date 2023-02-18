package com.radzik.michal.shop.product.mapper;

import com.radzik.michal.shop.common.dto.ProductDto;
import com.radzik.michal.shop.product.domain.dao.Product;
import com.radzik.michal.shop.product.mapper.impl.AuditableMapper;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface ProductMapper extends AuditableMapper<Product, ProductDto> {

    ProductDto toDto (Product product);

    Product toDao (ProductDto productDto);

    List<ProductDto> toDtos(List<Product>products);


}
