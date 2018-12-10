package com.epam.rd.june2018.session.repository.specification.impl;

import com.epam.rd.june2018.session.exception.DatabaseException;
import com.epam.rd.june2018.session.model.User;
import com.epam.rd.june2018.session.repository.specification.Interface.UserSpecification;

public class UserSpecificationById implements UserSpecification {
    private User user;

    public UserSpecificationById(User user) {
        this.user = user;
    }

    @Override
    public String toSqlClauses() {
        if(user != null && user.getId() > 0)
            return " WHERE users.id=" + user.getId();
        throw new DatabaseException("Bad specification");
    }
}
