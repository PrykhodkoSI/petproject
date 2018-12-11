package com.prykhodkosi.petproject.servletbased.hotel.service.Impl;

import com.prykhodkosi.petproject.servletbased.hotel.converter.BillConverter;
import com.prykhodkosi.petproject.servletbased.hotel.converter.BookingRequestConverter;
import com.prykhodkosi.petproject.servletbased.hotel.converter.UserConverter;
import com.prykhodkosi.petproject.servletbased.hotel.exception.ApplicationException;
import com.prykhodkosi.petproject.servletbased.hotel.repository.Interface.BillRepository;
import com.prykhodkosi.petproject.servletbased.hotel.repository.Interface.BookingRequestRepository;
import com.prykhodkosi.petproject.servletbased.hotel.repository.Interface.UserRepository;
import com.prykhodkosi.petproject.servletbased.hotel.repository.RepositoryFabric;
import com.prykhodkosi.petproject.servletbased.hotel.repository.specification.impl.*;
import com.prykhodkosi.petproject.servletbased.hotel.service.Interface.BillService;
import com.prykhodkosi.petproject.servletbased.hotel.validator.BillValidator;
import com.prykhodkosi.petproject.servletbased.hotel.validator.BillValidatorImpl;
import com.prykhodkosi.petproject.servletbased.hotel.model.Bill;
import com.prykhodkosi.petproject.servletbased.hotel.model.BookingRequest;
import com.prykhodkosi.petproject.servletbased.hotel.model.User;
import com.prykhodkosi.petproject.servletbased.hotel.web.dto.*;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class BillServiceImpl implements BillService {

    private BillRepository billRepository = RepositoryFabric.getBillRepository();
    private UserRepository userRepository = RepositoryFabric.getUserRepository();
    private BookingRequestRepository bookingRequestRepository = RepositoryFabric.getBookingRequestRepository();
    private BillConverter billConverter = new BillConverter();
    private UserConverter userConverter = new UserConverter();
    private BookingRequestConverter requestConverter = new BookingRequestConverter();
    private BillValidator billValidator = new BillValidatorImpl();

    @Override
    public ProfileBillDto getBill(Integer id) {
        Bill billToRead = new Bill(id);
        List<Bill> bills = billRepository.read(new BillSpecificationById(billToRead));
        if(bills == null || bills.isEmpty()){
            throw new ApplicationException("No such Bill: " + id,
                    "exception.bill.noBill",
                    id.toString());
        }
        Bill bill = bills.iterator().next();
        return getBillDto(bill);
    }

    private ProfileBillDto getBillDto(Bill bill) {
        ProfileBillDto profileBillDto = billConverter.asBillDto(bill);
        ProfileUserDto user = userConverter.asUserDto(getUser(bill.getUser().getId()));
        ProfileBookingRequestDto bookingRequest = getBookingRequest(bill.getId());
        profileBillDto.setUser(user);
        profileBillDto.setBookingRequest(bookingRequest);
        return profileBillDto;
    }

    private ProfileBookingRequestDto getBookingRequest(Integer billId) {
        BookingRequest bookingRequestToRead = new BookingRequest(billId);
        List<BookingRequest> bookingRequestes = bookingRequestRepository
                .read(new BookingRequestSpecificationById(bookingRequestToRead));
        if(bookingRequestes == null || bookingRequestes.isEmpty()){
            throw new ApplicationException("No such BookingRequest: " + bookingRequestToRead.getId(),
                    "exception.bill.noBookingRequest",
                    bookingRequestToRead.getId().toString());
        }
        return requestConverter.asBookingRequestDto(bookingRequestes.iterator().next());
    }

    @SuppressWarnings("Duplicates")
    private User getUser(Integer billId) {
        User userToRead = new User(billId);
        List<User> useres = userRepository
                .read(new UserSpecificationById(userToRead));
        if(useres == null || useres.isEmpty()){
            throw new ApplicationException("No such User: " + userToRead.getId(),
                    "exception.bill.noUser",
                    userToRead.getId().toString());
        }
        return useres.iterator().next();
    }

    @Override
    public List<ProfileBillDto> getBills() {
        List<Bill> bills = billRepository.read(new BillSpecificationAll());
        if(bills == null || bills.isEmpty()){
            throw new ApplicationException("Empty bill table",
                    "exception.bill.noTable");
        }
        List<ProfileBillDto> profileBillDtos = bills.stream()
                .map(this::getBillDto)
                .collect(Collectors.toList());
        return profileBillDtos;
    }

    @Override
    public List<ProfileBillDto> getBillsByUser(ProfileUserDto user) {
        List<Bill> bills = billRepository.read(new BillSpecificationByUser(userConverter.asAppUser(user)));
        if(bills == null || bills.isEmpty()){
            throw new ApplicationException("No Bills for user: " + user.getEmail(),
                    "exception.bill.noBillForUser",
                    user.getEmail());
        }
        List<ProfileBillDto> profileBillDtos = bills.stream()
                .map(this::getBillDto)
                .collect(Collectors.toList());
        return profileBillDtos;
    }

    @Override
    public List<ProfileBillDto> getBillsByUserNotPaid(ProfileUserDto user) {
        List<Bill> bills = billRepository.read(new BillSpecificationByUserNotPaid(userConverter.asAppUser(user)));
        if(bills == null || bills.isEmpty()){
            return Collections.emptyList();
        }
        List<ProfileBillDto> profileBillDtos = bills.stream()
                .map(this::getBillDto)
                .collect(Collectors.toList());
        return profileBillDtos;
    }

    @Override
    public List<ProfileBillDto> getBillsByOutdated(Integer daysOfOutdated) {
        List<Bill> bills = billRepository.read(new BillSpecificationByOutdated(daysOfOutdated));
        if(bills == null || bills.isEmpty()){
            return Collections.emptyList();
        }
        List<ProfileBillDto> profileBillDtos = bills.stream()
                .map(this::getBillDto)
                .collect(Collectors.toList());
        return profileBillDtos;
    }

    @Override
    public List<ProfileBillDto> getBillsByOutdated() {
        return getBillsByOutdated(2);
    }

    @Override
    public ProfileBillDto postBill(CreateBillDto bill) {
        billValidator.validateNewBill(bill);
        if(!billRepository.create(billConverter.asAppBill(bill))){
            throw new ApplicationException("Cant create bill: " + bill.toString(),
                    "exception.bill.cantCreateBill",
                    bill.toString());
        }
        return getBillByBookingRequest(bill.getBookingRequest());
    }

    @Override
    public ProfileBillDto putBill(UpdateBillDto bill) {
        getBill(bill.getId());//check if exists
        return tryPutBill(bill);
    }

    private ProfileBillDto tryPutBill(UpdateBillDto bill) {
        if(!billRepository.update(billConverter.asAppBill(bill))){
            throw new ApplicationException("Cant update bill: " + bill.toString(),
                    "exception.bill.cantUpdateBill",
                    bill.toString());
        }
        return billConverter.asBillDto(bill);
    }

    @Override
    public ProfileBillDto deleteBill(Integer id) {
        ProfileBillDto billDto = getBill(id);
        if(!billRepository.delete(billConverter.asAppBill(billDto))){
            throw new ApplicationException("Cant delete bill: " + id,
                    "exception.bill.cantDeleteBill",
                    id.toString());
        }
        return billDto;
    }

    @Override
    public List<ProfileBillDto> getBillsNotPaid() {
        List<Bill> bills = billRepository.read(new BillSpecificationNotPaid());
        if(bills == null || bills.isEmpty()){
            return Collections.emptyList();
        }
        List<ProfileBillDto> profileBillDtos = bills.stream()
                .map(this::getBillDto)
                .collect(Collectors.toList());
        return profileBillDtos;
    }

    @Override
    public ProfileBillDto payBill(Integer id) {
        ProfileBillDto bill = getBill(id);
        if(bill.isPaid()){
            throw new ApplicationException("Bill is paid already: " + id,
                    "exception.bill.isPaid",
                    id.toString());
        }
        bill.setPaid(true);
        bill.setPaidDate(LocalDate.now());
        return tryPutBill(billConverter.asBillDto(bill));
    }

    @Override
    public ProfileBillDto getBillByBookingRequest(Integer bookingRequestId) {
        List<Bill> bills = billRepository.read(new BillSpecificationByBookingRequest(bookingRequestId));
        if(bills == null || bills.isEmpty()){
            throw new ApplicationException("No bill for Request: " + bookingRequestId,
                    "exception.bill.noBillForRequest",
                    bookingRequestId.toString());
        }
        Bill bill = bills.iterator().next();
        return getBillDto(bill);
    }
}
