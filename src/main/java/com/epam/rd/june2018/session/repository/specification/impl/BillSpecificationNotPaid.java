package com.epam.rd.june2018.session.repository.specification.impl;

import com.epam.rd.june2018.session.repository.specification.Interface.BillSpecification;

public class BillSpecificationNotPaid implements BillSpecification {
    @Override
    public String toSqlClauses() {
        return " where bills.is_paid='false'";
    }
}
