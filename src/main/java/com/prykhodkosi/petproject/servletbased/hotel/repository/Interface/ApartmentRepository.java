package com.prykhodkosi.petproject.servletbased.hotel.repository.Interface;

import com.prykhodkosi.petproject.servletbased.hotel.repository.specification.Interface.ApartmentSpecification;
import com.prykhodkosi.petproject.servletbased.hotel.model.Apartment;

public interface ApartmentRepository extends CrudRepository<Apartment, ApartmentSpecification> {
}
