package com.radzik.michal.shop.product.mapper;

import com.radzik.michal.shop.common.dto.ProductDto;
import com.radzik.michal.shop.product.domain.dao.Product;
import org.mapstruct.Mapper;


import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    ProductDto toDto (Product product);

    Product toDao (ProductDto productDto);

    List<ProductDto> toDtos(List<Product>products);


}
