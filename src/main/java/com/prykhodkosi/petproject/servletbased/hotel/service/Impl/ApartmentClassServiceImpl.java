package com.prykhodkosi.petproject.servletbased.hotel.service.Impl;

import com.prykhodkosi.petproject.servletbased.hotel.exception.ApplicationException;
import com.prykhodkosi.petproject.servletbased.hotel.repository.Interface.ApartmentClassRepository;
import com.prykhodkosi.petproject.servletbased.hotel.repository.RepositoryFabric;
import com.prykhodkosi.petproject.servletbased.hotel.repository.specification.impl.ApartmentClassSpecificationAll;
import com.prykhodkosi.petproject.servletbased.hotel.repository.specification.impl.ApartmentClassSpecificationById;
import com.prykhodkosi.petproject.servletbased.hotel.repository.specification.impl.ApartmentClassSpecificationByName;
import com.prykhodkosi.petproject.servletbased.hotel.service.Interface.ApartmentClassService;
import com.prykhodkosi.petproject.servletbased.hotel.model.ApartmentClass;

import java.util.List;

public class ApartmentClassServiceImpl implements ApartmentClassService {

    private ApartmentClassRepository apartmentClassRepository = RepositoryFabric.getApartmentClassRepository();

    @Override
    public ApartmentClass getApartmentClass(Integer id) {
        ApartmentClass apartmentClass = new ApartmentClass(id);
        List<ApartmentClass> apartmentClasses = apartmentClassRepository.read(new ApartmentClassSpecificationById(apartmentClass));
        if(apartmentClasses == null || apartmentClasses.isEmpty()){
            throw new ApplicationException("No such ApartmentClass: " + id,
                    "exception.apartmentClass.noApartmentClass",
                    id.toString());
        }
        return apartmentClasses.iterator().next();
    }

    @Override
    public ApartmentClass getApartmentClassByName(String apartmentClass) {
        ApartmentClass criteriaApartmentClass = new ApartmentClass(apartmentClass);
        List<ApartmentClass> apartmentClasses = apartmentClassRepository.read(new ApartmentClassSpecificationByName(criteriaApartmentClass));
        if(apartmentClasses == null || apartmentClasses.isEmpty()){
            throw new ApplicationException("No such ApartmentClass: " + apartmentClass,
                    "exception.apartmentClass.noApartmentClass",
                    apartmentClass);
        }
        return apartmentClasses.iterator().next();
    }

    @Override
    public List<ApartmentClass> getApartmentClasses() {
        List<ApartmentClass> apartmentClasss = apartmentClassRepository.read(new ApartmentClassSpecificationAll());
        if(apartmentClasss == null || apartmentClasss.isEmpty()){
            throw new ApplicationException("Empty ApartmentClass table", "exception.apartmentClass.noTable");
        }
        return apartmentClasss;
    }

    @Override
    public ApartmentClass postApartmentClass(ApartmentClass apartmentClass) {
        List<ApartmentClass> apartmentClasses = apartmentClassRepository.read(new ApartmentClassSpecificationByName(apartmentClass));
        if(apartmentClasses != null && !apartmentClasses.isEmpty()){
            throw new ApplicationException("ApartmentClass already exists: " + apartmentClass.getName(),
                    "exception.apartmentClass.existsApartmentClass",
                    apartmentClass.getName());
        }
        if(!apartmentClassRepository.create(apartmentClass)){
            throw new ApplicationException("Cant create ApartmentClass: " + apartmentClass.getName(),
                    "exception.apartmentClass.cantCreateApartmentClass",
                    apartmentClass.getName());
        }
        return apartmentClass;
    }

    @Override
    public ApartmentClass putApartmentClass(ApartmentClass apartmentClass) {
        getApartmentClass(apartmentClass.getId());//check if exists
        if(!apartmentClassRepository.update(apartmentClass)){
            throw new ApplicationException("Cant update apartmentClass: " + apartmentClass.toString(),
                    "exception.apartmentClass.cantUpdateApartmentClass",
                    apartmentClass.toString());
        }
        return apartmentClass;
    }

    @Override
    public ApartmentClass deleteApartmentClass(Integer id) {
        ApartmentClass apartmentClass = getApartmentClass(id);
        if(!apartmentClassRepository.delete(apartmentClass)){
            throw new ApplicationException("Cant delete apartmentClass: " + id,
                    "exception.apartmentClass.cantDeleteApartmentClass",
                    id.toString());
        }
        return apartmentClass;
    }
}
