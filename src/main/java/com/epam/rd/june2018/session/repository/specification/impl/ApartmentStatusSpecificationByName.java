package com.epam.rd.june2018.session.repository.specification.impl;

import com.epam.rd.june2018.session.exception.DatabaseException;
import com.epam.rd.june2018.session.model.ApartmentStatus;
import com.epam.rd.june2018.session.repository.specification.Interface.ApartmentStatusSpecification;

public class ApartmentStatusSpecificationByName implements ApartmentStatusSpecification {
    private ApartmentStatus apartmentStatus;

    public ApartmentStatusSpecificationByName(ApartmentStatus apartmentStatus) {
        this.apartmentStatus = apartmentStatus;
    }

    @Override
    public String toSqlClauses() {
        if(apartmentStatus.getName() != null)
            return " WHERE apartment_statuses.name='" + apartmentStatus.getName() + "'";
        throw new DatabaseException("Bad specification");
    }
}
