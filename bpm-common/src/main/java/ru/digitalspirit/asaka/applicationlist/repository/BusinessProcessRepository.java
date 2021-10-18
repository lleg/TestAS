package ru.digitalspirit.asaka.applicationlist.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.digitalspirit.asaka.applicationlist.entity.BusinessProcess;

import java.util.List;

public interface BusinessProcessRepository extends JpaRepository<BusinessProcess, Long> {

    @Query("select b from BusinessProcess b where b.active = true")
    List<BusinessProcess> findAll();

    BusinessProcess findOneByCode(String code);

    BusinessProcess findOneBySubjectId(String subjectId);

}
