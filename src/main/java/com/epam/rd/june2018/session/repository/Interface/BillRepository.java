package com.epam.rd.june2018.session.repository.Interface;

import com.epam.rd.june2018.session.model.Bill;
import com.epam.rd.june2018.session.repository.specification.Interface.BillSpecification;

public interface BillRepository extends CrudRepository<Bill, BillSpecification> {
}
