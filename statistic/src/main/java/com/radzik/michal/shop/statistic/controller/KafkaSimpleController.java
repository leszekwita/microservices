package com.radzik.michal.shop.statistic.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.radzik.michal.shop.statistic.model.StatisticBasket;
import com.radzik.michal.shop.statistic.repository.StatisticBasketRepository;
import com.radzik.michal.shop.statistic.service.StatisticBasketService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/kafka")
public class KafkaSimpleController {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper jsonConverter;
    private final StatisticBasketService baketService;
    private final StatisticBasketRepository statisticBasketRepository;


    @PostMapping("/send")
    public void post(@RequestBody StatisticBasket basket) throws JsonProcessingException {

        kafkaTemplate.send("basketA", jsonConverter.writeValueAsString(basket));
    }

    @PostMapping("/elastic")
    public void saveElasticSearch(@RequestBody StatisticBasket basket) {

        statisticBasketRepository.save(basket);
    }



}
