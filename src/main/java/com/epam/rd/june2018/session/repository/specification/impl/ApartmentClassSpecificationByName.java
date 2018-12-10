package com.epam.rd.june2018.session.repository.specification.impl;

import com.epam.rd.june2018.session.exception.DatabaseException;
import com.epam.rd.june2018.session.model.ApartmentClass;
import com.epam.rd.june2018.session.repository.specification.Interface.ApartmentClassSpecification;

public class ApartmentClassSpecificationByName implements ApartmentClassSpecification {
    private ApartmentClass apartmentClass;

    public ApartmentClassSpecificationByName(ApartmentClass apartmentClass) {
        this.apartmentClass = apartmentClass;
    }

    @Override
    public String toSqlClauses() {
        if(apartmentClass.getName() != null)
            return " WHERE apartment_classes.name='" + apartmentClass.getName() + "'";
        throw new DatabaseException("Bad specification");
    }
}
