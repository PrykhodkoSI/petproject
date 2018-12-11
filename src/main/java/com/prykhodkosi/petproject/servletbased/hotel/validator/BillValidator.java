package com.prykhodkosi.petproject.servletbased.hotel.validator;

import com.prykhodkosi.petproject.servletbased.hotel.web.dto.CreateBillDto;

public interface BillValidator {
    void validateNewBill(CreateBillDto createDto);
}
