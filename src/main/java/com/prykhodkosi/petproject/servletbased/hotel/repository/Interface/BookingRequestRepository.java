package com.prykhodkosi.petproject.servletbased.hotel.repository.Interface;

import com.prykhodkosi.petproject.servletbased.hotel.repository.specification.Interface.BookingRequestSpecification;
import com.prykhodkosi.petproject.servletbased.hotel.model.BookingRequest;

public interface BookingRequestRepository extends CrudRepository<BookingRequest, BookingRequestSpecification> {
}
