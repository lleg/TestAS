package ru.digitalspirit.asaka.fileuploader.configuration;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jndi.JndiObjectFactoryBean;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import ru.digitalspirit.asaka.fileuploader.service.UploadService;

import javax.naming.NamingException;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

@EnableJpaRepositories(basePackages = "ru.digitalspirit.nbu.fileuploader.repository",
                       entityManagerFactoryRef = "fileUploaderEntityManagerFactory", transactionManagerRef =
                           "fileUploaderTransactionManager")
@EnableTransactionManagement
@EntityScan(basePackages = "ru.digitalspirit.nbu.fileuploader.entity")
@Configuration
public class FileUploaderConfig {

    @Bean
    public UploadService uploadService() {
        return new UploadService();
    }


    @Profile("server")
    @Bean(name = "fileUploaderDataSource")
    public DataSource fileDataSource() throws NamingException {
        JndiObjectFactoryBean factoryBean = new JndiObjectFactoryBean();
        factoryBean.setProxyInterface(DataSource.class);
        factoryBean.setResourceRef(true);
        factoryBean.setJndiName("jdbc/nbu_common");
        factoryBean.setLookupOnStartup(false);
        factoryBean.afterPropertiesSet();
        return (DataSource) factoryBean.getObject();
    }

    @Profile("local")
    @Bean(name = "fileUploaderDataSource")
    public DataSource localDataSource() {
        return DataSourceBuilder
                .create()
                .url("jdbc:oracle:thin:@//89.208.198.198:1521/BPMDB")
                .username("NBU_COMMON")
                .password("NBU_COMMON")
                .driverClassName("oracle.jdbc.OracleDriver")
                .build();
    }

    @Bean(name = "fileUploaderEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean fileEntityManagerFactory(
        @Qualifier("fileUploaderDataSource") DataSource fileDataSource) {
        LocalContainerEntityManagerFactoryBean lcemfb = new LocalContainerEntityManagerFactoryBean();
        lcemfb.setDataSource(fileDataSource);
        lcemfb.setPackagesToScan("ru.digitalspirit.nbu.fileuploader.entity");
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setDatabase(Database.ORACLE);
        vendorAdapter.setShowSql(true);
        lcemfb.setJpaVendorAdapter(vendorAdapter);
        lcemfb.setJpaProperties(additionalProperties());
        return lcemfb;
    }

    @Bean(name = "fileUploaderTransactionManager")
    public PlatformTransactionManager fileTransactionManager(
        @Qualifier("fileUploaderEntityManagerFactory") EntityManagerFactory emf) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(emf);
        return transactionManager;
    }

    Properties additionalProperties() {
        Properties properties = new Properties();
        properties.setProperty("hibernate.globally_quoted_identifiers", "true");
        properties.setProperty("hibernate.hbm2ddl.auto", "validate");
        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.Oracle12cDialect");
        properties.setProperty("hibernate.show_sql", "false");
        properties.setProperty("spring.jpa.hibernate.use-new-id-generator-mappings", "false");
        return properties;
    }
}
