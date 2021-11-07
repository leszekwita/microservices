package com.radzik.michal.shop.product.config;

import com.hazelcast.config.*;
import com.radzik.michal.shop.product.domain.dao.Product;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
@EnableCaching
@Configuration
public class HazelcastConfig {

    @Bean
    public Config configHazelcast() {
        Config config = new Config()
                .setInstanceName("hazelcast-instance")
                .addMapConfig(new MapConfig()
                        .setName("product")
                        .setEvictionConfig(new EvictionConfig()
                                .setSize(3)
                                .setEvictionPolicy(EvictionPolicy.LRU)
                                .setMaxSizePolicy(MaxSizePolicy.FREE_HEAP_SIZE)
                        ).setTimeToLiveSeconds(120));
        config.getSerializationConfig().addDataSerializableFactory
                (1, (int id) -> (id == 1) ? new Product() : null);
        return config;
    }


}
