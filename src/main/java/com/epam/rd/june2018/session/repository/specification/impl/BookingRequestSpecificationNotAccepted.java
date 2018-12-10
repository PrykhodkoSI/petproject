package com.epam.rd.june2018.session.repository.specification.impl;

import com.epam.rd.june2018.session.repository.specification.Interface.BookingRequestSpecification;

public class BookingRequestSpecificationNotAccepted implements BookingRequestSpecification {
    @Override
    public String toSqlClauses() {
        return " where booking_requests.is_accepted='false'";
    }
}
