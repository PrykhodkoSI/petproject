package com.epam.rd.june2018.session.validator;

import com.epam.rd.june2018.session.exception.ValidationException;
import com.epam.rd.june2018.session.web.dto.CreateBookingRequestDto;
import com.epam.rd.june2018.session.web.dto.ProfileUserDto;

import java.time.LocalDate;

public class BookingRequestValidatorImpl implements BookingRequestValidator {

    private UserValidator userValidator = new UserValidatorImpl();

    @Override
    public void validateNewRequest(CreateBookingRequestDto createDto) {
        LocalDate arrivalDate = createDto.getArrivalDate();
        LocalDate departureDate = createDto.getDepartureDate();
        Integer capacity = createDto.getCapacity();
        Integer apartmentClassId = createDto.getApartmentClassId();
        ProfileUserDto user = createDto.getUser();
        LocalDate now = LocalDate.now();
        if(arrivalDate == null || arrivalDate.isBefore(now)){
            throw new ValidationException("Incorrect arrivalDate: " + arrivalDate,
                    "exception.validate.request.arrival",
                    arrivalDate != null ? arrivalDate.toString() : null);
        }
        if(departureDate == null || departureDate.isBefore(arrivalDate)){
            throw new ValidationException("Incorrect departureDate: " + departureDate,
                    "exception.validate.request.departure",
                    departureDate != null ? departureDate.toString() : null);
        }
        if(capacity == null || capacity < 1){
            throw new ValidationException("Incorrect room capacity: " + capacity,
                    "exception.validate.request.capacity",
                    capacity != null ? capacity.toString() : null);
        }
        if(apartmentClassId == null || apartmentClassId < 1){
            throw new ValidationException("Incorrect apartmentClass: " + apartmentClassId,
                    "exception.validate.request.class",
                    apartmentClassId != null ? apartmentClassId.toString() : null);
        }
        userValidator.validateProfileUserDto(user);
    }
}
