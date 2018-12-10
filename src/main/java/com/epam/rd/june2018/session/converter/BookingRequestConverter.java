package com.epam.rd.june2018.session.converter;

import com.epam.rd.june2018.session.model.ApartmentClass;
import com.epam.rd.june2018.session.model.BookingRequest;
import com.epam.rd.june2018.session.web.dto.CreateBillDto;
import com.epam.rd.june2018.session.web.dto.CreateBookingRequestDto;
import com.epam.rd.june2018.session.web.dto.ProfileBookingRequestDto;

import java.time.LocalDate;

import static java.time.temporal.ChronoUnit.DAYS;

public class BookingRequestConverter {

    private UserConverter userConverter = new UserConverter();
    private ApartmentConverter apartmentConverter = new ApartmentConverter();

    public ProfileBookingRequestDto asBookingRequestDto(BookingRequest bookingRequest) {
        if(bookingRequest == null) return null;
        ProfileBookingRequestDto dto = new ProfileBookingRequestDto();
        dto.setId(bookingRequest.getId());
        dto.setArrivalDate(bookingRequest.getArrivalDate());
        dto.setDepartureDate(bookingRequest.getDepartureDate());
        dto.setApartment(apartmentConverter.asApartmentDto(bookingRequest.getApartment()));
        dto.setUser(userConverter.asUserDto(bookingRequest.getUser()));
        ApartmentClass apartmentClass = bookingRequest.getApartmentClass();
        if(apartmentClass != null) {
            dto.setApartmentClassId(apartmentClass.getId());
            dto.setApartmentClassName(apartmentClass.getName());
        }
        dto.setCapacity(bookingRequest.getCapacity());
        dto.setAccepted(bookingRequest.isAccepted());
        return dto;
    }

    public ProfileBookingRequestDto asBookingRequestDto(CreateBookingRequestDto bookingRequest) {
        if(bookingRequest == null) return null;
        ProfileBookingRequestDto dto = new ProfileBookingRequestDto();
        dto.setArrivalDate(bookingRequest.getArrivalDate());
        dto.setDepartureDate(bookingRequest.getDepartureDate());
        dto.setUser(bookingRequest.getUser());
        dto.setApartmentClassId(bookingRequest.getApartmentClassId());
        dto.setApartmentClassName(bookingRequest.getApartmentClassName());
        dto.setCapacity(bookingRequest.getCapacity());
        return dto;
    }

    public BookingRequest asAppBookingRequest(ProfileBookingRequestDto requestDto) {
        if(requestDto == null) return null;
        BookingRequest bookingRequest = new BookingRequest(
                requestDto.getId(),
                requestDto.getCapacity(),
                new ApartmentClass(requestDto.getApartmentClassId(), requestDto.getApartmentClassName()),
                requestDto.getArrivalDate(),
                requestDto.getDepartureDate(),
                userConverter.asAppUser(requestDto.getUser()),
                apartmentConverter.asAppApartment(requestDto.getApartment()),
                requestDto.isAccepted());
        return bookingRequest;
    }
    public BookingRequest asAppBookingRequest(CreateBookingRequestDto requestDto) {
        if(requestDto == null) return null;
        BookingRequest bookingRequest = new BookingRequest(
                requestDto.getCapacity(),
                new ApartmentClass(requestDto.getApartmentClassId(), requestDto.getApartmentClassName()),
                requestDto.getArrivalDate(),
                requestDto.getDepartureDate(),
                userConverter.asAppUser(requestDto.getUser()),
                false);
        return bookingRequest;
    }

    public CreateBillDto asCreateBillDto(ProfileBookingRequestDto requestDto) {
        if(requestDto == null) return null;
        CreateBillDto billDto = new CreateBillDto();
        LocalDate arrivalDate = requestDto.getArrivalDate();
        LocalDate departureDate = requestDto.getDepartureDate();
        int days = (int) DAYS.between(arrivalDate, departureDate);
        Integer price = requestDto.getApartment().getPrice();
        billDto.setBilledDate(LocalDate.now());
        billDto.setPrice(days * price);
        billDto.setBookingRequest(requestDto.getId());
        billDto.setUser(requestDto.getUser().getId());
        return billDto;
    }
}
