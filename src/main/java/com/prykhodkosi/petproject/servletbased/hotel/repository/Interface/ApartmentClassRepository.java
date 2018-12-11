package com.prykhodkosi.petproject.servletbased.hotel.repository.Interface;

import com.prykhodkosi.petproject.servletbased.hotel.repository.specification.Interface.ApartmentClassSpecification;
import com.prykhodkosi.petproject.servletbased.hotel.model.ApartmentClass;

public interface ApartmentClassRepository extends CrudRepository<ApartmentClass, ApartmentClassSpecification> {
}
