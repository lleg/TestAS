package ru.digitalspirit.asaka.orm.repository.microloan;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.digitalspirit.asaka.bpm.entity.ApplicationEntity;
import ru.digitalspirit.asaka.orm.repository.AbstractRepository;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

@Repository("microloanApplicationRepository")
public interface ApplicationRepository extends AbstractRepository<ApplicationEntity> {

    ApplicationEntity findOneByClaimNumBpm(String number);

    boolean existsByClaimNumBpm(String number);

    @Query("select a from ApplicationEntity a where a.claimDate between :fromDate and :toDate")
    List<ApplicationEntity> findByPeriod(@Param("fromDate") Date startDate, @Param("toDate") Date endDate);

}
