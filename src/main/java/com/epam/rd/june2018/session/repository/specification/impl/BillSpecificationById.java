package com.epam.rd.june2018.session.repository.specification.impl;

import com.epam.rd.june2018.session.exception.DatabaseException;
import com.epam.rd.june2018.session.model.Apartment;
import com.epam.rd.june2018.session.model.Bill;
import com.epam.rd.june2018.session.repository.specification.Interface.BillSpecification;

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
