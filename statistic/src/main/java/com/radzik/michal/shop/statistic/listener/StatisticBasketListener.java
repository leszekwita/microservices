package com.radzik.michal.shop.statistic.listener;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.radzik.michal.shop.statistic.model.StatisticBasket;
import com.radzik.michal.shop.statistic.service.StatisticBasketService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StatisticBasketListener {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper jsonConverter;
    private final StatisticBasketService baketService;


    @KafkaListener(topics = "basketA")
    public void getFromKafka(@Payload Message<?> basket) throws JsonProcessingException {
        StatisticBasket basketFromKafka = (StatisticBasket) jsonConverter.readValue(basket.getPayload().toString(), StatisticBasket.class);
        baketService.save(basketFromKafka);
    }
}
