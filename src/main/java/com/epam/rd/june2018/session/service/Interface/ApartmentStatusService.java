package com.epam.rd.june2018.session.service.Interface;

import com.epam.rd.june2018.session.model.ApartmentStatus;

import java.util.List;

public interface ApartmentStatusService {
    ApartmentStatus getApartmentStatus(Integer id);
    ApartmentStatus getApartmentStatusByName(String apartmentStatus);
    List<ApartmentStatus> getApartmentStatuses();
    ApartmentStatus postApartmentStatus(ApartmentStatus apartmentStatus);
    ApartmentStatus putApartmentStatus(ApartmentStatus apartmentStatus);
    ApartmentStatus deleteApartmentStatus(Integer id);
}
