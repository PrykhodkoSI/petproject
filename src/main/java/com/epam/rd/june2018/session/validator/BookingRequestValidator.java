package com.epam.rd.june2018.session.validator;

import com.epam.rd.june2018.session.web.dto.CreateBookingRequestDto;

public interface BookingRequestValidator {
    void validateNewRequest(CreateBookingRequestDto createDto);
}
