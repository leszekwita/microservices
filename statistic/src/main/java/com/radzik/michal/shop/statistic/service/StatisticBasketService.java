package com.radzik.michal.shop.statistic.service;

import com.radzik.michal.shop.statistic.model.StatisticBasket;
import com.radzik.michal.shop.statistic.repository.StatisticBasketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
@RequiredArgsConstructor
@Service
public class StatisticBasketService {

    private final StatisticBasketRepository basketRepository;

    public StatisticBasket save(StatisticBasket basket){
       return basketRepository.save(basket);
    }
}
