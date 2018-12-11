package com.prykhodkosi.petproject.servletbased.hotel.repository.specification.impl;

import com.prykhodkosi.petproject.servletbased.hotel.repository.specification.Interface.ApartmentSpecification;
import com.prykhodkosi.petproject.servletbased.hotel.exception.DatabaseException;
import com.prykhodkosi.petproject.servletbased.hotel.model.Apartment;

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
