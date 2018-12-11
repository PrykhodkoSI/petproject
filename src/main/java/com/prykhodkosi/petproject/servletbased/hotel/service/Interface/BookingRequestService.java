package com.prykhodkosi.petproject.servletbased.hotel.service.Interface;

import com.prykhodkosi.petproject.servletbased.hotel.web.dto.CreateBookingRequestDto;
import com.prykhodkosi.petproject.servletbased.hotel.web.dto.ProfileBookingRequestDto;
import com.prykhodkosi.petproject.servletbased.hotel.web.dto.ProfileUserDto;

import java.util.List;

public interface BookingRequestService {
    ProfileBookingRequestDto getBookingRequest(Integer id);
    ProfileBookingRequestDto getBookingRequestWithApartment(Integer id);
    List<ProfileBookingRequestDto> getBookingRequests();
    List<ProfileBookingRequestDto> getBookingRequestsByUser(ProfileUserDto user);
    List<ProfileBookingRequestDto> getBookingRequestsByUserNotAccepted(ProfileUserDto user);
    List<ProfileBookingRequestDto> getBookingRequestNotAccepted();
    ProfileBookingRequestDto postBookingRequest(CreateBookingRequestDto apartment);
    ProfileBookingRequestDto setApartment(Integer requestId, Integer apartmentId);

    ProfileBookingRequestDto accept(Integer userId, Integer requestId);

    ProfileBookingRequestDto deleteBookingRequest(Integer id);

    ProfileBookingRequestDto deleteUsersBookingRequest(Integer userId, Integer requestId);

    ProfileBookingRequestDto getUsersBookingRequest(Integer userId, Integer requestId);
}
