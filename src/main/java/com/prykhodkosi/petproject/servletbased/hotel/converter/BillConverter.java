package com.prykhodkosi.petproject.servletbased.hotel.converter;

import com.prykhodkosi.petproject.servletbased.hotel.model.Bill;
import com.prykhodkosi.petproject.servletbased.hotel.model.BookingRequest;
import com.prykhodkosi.petproject.servletbased.hotel.model.User;
import com.prykhodkosi.petproject.servletbased.hotel.web.dto.*;

public class BillConverter {

    private UserConverter userConverter = new UserConverter();
    private BookingRequestConverter requestConverter = new BookingRequestConverter();

    public ProfileBillDto asBillDto(Bill bill) {
        if(bill == null) return null;
        ProfileBillDto dto = new ProfileBillDto();
        dto.setId(bill.getId());
        dto.setBilledDate(bill.getBilledDate());
        dto.setPaidDate(bill.getPaidDate());
        dto.setPaid(bill.isPaid());
        dto.setPrice(bill.getPrice());
        dto.setBookingRequest(requestConverter.asBookingRequestDto(bill.getBookingRequest()));
        dto.setUser(userConverter.asUserDto(bill.getUser()));
        return dto;
    }

    public ProfileBillDto asBillDto(CreateBillDto bill) {
        if(bill == null) return null;
        ProfileBillDto dto = new ProfileBillDto();
        dto.setBilledDate(bill.getBilledDate());
        dto.setPrice(bill.getPrice());
        ProfileBookingRequestDto request = new ProfileBookingRequestDto(bill.getBookingRequest());
        dto.setBookingRequest(request);
        ProfileUserDto user = new ProfileUserDto(bill.getUser());
        dto.setUser(user);
        return dto;
    }

    public Bill asAppBill(ProfileBillDto billDto) {
        if(billDto == null) return null;
        Bill bill = new Bill(
                billDto.getId(),
                billDto.getPrice(),
                billDto.isPaid(),
                billDto.getBilledDate(),
                billDto.getPaidDate(),
                userConverter.asAppUser(billDto.getUser()),
                requestConverter.asAppBookingRequest(billDto.getBookingRequest())
        );
        return bill;
    }
    public Bill asAppBill(CreateBillDto billDto) {
        if(billDto == null) return null;
        BookingRequest request = new BookingRequest(billDto.getBookingRequest());
        User user = new User(billDto.getUser());
        Bill bill = new Bill(
                billDto.getPrice(),
                false,
                billDto.getBilledDate(),
                user,
                request
        );
        return bill;
    }

    public Bill asAppBill(UpdateBillDto billDto) {
        if(billDto == null) return null;
        BookingRequest request = new BookingRequest(billDto.getBookingRequest());
        User user = new User(billDto.getUser());
        Bill bill = new Bill(
                billDto.getId(),
                billDto.getPrice(),
                billDto.isPaid(),
                billDto.getBilledDate(),
                billDto.getPaidDate(),
                user,
                request
        );
        return bill;
    }

    public ProfileBillDto asBillDto(UpdateBillDto bill) {
        if(bill == null) return null;
        BookingRequest request = new BookingRequest(bill.getBookingRequest());
        User user = new User(bill.getUser());
        ProfileBillDto dto = new ProfileBillDto();
        dto.setId(bill.getId());
        dto.setBilledDate(bill.getBilledDate());
        dto.setPaidDate(bill.getPaidDate());
        dto.setPaid(bill.isPaid());
        dto.setPrice(bill.getPrice());
        dto.setBookingRequest(requestConverter.asBookingRequestDto(request));
        dto.setUser(userConverter.asUserDto(user));
        return dto;
    }

    public UpdateBillDto asBillDto(ProfileBillDto bill) {
        if(bill == null) return null;
        UpdateBillDto dto = new UpdateBillDto();
        dto.setId(bill.getId());
        dto.setBilledDate(bill.getBilledDate());
        dto.setBookingRequest(bill.getBookingRequest().getId());
        dto.setPaid(bill.isPaid());
        dto.setPaidDate(bill.getPaidDate());
        dto.setPrice(bill.getPrice());
        dto.setUser(bill.getUser().getId());
        return dto;
    }
}
