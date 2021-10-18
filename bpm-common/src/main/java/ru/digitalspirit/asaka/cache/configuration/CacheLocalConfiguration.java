package ru.digitalspirit.asaka.cache.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.digitalspirit.asaka.cache.service.CacheLocalServiceImpl;
import ru.digitalspirit.asaka.cache.service.CacheService;

@Configuration
public class CacheLocalConfiguration {

    @Bean
    public CacheService cacheService() {
        return new CacheLocalServiceImpl();
    }

}
