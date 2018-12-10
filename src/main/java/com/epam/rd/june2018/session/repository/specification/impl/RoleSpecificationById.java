package com.epam.rd.june2018.session.repository.specification.impl;

import com.epam.rd.june2018.session.exception.DatabaseException;
import com.epam.rd.june2018.session.model.Role;
import com.epam.rd.june2018.session.repository.specification.Interface.RoleSpecification;

public class RoleSpecificationById implements RoleSpecification {
    private Role role;
    public RoleSpecificationById(Role role) {
        this.role = role;
    }


    @Override
    public String toSqlClauses() {
        if(role != null && role.getId() > 0)
            return " WHERE roles.id=" + role.getId();
        throw new DatabaseException("Bad specification");
    }
}
