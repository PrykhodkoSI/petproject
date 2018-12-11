package com.prykhodkosi.petproject.servletbased.hotel.repository.Interface;

import com.prykhodkosi.petproject.servletbased.hotel.repository.specification.Interface.BillSpecification;
import com.prykhodkosi.petproject.servletbased.hotel.model.Bill;

public interface BillRepository extends CrudRepository<Bill, BillSpecification> {
}
