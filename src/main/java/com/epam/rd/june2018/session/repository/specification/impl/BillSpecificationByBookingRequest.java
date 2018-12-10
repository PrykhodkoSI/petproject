package com.epam.rd.june2018.session.repository.specification.impl;

import com.epam.rd.june2018.session.exception.DatabaseException;
import com.epam.rd.june2018.session.repository.specification.Interface.BillSpecification;
import com.epam.rd.june2018.session.web.dto.ProfileBookingRequestDto;

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
