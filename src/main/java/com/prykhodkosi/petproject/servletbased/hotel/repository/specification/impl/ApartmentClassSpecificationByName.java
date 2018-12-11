package com.prykhodkosi.petproject.servletbased.hotel.repository.specification.impl;

import com.prykhodkosi.petproject.servletbased.hotel.repository.specification.Interface.ApartmentClassSpecification;
import com.prykhodkosi.petproject.servletbased.hotel.exception.DatabaseException;
import com.prykhodkosi.petproject.servletbased.hotel.model.ApartmentClass;

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
