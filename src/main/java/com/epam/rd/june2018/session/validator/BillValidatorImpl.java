package com.epam.rd.june2018.session.validator;

import com.epam.rd.june2018.session.exception.ValidationException;
import com.epam.rd.june2018.session.web.dto.CreateBillDto;

import java.time.LocalDate;

public class BillValidatorImpl implements BillValidator {
    @Override
    public void validateNewBill(CreateBillDto createDto) {
        Integer bookingRequestId = createDto.getBookingRequest();
        Integer userId = createDto.getUser();
        Integer price = createDto.getPrice();
        LocalDate billedDate = createDto.getBilledDate();
        if (bookingRequestId == null
                || userId == null
                || price == null
                || billedDate == null
                || bookingRequestId < 1
                || userId < 1
                || price < 0) {
            throw new ValidationException("Invalid Bill: " + createDto.toString(),
                    "exception.validate.bill.newBill",
                    createDto.toString());
        }
    }
}
