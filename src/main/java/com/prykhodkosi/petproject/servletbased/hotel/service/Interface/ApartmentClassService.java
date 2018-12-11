package com.prykhodkosi.petproject.servletbased.hotel.service.Interface;

import com.prykhodkosi.petproject.servletbased.hotel.model.ApartmentClass;

import java.util.List;

public interface ApartmentClassService {
    ApartmentClass getApartmentClass(Integer id);
    ApartmentClass getApartmentClassByName(String apartmentClass);
    List<ApartmentClass> getApartmentClasses();
    ApartmentClass postApartmentClass(ApartmentClass apartmentClass);
    ApartmentClass putApartmentClass(ApartmentClass apartmentClass);
    ApartmentClass deleteApartmentClass(Integer id);
}
