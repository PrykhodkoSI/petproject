package com.epam.rd.june2018.session.service.Interface;

import com.epam.rd.june2018.session.web.dto.*;

import java.util.List;

public interface BillService {
    ProfileBillDto getBill(Integer id);
    List<ProfileBillDto> getBills();
    List<ProfileBillDto> getBillsByUser(ProfileUserDto user);
    List<ProfileBillDto> getBillsByUserNotPaid(ProfileUserDto user);
    List<ProfileBillDto> getBillsByOutdated(Integer daysOfOutdated);
    List<ProfileBillDto> getBillsByOutdated();

    ProfileBillDto postBill(CreateBillDto apartment);
    ProfileBillDto putBill(UpdateBillDto apartment);

    ProfileBillDto deleteBill(Integer id);

    List<ProfileBillDto> getBillsNotPaid();

    ProfileBillDto payBill(Integer id);

    ProfileBillDto getBillByBookingRequest(Integer bookingRequest);
}
