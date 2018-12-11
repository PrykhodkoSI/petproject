package com.prykhodkosi.petproject.servletbased.hotel.service.Interface;

import com.prykhodkosi.petproject.servletbased.hotel.web.dto.CreateBillDto;
import com.prykhodkosi.petproject.servletbased.hotel.web.dto.ProfileBillDto;
import com.prykhodkosi.petproject.servletbased.hotel.web.dto.ProfileUserDto;
import com.prykhodkosi.petproject.servletbased.hotel.web.dto.UpdateBillDto;

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
