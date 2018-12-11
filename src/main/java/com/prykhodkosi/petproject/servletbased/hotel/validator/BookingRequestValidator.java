package com.prykhodkosi.petproject.servletbased.hotel.validator;

import com.prykhodkosi.petproject.servletbased.hotel.web.dto.CreateBookingRequestDto;

public interface BookingRequestValidator {
    void validateNewRequest(CreateBookingRequestDto createDto);
}
