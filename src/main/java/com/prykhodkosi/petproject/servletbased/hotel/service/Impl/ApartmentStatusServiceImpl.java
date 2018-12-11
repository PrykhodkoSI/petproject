package com.prykhodkosi.petproject.servletbased.hotel.service.Impl;

import com.prykhodkosi.petproject.servletbased.hotel.exception.ApplicationException;
import com.prykhodkosi.petproject.servletbased.hotel.repository.Interface.ApartmentStatusRepository;
import com.prykhodkosi.petproject.servletbased.hotel.repository.RepositoryFabric;
import com.prykhodkosi.petproject.servletbased.hotel.repository.specification.impl.ApartmentStatusSpecificationAll;
import com.prykhodkosi.petproject.servletbased.hotel.repository.specification.impl.ApartmentStatusSpecificationById;
import com.prykhodkosi.petproject.servletbased.hotel.repository.specification.impl.ApartmentStatusSpecificationByName;
import com.prykhodkosi.petproject.servletbased.hotel.service.Interface.ApartmentStatusService;
import com.prykhodkosi.petproject.servletbased.hotel.model.ApartmentStatus;

import java.util.List;

public class ApartmentStatusServiceImpl implements ApartmentStatusService {
    private ApartmentStatusRepository apartmentStatusRepository = RepositoryFabric.getApartmentStatusRepository();

    @Override
    public ApartmentStatus getApartmentStatus(Integer id) {
        ApartmentStatus apartmentStatus = new ApartmentStatus(id);
        List<ApartmentStatus> apartmentStatuss = apartmentStatusRepository.read(new ApartmentStatusSpecificationById(apartmentStatus));
        if(apartmentStatuss == null || apartmentStatuss.isEmpty()){
            throw new ApplicationException("No such ApartmentStatus: " + id,
                    "exception.apartmentStatus.noApartmentStatus",
                    id.toString());
        }
        return apartmentStatuss.iterator().next();
    }

    @Override
    public ApartmentStatus getApartmentStatusByName(String apartmentStatus) {
        ApartmentStatus criteriaApartmentStatus = new ApartmentStatus(apartmentStatus);
        List<ApartmentStatus> apartmentStatuss = apartmentStatusRepository.read(new ApartmentStatusSpecificationByName(criteriaApartmentStatus));
        if(apartmentStatuss == null || apartmentStatuss.isEmpty()){
            throw new ApplicationException("No such ApartmentStatus: " + apartmentStatus,
                    "exception.apartmentStatus.noApartmentStatus",
                    apartmentStatus);
        }
        return apartmentStatuss.iterator().next();
    }

    @Override
    public List<ApartmentStatus> getApartmentStatuses() {
        List<ApartmentStatus> apartmentStatuss = apartmentStatusRepository.read(new ApartmentStatusSpecificationAll());
        if(apartmentStatuss == null || apartmentStatuss.isEmpty()){
            throw new ApplicationException("Empty ApartmentStatus table",
                    "exception.apartmentStatus.noTable");
        }
        return apartmentStatuss;
    }

    @Override
    public ApartmentStatus postApartmentStatus(ApartmentStatus apartmentStatus) {
        List<ApartmentStatus> apartmentStatuss = apartmentStatusRepository.read(new ApartmentStatusSpecificationByName(apartmentStatus));
        if(apartmentStatuss != null && !apartmentStatuss.isEmpty()){
            throw new ApplicationException("ApartmentStatus already exists: " + apartmentStatus.getName(),
                    "exception.apartmentStatus.existsApartmentStatus",
                    apartmentStatus.getName());
        }
        if(!apartmentStatusRepository.create(apartmentStatus)){
            throw new ApplicationException("Cant create ApartmentStatus: " + apartmentStatus.getName(),
                    "exception.apartmentStatus.cantCreateApartmentStatus",
                    apartmentStatus.getName());
        }
        return apartmentStatus;
    }

    @Override
    public ApartmentStatus putApartmentStatus(ApartmentStatus apartmentStatus) {
        getApartmentStatus(apartmentStatus.getId());//check if exists
        if(!apartmentStatusRepository.update(apartmentStatus)){
            throw new ApplicationException("Cant update apartmentStatus: " + apartmentStatus.toString(),
                    "exception.apartmentStatus.cantUpdateApartmentStatus",
                    apartmentStatus.toString());
        }
        return apartmentStatus;
    }

    @Override
    public ApartmentStatus deleteApartmentStatus(Integer id) {
        ApartmentStatus apartmentStatus = getApartmentStatus(id);
        if(!apartmentStatusRepository.delete(apartmentStatus)){
            throw new ApplicationException("Cant delete apartmentStatus: " + id,
                    "exception.apartmentStatus.cantDeleteApartmentStatus",
                    id.toString());
        }
        return apartmentStatus;
    }
}
