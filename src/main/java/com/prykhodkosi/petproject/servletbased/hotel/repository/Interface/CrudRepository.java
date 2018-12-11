package com.prykhodkosi.petproject.servletbased.hotel.repository.Interface;

import com.prykhodkosi.petproject.servletbased.hotel.repository.specification.Interface.SqlSpecification;

import java.util.List;

public interface CrudRepository<T, S extends SqlSpecification>{

    Boolean create(T entity);

    List<T> read(S specification);

    Boolean update(T entity);

    Boolean delete(T entity);
}
