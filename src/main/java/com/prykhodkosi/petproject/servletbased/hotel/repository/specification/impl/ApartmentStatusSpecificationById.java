package com.prykhodkosi.petproject.servletbased.hotel.repository.specification.impl;

import com.prykhodkosi.petproject.servletbased.hotel.repository.specification.Interface.ApartmentStatusSpecification;
import com.prykhodkosi.petproject.servletbased.hotel.exception.DatabaseException;
import com.prykhodkosi.petproject.servletbased.hotel.model.ApartmentStatus;

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
