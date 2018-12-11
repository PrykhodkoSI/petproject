package com.prykhodkosi.petproject.servletbased.hotel.service.Interface;

import com.prykhodkosi.petproject.servletbased.hotel.model.ApartmentStatus;

import java.util.List;

public interface ApartmentStatusService {
    ApartmentStatus getApartmentStatus(Integer id);
    ApartmentStatus getApartmentStatusByName(String apartmentStatus);
    List<ApartmentStatus> getApartmentStatuses();
    ApartmentStatus postApartmentStatus(ApartmentStatus apartmentStatus);
    ApartmentStatus putApartmentStatus(ApartmentStatus apartmentStatus);
    ApartmentStatus deleteApartmentStatus(Integer id);
}
