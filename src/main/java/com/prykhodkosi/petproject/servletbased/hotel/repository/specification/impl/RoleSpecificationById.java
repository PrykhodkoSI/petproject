package com.prykhodkosi.petproject.servletbased.hotel.repository.specification.impl;

import com.prykhodkosi.petproject.servletbased.hotel.repository.specification.Interface.RoleSpecification;
import com.prykhodkosi.petproject.servletbased.hotel.exception.DatabaseException;
import com.prykhodkosi.petproject.servletbased.hotel.model.Role;

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
