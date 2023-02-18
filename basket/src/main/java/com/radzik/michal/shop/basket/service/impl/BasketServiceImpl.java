package com.radzik.michal.shop.basket.service.impl;

import com.radzik.michal.shop.basket.client.ProductClient;
import com.radzik.michal.shop.basket.client.UserClient;
import com.radzik.michal.shop.basket.domain.Basket;
import com.radzik.michal.shop.basket.domain.Product;
import com.radzik.michal.shop.basket.exception.EntityNotFoundException;
import com.radzik.michal.shop.basket.exception.QuantityNotEnoughException;
import com.radzik.michal.shop.basket.repository.BasketRepository;
import com.radzik.michal.shop.basket.security.SecurityUtils;
import com.radzik.michal.shop.basket.service.BasketService;
import com.radzik.michal.shop.common.dto.ProductDto;
import com.radzik.michal.shop.common.dto.UserDto;
import com.radzik.michal.shop.common.kafka.BasketDto;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.retry.annotation.Recover;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.net.UnknownHostException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@Slf4j
public class BasketServiceImpl implements BasketService {

    private final ProductClient productClient;

    private final UserClient userClient;

    private final BasketRepository basketRepository;

    private final KafkaTemplate<String, BasketDto> kafkaTemplate;

   // @Retryable(value = {ConnectException.class, UnknownHostException.class}, maxAttempts = 5, backoff = @Backoff(delay = 500))
    @Override
    @Transactional
    public void save(Product product) {
        log.info("add Product to basket");
        UserDto currentUser = userClient.getLoggedCurrentUser();
        ProductDto productDb = productClient.findProductById(product.getId());
        if (productDb.getAmount() < product.getAmount()) {
            throw new QuantityNotEnoughException("You try add more product than is in warehouse");
        }

        basketRepository.save(Basket.builder()
                .email(currentUser.getEmail())
                .product(product.toBuilder()
                        .name(productDb.getName())
                        .price(productDb.getPrice().intValue())
                        .build())
                .build());
        kafkaTemplate.send("basketA", BasketDto.newBuilder()
                        .setAmount(product.getAmount())
                        .setEmail(currentUser.getEmail())
                        .setProductName(productDb.getName())
                        .setDate(LocalDateTime.now().toString())
                .build()
        );
    }

    @Override
    public void deleteProductFromBasket(Long productId) {
        String email = SecurityUtils.getCurrentUserEmail();
        Optional<Basket> optionalBasket = basketRepository.findByEmail(email);
        if (optionalBasket.isPresent()) {
            Basket basket = optionalBasket.get();
            basket.getProducts().removeIf(product -> product.getId().equals(productId));
            basketRepository.save(basket);
        } else {
            throw new EntityNotFoundException("Basket does not exist");
        }

    }

    @Override
    @CircuitBreaker(fallbackMethod = "updateFallbackMethod", name= "productService")
    @Transactional
    public void update(Product product) {
        String email = SecurityUtils.getCurrentUserEmail();
        Optional<Basket> optionalBasket = basketRepository.findByEmail(email);
        if (optionalBasket.isPresent()) {
            Basket basket = optionalBasket.get();
            Optional<Product> optionalProductInBasket = basket.getProducts().stream()
                    .filter(productInBasket -> productInBasket.getId().equals(product.getId()))
                    .findFirst();
            if (optionalProductInBasket.isPresent()) {
                Product productDb = optionalProductInBasket.get();
                ProductDto productDto = productClient.findProductById(product.getId());
                if (product.getAmount() > productDto.getAmount()) {
                    throw new QuantityNotEnoughException("You try add more product than is in warehouse");
                }

                productDb.setAmount(product.getAmount());
                productDb.setPrice(productDto.getPrice().intValue());
                productDb.setName(productDto.getName());
                basketRepository.save(basket);

            } else {
                ProductDto productDb = productClient.findProductById(product.getId());
                if (productDb.getAmount() <= product.getAmount()) {
                    throw new QuantityNotEnoughException("You try update more product than is in warehouse");
                }
                product.setName(productDb.getName());
                product.setPrice(productDb.getPrice().intValue());
                basket.getProducts().add(product);
                basketRepository.save(basket);
            }

        } else {
            save(product);
        }

    }

    @Override
    @RateLimiter(name="productService")
    @Operation(description = "get information about AllProducts", security = @SecurityRequirement(name = "BearerToken"))
    public List<Product> getAllProducts() {
        return basketRepository.findByEmail(SecurityUtils.getCurrentUserEmail())
                .map(Basket::getProducts)
                .orElseThrow(() -> new EntityNotFoundException("Basket does not exist"));
    }

    @Recover
    public void recover(Product product, UnknownHostException unknownHostException){
        log.info("Could not connect with external services", unknownHostException);
    }

    private void updateFallbackMethod(Product product, Exception e){
        log.info("updateFallbackMethod", e);
    }
}
