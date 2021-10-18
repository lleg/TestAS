package ru.digitalspirit.asaka.applicationlist.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import ru.digitalspirit.asaka.applicationlist.entity.ApplicationStatusEntity;

public interface ApplicationStatusRepository extends JpaRepository<ApplicationStatusEntity, Long>, QuerydslPredicateExecutor<ApplicationStatusEntity> {
    ApplicationStatusEntity findByApplicationId(String applicationId);
}
