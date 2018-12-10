package com.epam.rd.june2018.session.repository.specification.impl;

import com.epam.rd.june2018.session.exception.DatabaseException;
import com.epam.rd.june2018.session.model.ApartmentStatus;
import com.epam.rd.june2018.session.repository.specification.Interface.ApartmentSpecification;

public class ApartmentSpecificationByStatus implements ApartmentSpecification {
    ApartmentStatus apartmentStatus;
    public ApartmentSpecificationByStatus(ApartmentStatus status) {
        this.apartmentStatus = status;
    }

    @Override
    public String toSqlClauses() {
        if(apartmentStatus != null) {
            if (apartmentStatus.getId() != null && apartmentStatus.getId() > 0) {
                return " WHERE apartments.apartment_status_id=" + apartmentStatus.getId();

            } else if (apartmentStatus.getName() != null) {
                return " left join apartment_statuses " +
                        "on apartments.apartment_status_id=apartment_statuses.id " +
                        "WHERE apartment_statuses.name='" + apartmentStatus.getName() + "'";
            }
            else {
                throw new DatabaseException("Bad specification");
            }
        }
        else {
            throw new DatabaseException("Bad specification");
        }
    }
}
