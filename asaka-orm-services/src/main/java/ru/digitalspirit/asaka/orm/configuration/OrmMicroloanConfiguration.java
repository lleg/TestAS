package ru.digitalspirit.asaka.orm.configuration;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.*;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jndi.JndiObjectFactoryBean;
import org.springframework.jndi.JndiTemplate;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import ru.digitalspirit.asaka.orm.orika.OrmOrikaConfiguration;


import javax.naming.NamingException;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

@EnableJpaRepositories(basePackages = "ru.digitalspirit.asaka.orm.repository.microloan",
        entityManagerFactoryRef="ormMicroloanModelEntityManagerFactory", transactionManagerRef = "ormMicroloanModelTransactionManager")
@EnableTransactionManagement
@EntityScan(basePackages = "ru.digitalspirit.asaka.bpm.entity")
@ComponentScan("ru.digitalspirit.asaka.orm.service.microloan")
@Import(OrmOrikaConfiguration.class)
@Configuration
public class OrmMicroloanConfiguration {

    @Profile("server")
    @Bean(name = "ormMicroloanModelDataSource")
    public DataSource modelDataSource() throws NamingException {
        JndiObjectFactoryBean factoryBean = new JndiObjectFactoryBean();
        factoryBean.setProxyInterface(DataSource.class);
        factoryBean.setResourceRef(true);
        factoryBean.setJndiName("java:comp/env/jdbc/asaka_microloan");
        factoryBean.setLookupOnStartup(false);
        factoryBean.afterPropertiesSet();
        return (DataSource)factoryBean.getObject();
    }



    @Profile("local")
    @Bean(name = "ormMicroloanModelDataSource")
    public DataSource localDataSource() {
        return DataSourceBuilder
                .create()
                .url("jdbc:oracle:thin:@//89.208.209.112:1521/BPMDB")
                .username("BPM_MICROLOAN")
                .password("BPM_MICROLOAN")
                .driverClassName("oracle.jdbc.OracleDriver")
                .build();
    }

    @Bean(name = "ormMicroloanModelEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean modelEntityManagerFactory(@Qualifier("ormMicroloanModelDataSource")DataSource modelDataSource) {
        LocalContainerEntityManagerFactoryBean lcemfb = new LocalContainerEntityManagerFactoryBean();
        lcemfb.setDataSource(modelDataSource);
        lcemfb.setPackagesToScan("ru.digitalspirit.asaka.bpm.entity");
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setDatabase(Database.ORACLE);
        vendorAdapter.setShowSql(true);
        lcemfb.setJpaVendorAdapter(vendorAdapter);
        lcemfb.setJpaProperties(additionalProperties());
        return lcemfb;
    }

    @Bean(name = "ormMicroloanModelTransactionManager")
    public PlatformTransactionManager modelTransactionManager(@Qualifier("ormMicroloanModelEntityManagerFactory")EntityManagerFactory emf){
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(emf);
        return transactionManager;
    }

    Properties additionalProperties() {
        Properties properties = new Properties();
        properties.setProperty("hibernate.globally_quoted_identifiers", "true");
        properties.setProperty("hibernate.hbm2ddl.auto", "none");
        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.Oracle12cDialect");
        properties.setProperty("hibernate.show_sql", "false");
        properties.setProperty("hibernate.event.merge.entity_copy_observer", "allow");
        properties.setProperty("spring.jpa.hibernate.use-new-id-generator-mappings", "false");
    //    properties.setProperty("spring.jpa.properties.hibernate.enable_lazy_load_no_trans","true");
        return properties;
    }

}
