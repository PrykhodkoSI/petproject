package com.epam.rd.june2018.session.repository.specification.impl;

import com.epam.rd.june2018.session.exception.DatabaseException;
import com.epam.rd.june2018.session.model.BookingRequest;
import com.epam.rd.june2018.session.repository.specification.Interface.BookingRequestSpecification;

public class BookingRequestSpecificationById implements BookingRequestSpecification {
    BookingRequest bookingRequest;

    public BookingRequestSpecificationById(BookingRequest bookingRequest) {
        this.bookingRequest = bookingRequest;
    }

    @Override
    public String toSqlClauses() {
        if(bookingRequest != null && bookingRequest.getId() > 0)
            return " WHERE booking_requests.id=" + bookingRequest.getId();
        throw new DatabaseException("Bad specification");
    }
}
