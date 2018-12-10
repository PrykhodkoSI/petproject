package com.epam.rd.june2018.session.repository.Interface;

import com.epam.rd.june2018.session.model.Apartment;
import com.epam.rd.june2018.session.repository.specification.Interface.ApartmentSpecification;

public interface ApartmentRepository extends CrudRepository<Apartment, ApartmentSpecification> {
}
