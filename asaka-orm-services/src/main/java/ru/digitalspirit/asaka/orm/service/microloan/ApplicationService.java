package ru.digitalspirit.asaka.orm.service.microloan;

import org.springframework.stereotype.Service;
import ru.digitalspirit.asaka.bpm.entity.ApplicationEntity;
import ru.digitalspirit.asaka.microloan.dto.ApplicationDTO;
import ru.digitalspirit.asaka.orm.repository.microloan.ApplicationRepository;
import ru.digitalspirit.asaka.orm.service.AbstractService;

import java.math.BigInteger;
import java.util.Optional;

@Service("microloanApplicationService")
public class ApplicationService extends AbstractService<ApplicationEntity, ApplicationDTO> {

    public ApplicationEntity findByClimeBpmNumber(String climeBpmNumber) {
        return ((ApplicationRepository)repository).findOneByClaimNumBpm(climeBpmNumber);
    }

    public ApplicationEntity findByApplicationId(BigInteger applicationId) {
        Optional<ApplicationEntity> application = ((ApplicationRepository)repository).findById(applicationId);
        return application.isPresent() ? application.get() : null;
    }

    public boolean existsByApplicationNumber(String applicationNumber) {
        return ((ApplicationRepository)repository).existsByClaimNumBpm(applicationNumber);
    }

    public ApplicationDTO findByAppNumberDTO(String applicationNumber) {
        return (mapperFacade.map(findByClimeBpmNumber(applicationNumber), ApplicationDTO.class));
    }



}
