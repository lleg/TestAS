package ru.digitalspirit.asaka.orm.service;


import com.google.common.reflect.TypeToken;
import ma.glasnost.orika.MapperFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import ru.digitalspirit.asaka.orm.repository.AbstractRepository;


import java.math.BigInteger;

@Transactional(transactionManager = "ormMicroloanModelTransactionManager")
public abstract class AbstractService<E, D> {

    private final TypeToken<D> dtoTypeToken = new TypeToken<D>(getClass()) { };
    private final Class<D> dtoCLass = (Class<D>)dtoTypeToken.getType();

    @Autowired
    protected AbstractRepository<E> repository;

    @Autowired
    protected MapperFacade mapperFacade;

    public D getById(BigInteger id) {
        return mapperFacade.map(repository.findById(id), dtoCLass);
    }

    public E save(E entityClass) {
        return repository.save(entityClass);
    }

    public void delete(BigInteger id) {
        repository.deleteById(id);
    }
}
