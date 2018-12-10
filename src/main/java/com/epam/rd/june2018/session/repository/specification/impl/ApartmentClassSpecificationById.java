package com.epam.rd.june2018.session.repository.specification.impl;

import com.epam.rd.june2018.session.exception.DatabaseException;
import com.epam.rd.june2018.session.model.ApartmentClass;
import com.epam.rd.june2018.session.repository.specification.Interface.ApartmentClassSpecification;

public class ApartmentClassSpecificationById implements ApartmentClassSpecification {
    ApartmentClass apartmentClass;
    public ApartmentClassSpecificationById(ApartmentClass apartmentClass) {
        this.apartmentClass = apartmentClass;
    }

    @Override
    public String toSqlClauses() {
        if(apartmentClass != null && apartmentClass.getId() > 0)
            return " WHERE apartment_classes.id=" + apartmentClass.getId();
        throw new DatabaseException("Bad specification");
    }
}
