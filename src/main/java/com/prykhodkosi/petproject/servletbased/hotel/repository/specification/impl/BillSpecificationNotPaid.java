package com.prykhodkosi.petproject.servletbased.hotel.repository.specification.impl;

import com.prykhodkosi.petproject.servletbased.hotel.repository.specification.Interface.BillSpecification;

public class BillSpecificationNotPaid implements BillSpecification {
    @Override
    public String toSqlClauses() {
        return " where bills.is_paid='false'";
    }
}
