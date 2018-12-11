package com.prykhodkosi.petproject.servletbased.hotel.repository.specification.impl;

import com.prykhodkosi.petproject.servletbased.hotel.repository.specification.Interface.RoleSpecification;
import com.prykhodkosi.petproject.servletbased.hotel.exception.DatabaseException;
import com.prykhodkosi.petproject.servletbased.hotel.model.User;

public class RoleSpecificationByUser implements RoleSpecification {
    private User user;

    public RoleSpecificationByUser(User user) {
        this.user = user;
    }


    @Override
    public String toSqlClauses() {
        if(user != null && user.getId() > 0) {
            return " left join users_roles " +
                    "on users_roles.role_id=roles.id " +
                    "WHERE users_roles.user_id=" + user.getId();
        }
        throw new DatabaseException("Bad specification");
    }
}
