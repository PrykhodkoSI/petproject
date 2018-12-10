package com.epam.rd.june2018.session.repository.specification.impl;

import com.epam.rd.june2018.session.exception.DatabaseException;
import com.epam.rd.june2018.session.model.User;
import com.epam.rd.june2018.session.repository.specification.Interface.RoleSpecification;

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
