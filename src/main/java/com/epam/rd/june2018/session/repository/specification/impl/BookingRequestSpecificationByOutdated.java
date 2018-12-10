package com.epam.rd.june2018.session.repository.specification.impl;

import com.epam.rd.june2018.session.repository.specification.Interface.BookingRequestSpecification;

public class BookingRequestSpecificationByOutdated implements BookingRequestSpecification {
    Integer days = null;

    public BookingRequestSpecificationByOutdated() {}
    public BookingRequestSpecificationByOutdated(Integer days) {
        this.days = days;
    }

    @Override
    public String toSqlClauses() {
        if(days == null) {
            return " where id in (" +
                    "SELECT bills.booking_request_id from bills " +
                    "where bills.is_paid='false' " +
                    "and DATE(DATE_ADD(bills.billed_date, INTERVAL 2 DAY)) < DATE(NOW()))";
        }else {
            return " where id in (" +
                    "SELECT bills.booking_request_id from bills " +
                    "where bills.is_paid='false' " +
                    "and DATE(DATE_ADD(bills.billed_date, INTERVAL " + days.toString() +" DAY)) < DATE(NOW()))";
        }
    }
}
