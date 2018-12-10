package com.epam.rd.june2018.session.service.Impl;

import com.epam.rd.june2018.session.converter.ApartmentConverter;
import com.epam.rd.june2018.session.exception.ApplicationException;
import com.epam.rd.june2018.session.model.Apartment;
import com.epam.rd.june2018.session.model.ApartmentClass;
import com.epam.rd.june2018.session.model.ApartmentStatus;
import com.epam.rd.june2018.session.repository.Interface.ApartmentClassRepository;
import com.epam.rd.june2018.session.repository.Interface.ApartmentRepository;
import com.epam.rd.june2018.session.repository.Interface.ApartmentStatusRepository;
import com.epam.rd.june2018.session.repository.RepositoryFabric;
import com.epam.rd.june2018.session.repository.specification.impl.*;
import com.epam.rd.june2018.session.service.Interface.ApartmentService;
import com.epam.rd.june2018.session.web.dto.CreateApartmentDto;
import com.epam.rd.june2018.session.web.dto.ProfileApartmentDto;
import com.epam.rd.june2018.session.web.dto.UpdateApartmentDto;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class ApartmentServiceImpl implements ApartmentService {
    private ApartmentRepository apartmentRepository = RepositoryFabric.getApartmentRepository();
    private ApartmentClassRepository apartmentClassRepository = RepositoryFabric.getApartmentClassRepository();
    private ApartmentStatusRepository apartmentStatusRepository = RepositoryFabric.getApartmentStatusRepository();
    private ApartmentConverter apartmentConverter = new ApartmentConverter();
    @Override
    public ProfileApartmentDto getApartment(Integer id) {
        Apartment apartmentToRead = new Apartment(id);
        List<Apartment> apartments = apartmentRepository
                .read(new ApartmentSpecificationById(apartmentToRead));
        if(apartments == null || apartments.isEmpty()){
            throw new ApplicationException("No such Apartment: " + id,
                    "exception.apartment.noApartment",
                    id.toString());
        }
        Apartment apartment = apartments.iterator().next();
        return getApartmentDto(apartment);
    }

    private ProfileApartmentDto getApartmentDto(Apartment apartment) {
        ProfileApartmentDto profileApartmentDto = apartmentConverter.asApartmentDto(apartment);
        ApartmentClass apartmentClassForApartment = getApartmentClassForApartment(apartment);
        ApartmentStatus apartmentStatusForApartment = populateApartmentStatus(apartment);
        profileApartmentDto.setApartmentClassId(apartmentClassForApartment.getId());
        profileApartmentDto.setApartmentClassName(apartmentClassForApartment.getName());
        profileApartmentDto.setApartmentStatusId(apartmentStatusForApartment.getId());
        profileApartmentDto.setApartmentStatusName(apartmentStatusForApartment.getName());
        return profileApartmentDto;
    }

    private ApartmentStatus populateApartmentStatus(Apartment apartment) {
        ApartmentStatus apartmentStatusToRead = new ApartmentStatus(apartment.getApartmentStatus().getId());
        List<ApartmentStatus> apartmentStatuses = apartmentStatusRepository
                .read(new ApartmentStatusSpecificationById(apartmentStatusToRead));
        if(apartmentStatuses == null || apartmentStatuses.isEmpty()){
            throw new ApplicationException("No such ApartmentStatus: " + apartmentStatusToRead.getId(),
                    "exception.apartment.noStatus",
                    apartment.getId().toString());
        }
        return apartmentStatuses.iterator().next();
    }

    private ApartmentClass getApartmentClassForApartment(Apartment apartment) {
        ApartmentClass apartmentClassToRead = new ApartmentClass(apartment.getApartmentClass().getId());
        List<ApartmentClass> apartmentClasses = apartmentClassRepository
                .read(new ApartmentClassSpecificationById(apartmentClassToRead));
        if(apartmentClasses == null || apartmentClasses.isEmpty()){
            throw new ApplicationException("No such ApartmentClass: " + apartmentClassToRead.getId(),
                    "exception.apartment.noClass",
                    apartmentClassToRead.getId().toString());
        }
        return apartmentClasses.iterator().next();
    }

    @Override
    public List<ProfileApartmentDto> getApartments() {
        List<Apartment> apartments = apartmentRepository.read(new ApartmentSpecificationAll());
        if(apartments == null || apartments.isEmpty()){
            throw new ApplicationException("Empty apartment table",
                    "exception.apartment.noTable");
        }
        List<ProfileApartmentDto> profileApartmentDtos = apartments.stream()
                .peek(this::getApartmentDto)
                .map(this::getApartmentDto)
                .collect(Collectors.toList());
        return profileApartmentDtos;
    }

    @Override
    public ProfileApartmentDto postApartment(CreateApartmentDto apartment) {
        if(!apartmentRepository.create(apartmentConverter.asAppApartment(apartment))){
            throw new ApplicationException("Cant create apartment: " + apartment.getName(),
                    "exception.apartment.cantCreateApartment",
                    apartment.getName());
        }
        return apartmentConverter.asApartmentDto(apartment);
    }

    @Override
    public ProfileApartmentDto putApartment(UpdateApartmentDto apartment) {
        getApartment(apartment.getId());//check if exists
        Apartment apartmentToInsert = apartmentConverter.asAppApartment(apartment);
        if(!apartmentRepository.update(apartmentToInsert)){
            throw new ApplicationException("Cant update apartment: " + apartment.toString(),
                    "exception.apartment.cantUpdateApartment",
                    apartment.toString());
        }
        getApartmentDto(apartmentToInsert);
        return apartmentConverter.asApartmentDto(apartmentToInsert);
    }

    @Override
    public ProfileApartmentDto deleteApartment(Integer id) {
        ProfileApartmentDto apartmentDto = getApartment(id);
        if(!apartmentRepository.delete(apartmentConverter.asAppApartment(apartmentDto))){
            throw new ApplicationException("Cant delete apartment: " + id,
                    "exception.apartment.cantDeleteApartment",
                    id.toString());
        }
        return apartmentDto;
    }

    @Override
    public List<ProfileApartmentDto> getApartmentsByStatus(ApartmentStatus status) {
        List<Apartment> apartments = apartmentRepository.read(new ApartmentSpecificationByStatus(status));
        if(apartments == null || apartments.isEmpty()){
            throw new ApplicationException("No apartments with status: " + status.getName(),
                    "exception.apartment.noApartmentWithStatus",
                    status.getName());
        }
        List<ProfileApartmentDto> profileApartmentDtos = apartments.stream()
                .peek(this::getApartmentDto)
                .map(this::getApartmentDto)
                .collect(Collectors.toList());
        return profileApartmentDtos;
    }

    @Override
    public List<ProfileApartmentDto> getFreeApartments() {
        ApartmentStatus status = new ApartmentStatus("free");
        return getApartmentsByStatus(status);
    }

    @Override
    public List<ProfileApartmentDto> getFreeApartments(LocalDate arrival, LocalDate departure) {
        ApartmentStatus status = new ApartmentStatus("free");
        List<Apartment> apartments = apartmentRepository.read(new ApartmentSpecificationByDatesAndStatus(arrival, departure, status));
        if(apartments == null || apartments.isEmpty()){
            return Collections.emptyList();
        }
        List<ProfileApartmentDto> profileApartmentDtos = apartments.stream()
                .map(this::getApartmentDto)
                .collect(Collectors.toList());
        return profileApartmentDtos;
    }
}
