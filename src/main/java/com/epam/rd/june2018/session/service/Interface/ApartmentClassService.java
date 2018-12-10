package com.epam.rd.june2018.session.service.Interface;

import com.epam.rd.june2018.session.model.ApartmentClass;

import java.util.List;

public interface ApartmentClassService {
    ApartmentClass getApartmentClass(Integer id);
    ApartmentClass getApartmentClassByName(String apartmentClass);
    List<ApartmentClass> getApartmentClasses();
    ApartmentClass postApartmentClass(ApartmentClass apartmentClass);
    ApartmentClass putApartmentClass(ApartmentClass apartmentClass);
    ApartmentClass deleteApartmentClass(Integer id);
}
