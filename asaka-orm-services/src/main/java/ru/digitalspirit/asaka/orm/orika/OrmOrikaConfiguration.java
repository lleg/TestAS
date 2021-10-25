package ru.digitalspirit.asaka.orm.orika;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.converter.ConverterFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class OrmOrikaConfiguration {

    @Primary
    @Bean(name = "ormOrikaMapperFacade")
    public MapperFacade mapperFacade(@Qualifier("ormOrikaMapperFactory") MapperFactory mapperFactory) {
        ConverterFactory converterFactory = mapperFactory.getConverterFactory();
        converterFactory.registerConverter(new TimeOrikaConverter());
        converterFactory.registerConverter(new DateOrikaConverter());
        converterFactory.registerConverter(new ru.digitalspirit.asaka.orm.orika.TimestampOrikaConverter());
        return mapperFactory.getMapperFacade();
    }

    @Primary
    @Bean(name = "ormOrikaMapperFactory")
    public MapperFactory mapperFactory() {
        return new DefaultMapperFactory.Builder().build();
    }
}
