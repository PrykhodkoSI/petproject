package com.prykhodkosi.petproject.servletbased.hotel.repository.specification.impl;

import com.prykhodkosi.petproject.servletbased.hotel.repository.specification.Interface.BillSpecification;
import com.prykhodkosi.petproject.servletbased.hotel.exception.DatabaseException;

public class BillSpecificationByBookingRequest implements BillSpecification {
    private Integer bookingRequest;

    public BillSpecificationByBookingRequest(Integer bookingRequest) {
        this.bookingRequest = bookingRequest;
    }

    @Override
    public String toSqlClauses() {
        if(bookingRequest != null && bookingRequest > 0){
            return " where bills.booking_request_id=" + bookingRequest;
        }
        throw new DatabaseException("Bad specification");
    }
}
