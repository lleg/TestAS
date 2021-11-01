package ru.digitalspirit.asaka.config.configuration;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.*;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jndi.JndiObjectFactoryBean;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import ru.digitalspirit.asaka.bpm.configuration.BpmConfiguration;
//import ru.digitalspirit.asaka.role.configuration.RoleConfiguration;
import ru.digitalspirit.asaka.tasklist.service.TaskListService;
import ru.digitalspirit.asaka.tasklist.service.TaskListServiceImpl;
import ru.digitalspirit.asaka.config.service.ConfigParametersService;
import ru.digitalspirit.asaka.config.service.ConfigParametersServiceImpl;

import javax.naming.NamingException;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

@EnableJpaRepositories(basePackages = {"ru.digitalspirit.asaka.config.repository","ru.digitalspirit.asaka.applicationlist.repository","ru.digitalspirit.nbu.tasklist.repository"},
        entityManagerFactoryRef="configEntityManagerFactory", transactionManagerRef = "configTransactionManager")
@EnableTransactionManagement
@EntityScan(basePackages = {"ru.digitalspirit.asaka.config.entity","ru.digitalspirit.asaka.applicationlist.entity","ru.digitalspirit.asaka.tasklist.entity"})
@ComponentScan(basePackages = "ru.digitalspirit.asaka.applicationlist.service")
@Configuration
@Import({BpmConfiguration.class /*, RoleConfiguration.class*/})
public class ConfigParametersConfiguration {
    @Bean
    public ConfigParametersService configParametersService() {
        return new ConfigParametersServiceImpl();
    }

    @Profile("server")
    @Bean(name = "configDataSource")
    public DataSource configDataSource() throws NamingException {
        JndiObjectFactoryBean factoryBean = new JndiObjectFactoryBean();
        //factoryBean.setExpectedType(DataSource.class);
        factoryBean.setProxyInterface(DataSource.class);
        factoryBean.setResourceRef(true);
        //factoryBean.setJndiName("jdbc/bpm_common");
        factoryBean.setJndiName("java:comp/env/jdbc/bpm_common");
        factoryBean.setLookupOnStartup(false);
        factoryBean.afterPropertiesSet();
        return (DataSource)factoryBean.getObject();
    }


    @Profile("local")
    @Bean(name = "configDataSource")
    public DataSource localDataSource() {
        return DataSourceBuilder
                .create()
                .url("jdbc:oracle:thin:@//89.208.209.112:1521/BPMDB")
                .username("BPM_COMMON")
                .password("BPM_COMMON")
                .driverClassName("oracle.jdbc.OracleDriver")
                .build();
    }

    @Bean(name = "configEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean configEntityManagerFactory(@Qualifier("configDataSource")DataSource configDataSource) {
        LocalContainerEntityManagerFactoryBean lcemfb = new LocalContainerEntityManagerFactoryBean();
        lcemfb.setDataSource(configDataSource);
        lcemfb.setPackagesToScan("ru.digitalspirit.asaka.config.entity","ru.digitalspirit.asaka.applicationlist.entity","ru.digitalspirit.asaka.tasklist.entity");
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setDatabase(Database.ORACLE);
        vendorAdapter.setShowSql(true);
        lcemfb.setJpaVendorAdapter(vendorAdapter);
        lcemfb.setJpaProperties(additionalProperties());
        return lcemfb;
    }

    @Bean(name = "configTransactionManager")
    public PlatformTransactionManager configTransactionManager(@Qualifier("configEntityManagerFactory") EntityManagerFactory emf){
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
        properties.setProperty("spring.jpa.hibernate.use-new-id-generator-mappings", "false");
        properties.setProperty("spring.jpa.properties.hibernate.enable_lazy_load_no_trans","true");
        //properties.setProperty("hibernate.default_schema","nbu");
        return properties;
    }

}
