package com.prykhodkosi.petproject.servletbased.hotel.repository.specification.impl;

import com.prykhodkosi.petproject.servletbased.hotel.repository.specification.Interface.BillSpecification;
import com.prykhodkosi.petproject.servletbased.hotel.exception.DatabaseException;
import com.prykhodkosi.petproject.servletbased.hotel.model.Bill;

public class BillSpecificationById implements BillSpecification {
    Bill bill;

    public BillSpecificationById(Bill bill) {
        this.bill = bill;
    }

    @Override
    public String toSqlClauses() {
        if(bill != null && bill.getId() > 0)
            return " WHERE bills.id=" + bill.getId();
        throw new DatabaseException("Bad specification");
    }
}
