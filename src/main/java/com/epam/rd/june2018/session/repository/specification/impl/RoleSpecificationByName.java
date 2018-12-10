package com.epam.rd.june2018.session.repository.specification.impl;

import com.epam.rd.june2018.session.exception.DatabaseException;
import com.epam.rd.june2018.session.model.Role;
import com.epam.rd.june2018.session.repository.specification.Interface.RoleSpecification;

public class RoleSpecificationByName implements RoleSpecification {
    private Role role;
    public RoleSpecificationByName(Role role) {
        this.role = role;
    }

    @Override
    public String toSqlClauses() {
        if(role.getName() != null)
            return " WHERE roles.name='" + role.getName() + "'";
        throw new DatabaseException("Bad specification");
    }
}
