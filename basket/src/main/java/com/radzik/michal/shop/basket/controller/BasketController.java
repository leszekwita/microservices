package com.radzik.michal.shop.basket.controller;

import com.radzik.michal.shop.basket.domain.Product;
import com.radzik.michal.shop.basket.service.BasketService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/baskets")
@RequiredArgsConstructor
public class BasketController {

    private final BasketService basketService;


    @PostMapping
    public void addProductToBasket(@RequestBody Product product){
        basketService.save(product);
    }

    @GetMapping
    public List<Product> getAllProducts(){
        return basketService.getAllProducts();
    }

    @PatchMapping
    public void updateProductFromBasket(@RequestBody Product product){
        basketService.update(product);
    }

    @DeleteMapping("/{id}")
    public void deleteProductFromBasket(@PathVariable Long id){
        basketService.deleteProductFromBasket(id);
    }

}
