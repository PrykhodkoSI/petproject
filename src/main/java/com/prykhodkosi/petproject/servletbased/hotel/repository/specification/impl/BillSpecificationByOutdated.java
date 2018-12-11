package com.prykhodkosi.petproject.servletbased.hotel.repository.specification.impl;

import com.prykhodkosi.petproject.servletbased.hotel.repository.specification.Interface.BillSpecification;

public class BillSpecificationByOutdated implements BillSpecification {
    private Integer days;

    public BillSpecificationByOutdated() {}
    public BillSpecificationByOutdated(Integer days) {
        this.days = days;
    }

    @Override
    public String toSqlClauses() {
        if(days == null) {
            return " where bills.is_paid='false' " +
                    "and DATE(DATE_ADD(bills.billed_date, INTERVAL 2 DAY)) < DATE(NOW())";
        }else {
            return " where bills.is_paid='false' " +
                    "and DATE(DATE_ADD(bills.billed_date, INTERVAL " + days.toString() + " DAY)) < DATE(NOW())";
        }
    }
}