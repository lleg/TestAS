package ru.digitalspirit.asaka.bpm.orm.service;


import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Before;
import ru.digitalspirit.asaka.bpm.entity.*;
import ru.digitalspirit.asaka.bpm.orm.configuration.Model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class EntityServiceTest {
    private ApplicationEntity app = null;

    @Before
    public void setUp() {
        Timestamp date1 = new Timestamp(System.currentTimeMillis());
        Date date = new Date(System.currentTimeMillis());

        AdditionalIncomeEntity additionalIncomeEntity = new AdditionalIncomeEntity();
        additionalIncomeEntity.setIncomeType("type1");
        additionalIncomeEntity.setSum(BigDecimal.valueOf(124313.6));

        AddressEntity addressEntity1 = AddressEntity.builder().addressType("Dhtasf")
                .city("Piter")
                .country("Russia")
                .house("2")
                .postalCode("234534")
                .apartment("123").build();

        AddressEntity addressEntity2 = AddressEntity.builder().addressType("Dhtasf")
                .city("Moscow")
                .country("Russia")
                .house("5")
                .postalCode("111111")
                .apartment("4").build();


        CurrentLoanEntity currentLoanEntity1 = new CurrentLoanEntity();
        currentLoanEntity1.setSum(BigDecimal.valueOf(324.4));
        currentLoanEntity1.setCreditBank("Alfa");

        DepositInfoEntity depositInfoEntity = new DepositInfoEntity();
        depositInfoEntity.setSum(BigDecimal.valueOf(1324.6));
        depositInfoEntity.setBank("Alfa");

        JobInfoEntity jobInfoEntity = new JobInfoEntity();
        jobInfoEntity.setInn("124353452356");
        jobInfoEntity.setBankSalary("Alfa");
        jobInfoEntity.setEmployerName("SPIRIT");
        jobInfoEntity.setStartJobDate(date);
        jobInfoEntity.setTotalJobExperienceMonths(12);

        DocumentEntity documentEntity = new DocumentEntity();
        documentEntity.setDocumentName("Passport");
        documentEntity.setDivisionCode("234-234");
        documentEntity.setIdentificationFlag(true);
        documentEntity.setSeries("1234");
        documentEntity.setNumber("1234125");

        PhoneEntity phoneEntity =new PhoneEntity();
        phoneEntity.setNumber("12352345235");
        phoneEntity.setPrimaryPhone(true);

        //---- REL PERSON

        AdditionalIncomeEntity additionalIncomeEntityRel = new AdditionalIncomeEntity();
        additionalIncomeEntityRel.setIncomeType("type23");
        additionalIncomeEntityRel.setSum(BigDecimal.valueOf(124313.6));

        AddressEntity addressEntity1Rel = AddressEntity.builder().addressType("Прописка")
                .city("Voroneg")
                .country("Russia")
                .house("54")
                .postalCode("3456")
                .apartment("7").build();

        CurrentLoanEntity currentLoanEntity1Rel = new CurrentLoanEntity();
        currentLoanEntity1Rel.setSum(BigDecimal.valueOf(324.4));
        currentLoanEntity1Rel.setCreditBank("VTB");

        JobInfoEntity jobInfoEntityRel = new JobInfoEntity();
        jobInfoEntityRel.setInn("124353452356");
        jobInfoEntityRel.setBankSalary("AlfaBank");
        jobInfoEntityRel.setEmployerName("WORK");
        jobInfoEntityRel.setStartJobDate(date);
        jobInfoEntityRel.setTotalJobExperienceMonths(12);

        PhoneEntity phoneEntityRel =new PhoneEntity();
        phoneEntityRel.setNumber("12352345235");
        phoneEntityRel.setPrimaryPhone(true);

        RelatedPersonEntity relatedPersonEntity = new RelatedPersonEntity();
        List<AdditionalIncomeEntity> additionalIncomeEntityListRel = new ArrayList();
        additionalIncomeEntityListRel.add(additionalIncomeEntityRel);
        List<AddressEntity> addressEntityListRel = new ArrayList();
        addressEntityListRel.add(addressEntity1Rel);

        relatedPersonEntity.setAdditionalIncome(additionalIncomeEntityListRel);
        relatedPersonEntity.setAddresses(addressEntityListRel);
        List<CurrentLoanEntity> currentLoanEntityListRel = new ArrayList();
        currentLoanEntityListRel.add(currentLoanEntity1Rel);
        relatedPersonEntity.setCurrentLoans(currentLoanEntityListRel);
        relatedPersonEntity.setJobInfo(jobInfoEntityRel);
        relatedPersonEntity.setPhone(phoneEntityRel);
        relatedPersonEntity.setClientCode("2351223");
        relatedPersonEntity.setClientUID("q24523624564647");


        //------------------------------------


        ClientEntity clientEntity = new ClientEntity();
        clientEntity.setClientID("1111111");
        clientEntity.setClientCode("7777");
        clientEntity.setClientUID("111111UID");
        clientEntity.setClientCodeCrm("7777CRM");
        clientEntity.setFullName("Petrov Petr Petrovich");
        List<AdditionalIncomeEntity> additionalIncomeEntityList = new ArrayList();
        additionalIncomeEntityList.add(additionalIncomeEntity);
        clientEntity.setAdditionalIncome(additionalIncomeEntityList);
        List<AddressEntity> addressEntityList = new ArrayList();
        addressEntityList.add(addressEntity1);
        addressEntityList.add(addressEntity2);
        clientEntity.setAddresses(addressEntityList);
        clientEntity.setBirthDate(date);
        clientEntity.setAdultChildrenNum(2);
        List<CurrentLoanEntity> currentLoanEntityList = new ArrayList();
        currentLoanEntityList.add(currentLoanEntity1);
        clientEntity.setCurrentLoans(currentLoanEntityList);
        List<DepositInfoEntity> depositInfoEntityList = new ArrayList();
        depositInfoEntityList.add(depositInfoEntity);
        clientEntity.setDepositInfo(depositInfoEntityList);
        clientEntity.setInn("132412355143");
        clientEntity.setRegAddrEqualseResAddr(true);
        clientEntity.setIsCar(true);
        clientEntity.setJobInfo(jobInfoEntity);
        List<DocumentEntity> documentEntityList = new ArrayList();
        documentEntityList.add(documentEntity);
        clientEntity.setDocuments(documentEntityList);
        clientEntity.setPhone(phoneEntity);
        clientEntity.setIsRealEstate(true);
        List<RelatedPersonEntity> relatedPersonEntityList = new ArrayList();
        relatedPersonEntityList.add(relatedPersonEntity);
        clientEntity.setRelatedPersons(relatedPersonEntityList);

        AdditionalContactEntity additionalContactEntity = new AdditionalContactEntity();
        additionalContactEntity.setPhone("23452346256");
        additionalContactEntity.setFio("Ivanov Ivan Petrovich");
        additionalContactEntity.setRelationType("1");

        AttachedDocumentEntity attachedDocumentEntity = new AttachedDocumentEntity();
        attachedDocumentEntity.setDocumentName("Анкета");
        attachedDocumentEntity.setDocumentDate(date);
        attachedDocumentEntity.setGuid(UUID.randomUUID().toString());

        LoanEntity loanEntity = new LoanEntity();
        loanEntity.setCurrency("RUB");
        loanEntity.setSum(BigDecimal.valueOf(123442.6));
        loanEntity.setCreditType("asfwqfwe");

        app = new ApplicationEntity();
        app.setClient(clientEntity);
        app.setBranch("BRANCH");
        app.setClaimDate(date1);
        app.setClaimNumBpm("qqq111111");
        app.setBranch("1234215");
        List<AdditionalContactEntity> additionalContactEntityList = new ArrayList();
        additionalContactEntityList.add(additionalContactEntity);
        app.setAdditionalContacts(additionalContactEntityList);
        app.setStatus("23");
        List<AttachedDocumentEntity> attachedDocumentEntityList = new ArrayList();
        attachedDocumentEntityList.add(attachedDocumentEntity);
        app.setAttachedDocuments(attachedDocumentEntityList);
        app.setLoan(loanEntity);

    }

    @org.junit.Test
    public void save() {
        Transaction transaction = null;
        EntityService es = new EntityService();
        Session session = es.getSession(Model.ASAKA_MICROZIME);
        transaction = session.beginTransaction();
        Serializable id = es.save(app, session);
        transaction.commit();
        assertNotNull(id);

    }

    @org.junit.Test
    public void getById() {
        Transaction transaction = null;
        EntityService es = new EntityService();
        Session session = es.getSession(Model.ASAKA_MICROZIME);
        transaction = session.beginTransaction();
        Serializable id = es.save(app, session);
        transaction.commit();

        ApplicationEntity appOut = es.getById(ApplicationEntity.class,
                id, session);
        assertEquals(id, appOut.getApplicationID());
    }
}