package com.radzik.michal.shop.basket.controller;

import com.radzik.michal.shop.basket.client.ProductClient;
import com.radzik.michal.shop.common.dto.ProductDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RequiredArgsConstructor
@RestController
@RequestMapping("/load-balancer")
public class LoadBalancerTestController {

    private final RestTemplate restTemplate;

    private final ProductClient productClient;

    @GetMapping("/feign-client/{idProduct}")
    public ProductDto testFeignClient(@PathVariable Long idProduct){
        return productClient.findProductById(idProduct);
    }
    @GetMapping("/rest-template/{idProduct}")
    public ProductDto testRestTemplate(@PathVariable Long idProduct){
        return restTemplate.getForObject("http://PRODUCT-SERVICE/api/products/"+idProduct, ProductDto.class);
    }

}
