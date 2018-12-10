package com.epam.rd.june2018.session.service.Impl;

import com.epam.rd.june2018.session.exception.ApplicationException;
import com.epam.rd.june2018.session.model.ApartmentClass;
import com.epam.rd.june2018.session.repository.Interface.ApartmentClassRepository;
import com.epam.rd.june2018.session.repository.RepositoryFabric;
import com.epam.rd.june2018.session.repository.specification.impl.ApartmentClassSpecificationAll;
import com.epam.rd.june2018.session.repository.specification.impl.ApartmentClassSpecificationById;
import com.epam.rd.june2018.session.repository.specification.impl.ApartmentClassSpecificationByName;
import com.epam.rd.june2018.session.service.Interface.ApartmentClassService;

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
