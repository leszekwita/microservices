package com.radzik.michal.shop.basket.client;

import com.radzik.michal.shop.common.dto.ProductDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="PRODUCT-SERVICE")
//@RequestMapping("/api/products")
public interface ProductClient {

    @GetMapping("/api/products/{id}")
    ProductDto findProductById(@PathVariable Long id);

}
