package ru.digitalspirit.asaka.cache.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import ru.digitalspirit.asaka.cache.service.CacheLocalServiceImpl;
import ru.digitalspirit.asaka.cache.service.CacheService;
import ru.digitalspirit.asaka.cache.service.CacheServiceImpl;
import ru.digitalspirit.asaka.settings.Environment;

@Configuration
public class CacheConfiguration {

    @Profile("server")
    @Bean
    public CacheService cacheService() {
        return new CacheServiceImpl(
                Environment.getCacheJndi(),
                Environment.getCachePriority(),
                Environment.getCacheTimeToLive(),
                Environment.getCacheInactivityTime());
    }

    @Profile("local")
    @Bean
    public CacheService localCacheService() {
        return new CacheLocalServiceImpl();
    }

}
