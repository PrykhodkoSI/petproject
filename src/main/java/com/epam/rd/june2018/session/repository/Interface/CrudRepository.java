package com.epam.rd.june2018.session.repository.Interface;

import com.epam.rd.june2018.session.repository.specification.Interface.SqlSpecification;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface CrudRepository<T, S extends SqlSpecification>{

    Boolean create(T entity);

    List<T> read(S specification);

    Boolean update(T entity);

    Boolean delete(T entity);
}
