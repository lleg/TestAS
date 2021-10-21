package ru.digitalspirit.asaka.bpm.orm.service;


import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Before;
import ru.digitalspirit.asaka.bpm.entity.*;
import ru.digitalspirit.asaka.bpm.orm.configuration.Model;

import java.io.Serializable;
import java.math.BigDecimal;
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

        AdditionalIncomeEntity additionalIncomeEntity = AdditionalIncomeEntity.builder()
                .incomeType("type1")
                .sum(BigDecimal.valueOf(124313.6)).build();

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


        CurrentLoanEntity currentLoanEntity1 = CurrentLoanEntity.builder()
                .sum(BigDecimal.valueOf(324.4))
                .creditBank("Alfa")
                .monthlyPayment(BigDecimal.valueOf(20.4)).build();

        DepositInfoEntity depositInfoEntity = DepositInfoEntity.builder()
                .sum(BigDecimal.valueOf(1324.6))
                .bank("Alfa").build();

        JobInfoEntity jobInfoEntity = JobInfoEntity.builder().inn("124353452356")
                .bankSalary("Alfa")
                .employerName("SPIRIT")
                .startJobDate(date)
                .totalJobExperienceMonths(12).build();

        DocumentEntity documentEntity = DocumentEntity.builder()
                .documentName("Passport")
                .divisionCode("234-234")
                .identificationFlag(true)
                .series("1234")
                .number("1234125").build();

        PhoneEntity phoneEntity = PhoneEntity.builder()
                .number("12352345235")
                .primaryPhone(true).build();

        List<PhoneEntity> phoneList = new ArrayList();
        phoneList.add(phoneEntity);

        //---- REL PERSON

        AdditionalIncomeEntity additionalIncomeEntityRel = AdditionalIncomeEntity.builder()
                .incomeType("type23")
                .sum(BigDecimal.valueOf(124313.6)).build();

        AddressEntity addressEntity1Rel = AddressEntity.builder().addressType("Прописка")
                .city("Voroneg")
                .country("Russia")
                .house("54")
                .postalCode("3456")
                .apartment("7").build();

        CurrentLoanEntity currentLoanEntity1Rel = CurrentLoanEntity.builder()
                .sum(BigDecimal.valueOf(324.4))
                .creditBank("VTB").build();

        JobInfoEntity jobInfoEntityRel = JobInfoEntity.builder()
                .inn("124353452356")
                .bankSalary("AlfaBank")
                .employerName("WORK")
                .startJobDate(date)
                .totalJobExperienceMonths(12).build();

        PhoneEntity phoneEntityRel = PhoneEntity.builder()
                .number("12352345235")
                .primaryPhone(true).build();

        List<PhoneEntity> phoneEntityList = new ArrayList();
        phoneEntityList.add(phoneEntityRel);
        List<AdditionalIncomeEntity> additionalIncomeEntityListRel = new ArrayList();
        additionalIncomeEntityListRel.add(additionalIncomeEntityRel);
        List<AddressEntity> addressEntityListRel = new ArrayList();
        addressEntityListRel.add(addressEntity1Rel);
        List<CurrentLoanEntity> currentLoanEntityListRel = new ArrayList();
        currentLoanEntityListRel.add(currentLoanEntity1Rel);

        RelatedPersonEntity relatedPersonEntity = RelatedPersonEntity.builder()
                .additionalIncome(additionalIncomeEntityListRel)
                .addresses(addressEntityListRel)
                .currentLoans(currentLoanEntityListRel)
                .jobInfo(jobInfoEntityRel)
                .phone(phoneEntityList)
                .clientCode("2351223")
                .clientUID("q24523624564647").build();


        //------------------------------------
        List<AdditionalIncomeEntity> additionalIncomeEntityList = new ArrayList();
        additionalIncomeEntityList.add(additionalIncomeEntity);

        List<AddressEntity> addressEntityList = new ArrayList();
        addressEntityList.add(addressEntity1);
        addressEntityList.add(addressEntity2);

        List<CurrentLoanEntity> currentLoanEntityList = new ArrayList();
        currentLoanEntityList.add(currentLoanEntity1);

        List<DepositInfoEntity> depositInfoEntityList = new ArrayList();
        depositInfoEntityList.add(depositInfoEntity);

        List<DocumentEntity> documentEntityList = new ArrayList();
        documentEntityList.add(documentEntity);

        List<RelatedPersonEntity> relatedPersonEntityList = new ArrayList();
        relatedPersonEntityList.add(relatedPersonEntity);

        ClientEntity clientEntity = ClientEntity.builder()
                .clientID("1111111")
                .clientCode("7777")
                .clientUID("111111UID")
                .clientCodeCrm("7777CRM")
                .fullName("Petrov Petr Petrovich")
                .additionalIncome(additionalIncomeEntityList)
                .addresses(addressEntityList)
                .birthDate(date)
                .adultChildrenNum(2)
                .currentLoans(currentLoanEntityList)
                .depositInfo(depositInfoEntityList)
                .inn("132412355143")
                .regAddrEqualseResAddr(true)
                .isCar(true)
                .jobInfo(jobInfoEntity)
                .documents(documentEntityList)
                .phone(phoneList)
                .isRealEstate(true)
                .relatedPersons(relatedPersonEntityList).build();

        AdditionalContactEntity additionalContactEntity = AdditionalContactEntity.builder()
                .phone("23452346256")
                .fio("Ivanov Ivan Petrovich")
                .relationType("1").build();

        AttachedDocumentEntity attachedDocumentEntity = AttachedDocumentEntity.builder()
                .documentName("Анкета")
                .documentDate(date)
                .guid(UUID.randomUUID().toString()).build();

        LoanEntity loanEntity = LoanEntity.builder()
                .currency("RUB")
                .sum(BigDecimal.valueOf(123442.6))
                .issuanceForm("asfwqfwe").build();

        List<AdditionalContactEntity> additionalContactEntityList = new ArrayList();
        additionalContactEntityList.add(additionalContactEntity);

        List<AttachedDocumentEntity> attachedDocumentEntityList = new ArrayList();
        attachedDocumentEntityList.add(attachedDocumentEntity);

        app = ApplicationEntity.builder()
                .client(clientEntity)
                .branch("BRANCH")
                .claimDate(date1)
                .claimNumBpm("qqq111111")
                .branch("1234215")
                .additionalContacts(additionalContactEntityList)
                .status("23")
                .attachedDocuments(attachedDocumentEntityList)
                .loan(loanEntity).build();

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