package ru.digitalspirit.asaka.configuration;


import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import ru.digitalspirit.asaka.bpm.configuration.BpmConfiguration;
import ru.digitalspirit.asaka.orm.configuration.OrmMicroloanConfiguration;


@Configuration
@Import({OrmMicroloanConfiguration.class,
        // LogListConfiguration.class,
        SwaggerConfiguration.class,
        //  DbConfiguration.class,
      //  CacheConfiguration.class,
        //TaskListConfiguration.class,

       // BpmConfiguration.class
       // JacksonConfig.class

        })
public class AppConfiguration {

}
