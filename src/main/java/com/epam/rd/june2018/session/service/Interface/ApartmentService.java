package com.epam.rd.june2018.session.service.Interface;

import com.epam.rd.june2018.session.model.Apartment;
import com.epam.rd.june2018.session.model.ApartmentStatus;
import com.epam.rd.june2018.session.web.dto.CreateApartmentDto;
import com.epam.rd.june2018.session.web.dto.ProfileApartmentDto;
import com.epam.rd.june2018.session.web.dto.UpdateApartmentDto;

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
