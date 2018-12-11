package com.prykhodkosi.petproject.servletbased.hotel.repository.specification.impl;

import com.prykhodkosi.petproject.servletbased.hotel.exception.DatabaseException;
import com.prykhodkosi.petproject.servletbased.hotel.model.ApartmentClass;
import com.prykhodkosi.petproject.servletbased.hotel.repository.specification.Interface.ApartmentClassSpecification;

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
