package com.prykhodkosi.petproject.servletbased.hotel.repository.specification.impl;

import com.prykhodkosi.petproject.servletbased.hotel.repository.specification.Interface.BookingRequestSpecification;

public class BookingRequestSpecificationNotAccepted implements BookingRequestSpecification {
    @Override
    public String toSqlClauses() {
        return " where booking_requests.is_accepted='false'";
    }
}
