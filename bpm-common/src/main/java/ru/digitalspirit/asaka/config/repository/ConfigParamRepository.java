package ru.digitalspirit.asaka.config.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.digitalspirit.asaka.config.entity.ConfigParam;


@Repository("configParamRepository")
public interface ConfigParamRepository  extends JpaRepository<ConfigParam, Integer> {

    ConfigParam findOneByCode(String code);

}
