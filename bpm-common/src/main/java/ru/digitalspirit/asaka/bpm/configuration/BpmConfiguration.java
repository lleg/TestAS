package ru.digitalspirit.asaka.bpm.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.client.RestTemplate;
import ru.digitalspirit.asaka.applicationlist.entity.BusinessProcessRole;
import ru.digitalspirit.asaka.bpm.service.*;
import ru.digitalspirit.asaka.refbook.configuration.DbConfiguration;
import ru.digitalspirit.asaka.bpm.service.*;
import ru.digitalspirit.asaka.util.CoreUtil;

import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;


@Configuration
@Import(DbConfiguration.class)
@ComponentScan({"ru.digitalspirit.asaka.bpm.service"})
public class BpmConfiguration {

    private static Logger logger = LoggerFactory.getLogger(BpmConfiguration.class);

    @Autowired
    JpaRepository<BusinessProcessRole, Long> businessProcessRoleRepository;

    @Bean
    public NbuBpmService nfoBpmService() {
        return new NbuBpmServiceImpl();
    }

    @Bean
    public UserInfoService nfoUserService(NbuBpmService nbuBpmService) {
        NbuUserInfoServiceImpl userService = new NbuUserInfoServiceImpl(businessProcessRoleRepository);
        userService.setBpmService(nbuBpmService);
        return userService;
    }

    @Bean
    public NbuBpmTaskListHelper nfoBpmTaskListHelper() {
        return new NbuBpmTaskListHelper(restTemplate());
    }

    @Bean
    public NbuBpmSendStatusHelper nfoBpmSendStatusHelper() {
        return new NbuBpmSendStatusHelper(restTemplate());
    }

    @Bean
    public NbuJSessionIdHelper nfoJSessionIdHelper() {
        return new NbuJSessionIdHelper();
    }

    private RestTemplate restTemplate() {
        RestTemplate restTemplate = null;
        try {
            restTemplate = CoreUtil.getSecuredRestTemplate();
        } catch (KeyStoreException | NoSuchAlgorithmException | KeyManagementException e) {
            logger.error("restTemplate()", e);
            restTemplate = new RestTemplate();
        }
        return restTemplate;
    }

}
