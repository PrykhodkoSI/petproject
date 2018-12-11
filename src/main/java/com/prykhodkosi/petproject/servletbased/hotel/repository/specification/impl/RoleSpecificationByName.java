package com.prykhodkosi.petproject.servletbased.hotel.repository.specification.impl;

import com.prykhodkosi.petproject.servletbased.hotel.repository.specification.Interface.RoleSpecification;
import com.prykhodkosi.petproject.servletbased.hotel.exception.DatabaseException;
import com.prykhodkosi.petproject.servletbased.hotel.model.Role;

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
