package com.prykhodkosi.petproject.servletbased.hotel.repository.Interface;

import com.prykhodkosi.petproject.servletbased.hotel.repository.specification.Interface.ApartmentStatusSpecification;
import com.prykhodkosi.petproject.servletbased.hotel.model.ApartmentStatus;

public interface ApartmentStatusRepository extends CrudRepository<ApartmentStatus, ApartmentStatusSpecification> {
}
