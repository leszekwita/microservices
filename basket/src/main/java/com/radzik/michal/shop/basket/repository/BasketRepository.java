package com.radzik.michal.shop.basket.repository;

import com.radzik.michal.shop.basket.domain.Basket;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.Optional;

public interface BasketRepository extends ElasticsearchRepository<Basket,String> {
    Optional<Basket> findByEmail(String email);
}
