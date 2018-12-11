package com.prykhodkosi.petproject.servletbased.hotel.service.Interface;

import com.prykhodkosi.petproject.servletbased.hotel.model.ApartmentStatus;
import com.prykhodkosi.petproject.servletbased.hotel.web.dto.CreateApartmentDto;
import com.prykhodkosi.petproject.servletbased.hotel.web.dto.ProfileApartmentDto;
import com.prykhodkosi.petproject.servletbased.hotel.web.dto.UpdateApartmentDto;

import java.time.LocalDate;
import java.util.List;

public interface ApartmentService {
    ProfileApartmentDto getApartment(Integer id);
    List<ProfileApartmentDto> getApartments();
    ProfileApartmentDto postApartment(CreateApartmentDto apartment);
    ProfileApartmentDto putApartment(UpdateApartmentDto apartment);
    ProfileApartmentDto deleteApartment(Integer id);
    List<ProfileApartmentDto> getApartmentsByStatus(ApartmentStatus status);
    List<ProfileApartmentDto> getFreeApartments();
    List<ProfileApartmentDto> getFreeApartments(LocalDate arrival, LocalDate departure);
}
