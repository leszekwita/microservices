package com.radzik.michal.shop.basket.controller;

import com.radzik.michal.shop.basket.domain.Product;
import com.radzik.michal.shop.basket.service.BasketService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@RequestMapping("/api/baskets")
@PreAuthorize("isAuthenticated")
@RequiredArgsConstructor
public class BasketController {

    private final BasketService basketService;


    @PostMapping("/api/baskets")
    @Operation(description = "addProductToBasket", security = @SecurityRequirement(name = "BearerToken"))
    public void addProductToBasket(@RequestBody Product product){
        basketService.save(product);
    }

    @GetMapping("/api/baskets")
    @Operation(description = "getAllProducts", security = @SecurityRequirement(name = "BearerToken"))
    public List<Product> getAllProducts(){
        return basketService.getAllProducts();
    }

    @PatchMapping("/api/baskets")
    @Operation(description = "updateProductFromBasket", security = @SecurityRequirement(name = "BearerToken"))
    public void updateProductFromBasket(@RequestBody Product product){
        basketService.update(product);
    }

    @DeleteMapping("/api/baskets/{id}")
    @Operation(description = "deleteProductFromBasket", security = @SecurityRequirement(name = "BearerToken"))
    public void deleteProductFromBasket(@PathVariable Long id){
        basketService.deleteProductFromBasket(id);
    }

}
