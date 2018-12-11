package com.prykhodkosi.petproject.servletbased.hotel.repository.specification.impl;

import com.prykhodkosi.petproject.servletbased.hotel.repository.specification.Interface.UserSpecification;
import com.prykhodkosi.petproject.servletbased.hotel.exception.DatabaseException;
import com.prykhodkosi.petproject.servletbased.hotel.model.User;

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
