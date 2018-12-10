package com.epam.rd.june2018.session.converter;

import com.epam.rd.june2018.session.model.Apartment;
import com.epam.rd.june2018.session.model.ApartmentClass;
import com.epam.rd.june2018.session.model.ApartmentStatus;
import com.epam.rd.june2018.session.web.dto.CreateApartmentDto;
import com.epam.rd.june2018.session.web.dto.ProfileApartmentDto;
import com.epam.rd.june2018.session.web.dto.UpdateApartmentDto;


public class ApartmentConverter {
    public ProfileApartmentDto asApartmentDto(Apartment apartment) {
        if(apartment == null) return null;
        ProfileApartmentDto dto = new ProfileApartmentDto();
        dto.setId(apartment.getId());
        dto.setName(apartment.getName());
        dto.setCapacity(apartment.getCapacity());
        dto.setPrice(apartment.getPrice());
        ApartmentClass apartmentClass = apartment.getApartmentClass();
        if(apartmentClass != null) {
            dto.setApartmentClassId(apartmentClass.getId());
            dto.setApartmentClassName(apartmentClass.getName());
        }
        ApartmentStatus apartmentStatus = apartment.getApartmentStatus();
        if(apartmentClass != null) {
            dto.setApartmentStatusId(apartmentStatus.getId());
            dto.setApartmentStatusName(apartmentStatus.getName());
        }
        return dto;
    }

    public ProfileApartmentDto asApartmentDto(CreateApartmentDto apartment) {
        if(apartment == null) return null;
        ProfileApartmentDto dto = new ProfileApartmentDto();
        dto.setName(apartment.getName());
        dto.setCapacity(apartment.getCapacity());
        dto.setPrice(apartment.getPrice());
        return dto;
    }

    public Apartment asAppApartment(ProfileApartmentDto apartmentDto) {
        if(apartmentDto == null) return null;
        ApartmentClass apartmentClass = new ApartmentClass(apartmentDto.getApartmentClassId(), apartmentDto.getApartmentClassName());
        ApartmentStatus apartmentStatus = new ApartmentStatus(apartmentDto.getApartmentStatusId(), apartmentDto.getApartmentClassName());
        Apartment apartment = new Apartment(apartmentDto.getId(),
                apartmentDto.getName(),
                apartmentDto.getPrice(),
                apartmentDto.getCapacity(),
                apartmentClass,
                apartmentStatus);
        return apartment;
    }
    public Apartment asAppApartment(CreateApartmentDto apartmentDto) {
        if(apartmentDto == null) return null;
        Apartment apartment = new Apartment(apartmentDto.getName(),
                apartmentDto.getPrice(),
                apartmentDto.getCapacity(),
                apartmentDto.getApartmentClassId(),
                apartmentDto.getApartmentStatusId());
        return apartment;
    }

    public Apartment asAppApartment(UpdateApartmentDto apartmentDto) {
        if(apartmentDto == null) return null;
        Apartment apartment = new Apartment(apartmentDto.getId(),
                apartmentDto.getName(),
                apartmentDto.getPrice(),
                apartmentDto.getCapacity(),
                apartmentDto.getApartmentClassId(),
                apartmentDto.getApartmentStatusId());
        return apartment;
    }

    public UpdateApartmentDto asAppApartment(Apartment apartment) {
        if(apartment == null) return null;
        UpdateApartmentDto apartmentDto = new UpdateApartmentDto();
        apartmentDto.setId(apartment.getId());
        apartmentDto.setName(apartment.getName());
        apartmentDto.setCapacity(apartment.getCapacity());
        apartmentDto.setPrice(apartment.getPrice());
        apartmentDto.setApartmentClassId(apartment.getApartmentClass().getId());
        apartmentDto.setApartmentStatusId(apartment.getApartmentStatus().getId());
        return apartmentDto;
    }
}
