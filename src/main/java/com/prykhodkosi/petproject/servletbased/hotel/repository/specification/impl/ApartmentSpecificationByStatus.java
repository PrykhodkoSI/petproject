package com.prykhodkosi.petproject.servletbased.hotel.repository.specification.impl;

import com.prykhodkosi.petproject.servletbased.hotel.repository.specification.Interface.ApartmentSpecification;
import com.prykhodkosi.petproject.servletbased.hotel.exception.DatabaseException;
import com.prykhodkosi.petproject.servletbased.hotel.model.ApartmentStatus;

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
