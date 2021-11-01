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

        AdditionalIncomeTypeEntity additionalIncomeEntity = AdditionalIncomeTypeEntity.builder()
                .incomeType("type1")
                .sum(BigDecimal.valueOf(124313.6)).build();

        AddressTypeEntity addressEntity1 = AddressTypeEntity.builder().addressType("Dhtasf")
                .city("Piter")
                .country("Russia")
                .house("2")
                .postalCode("234534")
                .apartment("123").build();

        AddressTypeEntity addressEntity2 = AddressTypeEntity.builder().addressType("Dhtasf")
                .city("Moscow")
                .country("Russia")
                .house("5")
                .postalCode("111111")
                .apartment("4").build();


        CurrentLoanTypeEntity currentLoanEntity1 = CurrentLoanTypeEntity.builder()
                .sum(BigDecimal.valueOf(324.4))
                .creditBank("Alfa")
                .monthlyPayment(BigDecimal.valueOf(20.4)).build();

        DepositInfoTypeEntity depositInfoEntity = DepositInfoTypeEntity.builder()
                .sum(BigDecimal.valueOf(1324.6))
                .bank("Alfa").build();

        JobInfoTypeEntity jobInfoEntity = JobInfoTypeEntity.builder().inn("124353452356")
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

        PhoneTypeEntity phoneEntity = PhoneTypeEntity.builder()
                .number("12352345235")
                .primaryPhone(true).build();

        List<PhoneTypeEntity> phoneList = new ArrayList();
        phoneList.add(phoneEntity);

        //---- REL PERSON

        AdditionalIncomeTypeEntity additionalIncomeEntityRel = AdditionalIncomeTypeEntity.builder()
                .incomeType("type23")
                .sum(BigDecimal.valueOf(124313.6)).build();

        AddressTypeEntity addressEntity1Rel = AddressTypeEntity.builder().addressType("Прописка")
                .city("Voroneg")
                .country("Russia")
                .house("54")
                .postalCode("3456")
                .apartment("7").build();

        CurrentLoanTypeEntity currentLoanEntity1Rel = CurrentLoanTypeEntity.builder()
                .sum(BigDecimal.valueOf(324.4))
                .creditBank("VTB").build();

        JobInfoTypeEntity jobInfoEntityRel = JobInfoTypeEntity.builder()
                .inn("124353452356")
                .bankSalary("AlfaBank")
                .employerName("WORK")
                .startJobDate(date)
                .totalJobExperienceMonths(12).build();

        PhoneTypeEntity phoneEntityRel = PhoneTypeEntity.builder()
                .number("12352345235")
                .primaryPhone(true).build();

        List<PhoneTypeEntity> phoneEntityList = new ArrayList();
        phoneEntityList.add(phoneEntityRel);
        List<AdditionalIncomeTypeEntity> additionalIncomeEntityListRel = new ArrayList();
        additionalIncomeEntityListRel.add(additionalIncomeEntityRel);
        List<AddressTypeEntity> addressEntityListRel = new ArrayList();
        addressEntityListRel.add(addressEntity1Rel);
        List<CurrentLoanTypeEntity> currentLoanEntityListRel = new ArrayList();
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
        List<AdditionalIncomeTypeEntity> additionalIncomeEntityList = new ArrayList();
        additionalIncomeEntityList.add(additionalIncomeEntity);

        List<AddressTypeEntity> addressEntityList = new ArrayList();
        addressEntityList.add(addressEntity1);
        addressEntityList.add(addressEntity2);

        List<CurrentLoanTypeEntity> currentLoanEntityList = new ArrayList();
        currentLoanEntityList.add(currentLoanEntity1);

        List<DepositInfoTypeEntity> depositInfoEntityList = new ArrayList();
        depositInfoEntityList.add(depositInfoEntity);

        List<DocumentEntity> documentEntityList = new ArrayList();
        documentEntityList.add(documentEntity);

        List<RelatedPersonEntity> relatedPersonEntityList = new ArrayList();
        relatedPersonEntityList.add(relatedPersonEntity);

        ClientTypeEntity clientEntity = ClientTypeEntity.builder()
                .clientID("1111111")
                .clientCode("7777")
                .clientUID("111111UID")
                .clientCodeCrm("7777CRM")
                .fullName("Petrov Petr Petrovich")
                .additionalIncome(additionalIncomeEntityList)
                .address(addressEntityList)
                .birthDate(date)
                .adultChildrenNum(2)
                .currentLoan(currentLoanEntityList)
                .depositInfo(depositInfoEntityList)
                .inn("132412355143")
                .regAddrEqualsResAddr(true)
                .isCar(true)
                .jobInfo(jobInfoEntity)
                .document(documentEntityList)
                .phone(phoneList)
                .isRealEstate(true)
                .relatedPerson(relatedPersonEntityList).build();

        AdditionalContactTypeEntity additionalContactEntity = AdditionalContactTypeEntity.builder()
                .phone("23452346256")
                .fio("Ivanov Ivan Petrovich")
                .relationType("1").build();

        AttachedDocumentEntity attachedDocumentEntity = AttachedDocumentEntity.builder()
                .documentName("Анкета")
                .documentDate(date)
                .guid(UUID.randomUUID().toString()).build();

        LoanTypeEntity loanEntity = LoanTypeEntity.builder()
                .currency("RUB")
                .sum(BigDecimal.valueOf(123442.6))
                .issuanceForm("asfwqfwe").build();

        List<AdditionalContactTypeEntity> additionalContactEntityList = new ArrayList();
        additionalContactEntityList.add(additionalContactEntity);

        List<AttachedDocumentEntity> attachedDocumentEntityList = new ArrayList();
        attachedDocumentEntityList.add(attachedDocumentEntity);

        app = ApplicationEntity.builder()
                .client(clientEntity)
                .branch("BRANCH")
                .claimDate(date1)
                .claimNumBpm("qqq111111")
                .branch("1234215")
                .additionalContact(additionalContactEntityList)
                .status("23")
                .attachedDocuments(attachedDocumentEntityList)
                .loan(loanEntity).build();

    }

    @org.junit.Test
    public void save() {
        Transaction transaction = null;
        EntityService es = new EntityService();
        Session session = es.getSession(Model.ASAKA_MICROLOAN);
        transaction = session.beginTransaction();
        Serializable id = es.save(app, session);
        transaction.commit();
        assertNotNull(id);

    }

    @org.junit.Test
    public void getById() {
        Transaction transaction = null;
        EntityService es = new EntityService();
        Session session = es.getSession(Model.ASAKA_MICROLOAN);
        transaction = session.beginTransaction();
        Serializable id = es.save(app, session);
        transaction.commit();

        ApplicationEntity appOut = es.getById(ApplicationEntity.class,
                id, session);
        assertEquals(id, appOut.getApplicationID());
    }
}