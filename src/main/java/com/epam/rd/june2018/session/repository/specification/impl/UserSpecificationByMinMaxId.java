package com.epam.rd.june2018.session.repository.specification.impl;

import com.epam.rd.june2018.session.repository.specification.Interface.UserSpecification;

public class UserSpecificationByMinMaxId implements UserSpecification {
    private int min;
    private int max;

    public UserSpecificationByMinMaxId(int min, int max) {
        this.min = min;
        this.max = max;
    }

    @Override
    public String toSqlClauses() {
            return " WHERE users.id>=" + min + " AND users.id<=" + max;
    }
}