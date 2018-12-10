package com.epam.rd.june2018.session.repository.specification.impl;

import com.epam.rd.june2018.session.exception.DatabaseException;
import com.epam.rd.june2018.session.model.ApartmentStatus;
import com.epam.rd.june2018.session.repository.specification.Interface.ApartmentStatusSpecification;

public class ApartmentStatusSpecificationById implements ApartmentStatusSpecification {
    ApartmentStatus apartmentStatus;
    public ApartmentStatusSpecificationById(ApartmentStatus apartmentStatus) {
        this.apartmentStatus = apartmentStatus;
    }

    @Override
    public String toSqlClauses() {
        if(apartmentStatus != null && apartmentStatus.getId() > 0)
            return " WHERE apartment_statuses.id=" + apartmentStatus.getId();
        throw new DatabaseException("Bad specification");
    }
}
