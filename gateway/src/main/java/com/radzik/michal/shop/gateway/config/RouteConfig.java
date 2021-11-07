package com.radzik.michal.shop.gateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RouteConfig {

    public RouteLocator routeLocator(RouteLocatorBuilder builder){
        return builder.routes()
                .route("product", r-> r.host("localhost:8080")
                        .and()
                        .path("api/product/**")
                        .uri("http//:localhost:8181"))
                        .build();
    }
}
