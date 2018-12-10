package com.epam.rd.june2018.session.repository.specification.impl;

import com.epam.rd.june2018.session.exception.DatabaseException;
import com.epam.rd.june2018.session.repository.specification.Interface.UserSpecification;

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
