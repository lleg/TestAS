package ru.digitalspirit.asaka.orm.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.math.BigInteger;

@NoRepositoryBean
public interface AbstractRepository<E> extends CrudRepository<E, BigInteger> {

}
