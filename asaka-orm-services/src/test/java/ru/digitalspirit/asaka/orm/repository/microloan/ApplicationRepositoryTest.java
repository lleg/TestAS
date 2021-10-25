package ru.digitalspirit.asaka.orm.repository.microloan;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;
import ru.digitalspirit.asaka.bpm.entity.*;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigInteger;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Optional;
import java.util.UUID;


import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = ru.digitalspirit.asaka.orm.configuration.OrmMicroloanConfiguration.class)
@Transactional
@ActiveProfiles(profiles = "local")
class ApplicationRepositoryTest {

    @Autowired
    ApplicationRepository applicationRepository;


    @Test
    public void findOneByClaimNumBpm(){

        Timestamp date1 = new Timestamp(System.currentTimeMillis());
        Date date = new Date(System.currentTimeMillis());
        String uuid = UUID.randomUUID().toString();

        ClientEntity clientEntity = new ClientEntity();
        clientEntity.setClientID("1111111");
        clientEntity.setClientCode("7777");
        clientEntity.setClientUID("111111UID");
        clientEntity.setClientCodeCrm("7777CRM");
        clientEntity.setFullName("Petrov Petr Petrovich");
        clientEntity.setBirthDate(date);


        ApplicationEntity app = new ApplicationEntity();
        app.setClaimNumCrm("wet24564");
        app.setBranch("BRANCH1");
        app.setClaimNumBpm(uuid);
        app.setClient(clientEntity);

        ApplicationEntity appSaved = this.applicationRepository.save(app);

        Optional<ApplicationEntity> appById = this.applicationRepository.findById(appSaved.getApplicationID());

        assertNotNull(appById.isPresent() ? appById.get() : null);

        ApplicationEntity appSaved1 = appById.isPresent() ? appById.get() : new ApplicationEntity();
        assertEquals(app.getBranch(), appSaved1.getBranch());



    }

}