package com.epam.rd.june2018.session.repository.specification.impl;

import com.epam.rd.june2018.session.exception.DatabaseException;
import com.epam.rd.june2018.session.model.Apartment;
import com.epam.rd.june2018.session.repository.specification.Interface.ApartmentSpecification;

public class ApartmentSpecificationById implements ApartmentSpecification {

    Apartment apartment;

    public ApartmentSpecificationById(Apartment apartment) {
        this.apartment = apartment;
    }

    @Override
    public String toSqlClauses() {
        if(apartment != null && apartment.getId() > 0)
            return " WHERE apartments.id=" + apartment.getId();
        throw new DatabaseException("Bad specification");
    }
}
