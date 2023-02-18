package com.radzik.michal.shop.statistic.repository;

import com.radzik.michal.shop.statistic.model.StatisticBasket;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;


public interface StatisticBasketRepository extends ElasticsearchRepository<StatisticBasket,String> {
}
