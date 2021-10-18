package ru.digitalspirit.asaka.tasklist.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import ru.digitalspirit.asaka.tasklist.entity.TaskEntity;

public interface TaskRepository extends JpaRepository<TaskEntity, Long>, QuerydslPredicateExecutor<TaskEntity> {

    TaskEntity findOneByTaskIdAndApplicationNumber(String taskId, String applicationNumber);

}
