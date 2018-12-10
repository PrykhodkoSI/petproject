package com.epam.rd.june2018.session.validator;

import com.epam.rd.june2018.session.web.dto.CreateBillDto;

public interface BillValidator {
    void validateNewBill(CreateBillDto createDto);
}
