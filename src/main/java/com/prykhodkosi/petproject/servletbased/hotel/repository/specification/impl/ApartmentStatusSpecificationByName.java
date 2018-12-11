package com.prykhodkosi.petproject.servletbased.hotel.repository.specification.impl;

import com.prykhodkosi.petproject.servletbased.hotel.repository.specification.Interface.ApartmentStatusSpecification;
import com.prykhodkosi.petproject.servletbased.hotel.exception.DatabaseException;
import com.prykhodkosi.petproject.servletbased.hotel.model.ApartmentStatus;

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
