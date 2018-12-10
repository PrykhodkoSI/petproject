package com.epam.rd.june2018.session.repository.Interface;

import com.epam.rd.june2018.session.model.BookingRequest;
import com.epam.rd.june2018.session.repository.specification.Interface.BookingRequestSpecification;

public interface BookingRequestRepository extends CrudRepository<BookingRequest, BookingRequestSpecification> {
}
