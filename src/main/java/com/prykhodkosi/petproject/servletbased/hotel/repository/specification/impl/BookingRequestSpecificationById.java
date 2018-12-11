package com.prykhodkosi.petproject.servletbased.hotel.repository.specification.impl;

import com.prykhodkosi.petproject.servletbased.hotel.repository.specification.Interface.BookingRequestSpecification;
import com.prykhodkosi.petproject.servletbased.hotel.exception.DatabaseException;
import com.prykhodkosi.petproject.servletbased.hotel.model.BookingRequest;

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
