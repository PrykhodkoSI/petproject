package com.prykhodkosi.petproject.servletbased.hotel.repository.specification.impl;

import com.prykhodkosi.petproject.servletbased.hotel.exception.DatabaseException;
import com.prykhodkosi.petproject.servletbased.hotel.repository.specification.Interface.UserSpecification;

public class UserSpecificationByEmail implements UserSpecification {
    private String email;

    public UserSpecificationByEmail(String email) {
        this.email = email;
    }

    @Override
    public String toSqlClauses() {
        if(email != null) {
            return String.format(" WHERE users.email='%s'", email);
        }
        throw new DatabaseException("Bad specification");
    }
}
