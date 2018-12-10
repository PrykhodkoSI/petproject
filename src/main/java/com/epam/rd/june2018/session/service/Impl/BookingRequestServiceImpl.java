package com.epam.rd.june2018.session.service.Impl;

import com.epam.rd.june2018.session.converter.ApartmentConverter;
import com.epam.rd.june2018.session.converter.BookingRequestConverter;
import com.epam.rd.june2018.session.converter.UserConverter;
import com.epam.rd.june2018.session.exception.ApplicationException;
import com.epam.rd.june2018.session.model.*;
import com.epam.rd.june2018.session.repository.Interface.ApartmentClassRepository;
import com.epam.rd.june2018.session.repository.Interface.ApartmentRepository;
import com.epam.rd.june2018.session.repository.Interface.BookingRequestRepository;
import com.epam.rd.june2018.session.repository.Interface.UserRepository;
import com.epam.rd.june2018.session.repository.RepositoryFabric;
import com.epam.rd.june2018.session.repository.specification.impl.*;
import com.epam.rd.june2018.session.service.Interface.ApartmentClassService;
import com.epam.rd.june2018.session.service.Interface.ApartmentStatusService;
import com.epam.rd.june2018.session.service.Interface.BillService;
import com.epam.rd.june2018.session.service.Interface.BookingRequestService;
import com.epam.rd.june2018.session.service.ServiceFabric;
import com.epam.rd.june2018.session.validator.BookingRequestValidator;
import com.epam.rd.june2018.session.validator.BookingRequestValidatorImpl;
import com.epam.rd.june2018.session.web.dto.*;

import java.util.*;
import java.util.stream.Collectors;

public class BookingRequestServiceImpl implements BookingRequestService {
    private BookingRequestRepository bookingRequestRepository = RepositoryFabric.getBookingRequestRepository();
    private ApartmentRepository apartmentRepository = RepositoryFabric.getApartmentRepository();
    private ApartmentClassRepository apartmentClassRepository = RepositoryFabric.getApartmentClassRepository();
    private UserRepository userRepository = RepositoryFabric.getUserRepository();
    private BillService billService = ServiceFabric.getBillService();
    private BookingRequestConverter bookingRequestConverter = new BookingRequestConverter();
    private UserConverter userConverter = new UserConverter();
    private ApartmentConverter apartmentConverter = new ApartmentConverter();
    private BookingRequestValidator requestValidator = new BookingRequestValidatorImpl();
    private ApartmentClassService apartmentClassService = ServiceFabric.getApartmentClassService();
    private ApartmentStatusService apartmentStatusService = ServiceFabric.getApartmentStatusService();

    @Override
    public ProfileBookingRequestDto getBookingRequest(Integer id) {
        BookingRequest bookingRequestToRead = new BookingRequest(id);
        List<BookingRequest> bookingRequests = bookingRequestRepository.read(new BookingRequestSpecificationById(bookingRequestToRead));
        if(bookingRequests == null || bookingRequests.isEmpty()){
            throw new ApplicationException("No such BookingRequest: " + id,
                    "exception.request.noRequest",
                    id.toString());
        }
        BookingRequest bookingRequest = bookingRequests.iterator().next();
        return getProfileBookingRequestDto(bookingRequest);
    }

    @Override
    public ProfileBookingRequestDto getBookingRequestWithApartment(Integer id) {
        ProfileBookingRequestDto bookingRequest = getBookingRequest(id);
        ProfileApartmentDto apartment = bookingRequest.getApartment();
        ApartmentClass apartmentClass = apartmentClassService.getApartmentClass(apartment.getApartmentClassId());
        ApartmentStatus apartmentStatus = apartmentStatusService.getApartmentStatus(apartment.getApartmentStatusId());
        apartment.setApartmentClassId(apartmentClass.getId());
        apartment.setApartmentClassName(apartmentClass.getName());
        apartment.setApartmentStatusId(apartmentStatus.getId());
        apartment.setApartmentStatusName(apartmentStatus.getName());
        return bookingRequest;
    }

    private ProfileBookingRequestDto getProfileBookingRequestDto(BookingRequest bookingRequest) {
        ProfileBookingRequestDto requestDto = bookingRequestConverter.asBookingRequestDto(bookingRequest);
        ProfileUserDto user = getUserDto(bookingRequest);
        ApartmentClass apartmentClass = getApartmentClass(bookingRequest);
        ProfileApartmentDto apartment = apartmentConverter.asApartmentDto(getApartment(bookingRequest));
        requestDto.setUser(user);
        requestDto.setApartmentClassId(apartmentClass.getId());
        requestDto.setApartmentClassName(apartmentClass.getName());
        requestDto.setApartment(apartment);
        return requestDto;
    }

    private ApartmentClass getApartmentClass(BookingRequest bookingRequest) {
        ApartmentClass apartmentClassToRead = new ApartmentClass(bookingRequest.getApartmentClass().getId());
        List<ApartmentClass> apartmentsClasses = apartmentClassRepository
                .read(new ApartmentClassSpecificationById(apartmentClassToRead));
        if(apartmentsClasses != null && !apartmentsClasses.isEmpty()){
            return apartmentsClasses.iterator().next();
        }
        return bookingRequest.getApartmentClass();
    }

    private Apartment getApartment(BookingRequest bookingRequest) {
        Integer apartmentId = bookingRequest.getApartment().getId();
        if(apartmentId != null && apartmentId > 0) {
            Apartment apartmentToRead = new Apartment(apartmentId);
            List<Apartment> apartments = apartmentRepository
                    .read(new ApartmentSpecificationById(apartmentToRead));
            if (apartments != null && !apartments.isEmpty()) {
                return apartments.iterator().next();
            }
        }
        return bookingRequest.getApartment();
    }

    @SuppressWarnings("Duplicates")
    private ProfileUserDto getUserDto(BookingRequest bookingRequest) {
        return userConverter.asUserDto(getUser(bookingRequest.getUser().getId()));
    }

    private User getUser(Integer userId) {
        User userToRead = new User(userId);
        List<User> users = userRepository
                .read(new UserSpecificationById(userToRead));
        if(users == null || users.isEmpty()){
            throw new ApplicationException("No such User: " + userToRead.getId(),
                    "exception.request.noUser",
                    userToRead.getId().toString());
        }
        return users.iterator().next();
    }

    @Override
    public List<ProfileBookingRequestDto> getBookingRequests() {
        List<BookingRequest> bookingRequests = bookingRequestRepository.read(new BookingRequestSpecificationAll());
        if(bookingRequests == null || bookingRequests.isEmpty()){
            throw new ApplicationException("Empty bookingRequest table",
                    "exception.request.noTable");
        }
        List<ProfileBookingRequestDto> profileBookingRequestDtos = bookingRequests.stream()
                .map(this::getProfileBookingRequestDto)
                .collect(Collectors.toList());
        return profileBookingRequestDtos;
    }

    @Override
    public List<ProfileBookingRequestDto> getBookingRequestsByUser(ProfileUserDto user) {
        List<BookingRequest> bookingRequests = bookingRequestRepository.read(new BookingRequestSpecificationByUser(userConverter.asAppUser(user)));
        if(bookingRequests == null || bookingRequests.isEmpty()){
            throw new ApplicationException("No BookingRequests for user: " + user.getEmail(),
                    "exception.request.noRequestForUser",
                    user.getEmail());
        }
        List<ProfileBookingRequestDto> profileBookingRequestDtos = bookingRequests.stream()
                .map(this::getProfileBookingRequestDto)
                .collect(Collectors.toList());
        return profileBookingRequestDtos;
    }

    @Override
    public List<ProfileBookingRequestDto> getBookingRequestsByUserNotAccepted(ProfileUserDto user) {
        List<BookingRequest> bookingRequests = bookingRequestRepository.read(
                new BookingRequestSpecificationByUserNotAccepted(userConverter.asAppUser(user)));
        if(bookingRequests == null || bookingRequests.isEmpty()){
            return Collections.emptyList();
        }
        List<ProfileBookingRequestDto> profileBookingRequestDtos = bookingRequests.stream()
                .map(this::getProfileBookingRequestDto)
                .collect(Collectors.toList());
        return profileBookingRequestDtos;
    }

    @Override
    public List<ProfileBookingRequestDto> getBookingRequestNotAccepted() {
        List<BookingRequest> bookingRequests = bookingRequestRepository.read(new BookingRequestSpecificationNotAccepted());
        if(bookingRequests == null || bookingRequests.isEmpty()){
            return Collections.emptyList();
        }
        List<ProfileBookingRequestDto> profileBookingRequestDtos = bookingRequests.stream()
                .map(this::getProfileBookingRequestDto)
                .collect(Collectors.toList());
        return profileBookingRequestDtos;
    }

    @Override
    public ProfileBookingRequestDto postBookingRequest(CreateBookingRequestDto bookingRequest) {
        requestValidator.validateNewRequest(bookingRequest);
        if(!bookingRequestRepository.create(bookingRequestConverter.asAppBookingRequest(bookingRequest))){
            throw new ApplicationException("Cant create BookingRequest: " + bookingRequest.toString(),
                    "exception.request.cantCreateRequest",
                    bookingRequest.toString());
        }
        return bookingRequestConverter.asBookingRequestDto(bookingRequest);
    }

    @Override
    public ProfileBookingRequestDto setApartment(Integer requestId, Integer apartmentId) {
        ProfileBookingRequestDto bookingRequest = getBookingRequest(requestId);
        ProfileApartmentDto apartment = new ProfileApartmentDto(apartmentId);
        bookingRequest.setApartment(apartment);
        putBookingRequest(bookingRequest);
        return getBookingRequest(requestId);
    }

    @Override
    public ProfileBookingRequestDto accept(Integer userId, Integer requestId) {
        getUser(userId); //if exists
        ProfileBookingRequestDto bookingRequest = getBookingRequest(requestId);
        Integer apartmentId = bookingRequest.getApartment().getId();
        if(bookingRequest.isAccepted() || apartmentId == null || apartmentId < 1){
            throw new ApplicationException("BookingRequest cant be accepted: " + bookingRequest.toString(),
                    "exception.request.isAccepted",
                    bookingRequest.toString());
        }
        bookingRequest.setAccepted(true);
        ProfileBookingRequestDto requestDto = putBookingRequest(bookingRequest);
        CreateBillDto bill = bookingRequestConverter.asCreateBillDto(requestDto);
        billService.postBill(bill);
        return getBookingRequest(requestId);
    }

    private ProfileBookingRequestDto putBookingRequest(ProfileBookingRequestDto bookingRequest) {
        if(!bookingRequestRepository.update(bookingRequestConverter.asAppBookingRequest(bookingRequest))){
            throw new ApplicationException("Cant update bookingRequest: " + bookingRequest.toString(),
                    "exception.request.cantUpdateRequest",
                    bookingRequest.toString());
        }
        return bookingRequest;
    }

    @Override
    public ProfileBookingRequestDto deleteBookingRequest(Integer id) {
        ProfileBookingRequestDto bookingRequestDto = getBookingRequest(id);
        if(!bookingRequestRepository.delete(bookingRequestConverter.asAppBookingRequest(bookingRequestDto))){
            throw new ApplicationException("Cant delete bookingRequest: " + id,
                    "exception.request.cantDeleteRequest",
                    id.toString());
        }
        return bookingRequestDto;
    }

    @Override
    public ProfileBookingRequestDto deleteUsersBookingRequest(Integer userId, Integer requestId) {
        ProfileBookingRequestDto bookingRequestDto = getBookingRequest(requestId);
        if(!Objects.equals(bookingRequestDto.getUser().getId(), userId)){
            throw new ApplicationException("Unauthorized delete of bookingRequest: " + requestId,
                    "exception.request.cantDeleteUsersRequest",
                    requestId.toString());
        }
        if(!bookingRequestRepository.delete(bookingRequestConverter.asAppBookingRequest(bookingRequestDto))){
            throw new ApplicationException("Cant delete bookingRequest: " + requestId,
                    "exception.request.cantDeleteRequest",
                    requestId.toString());
        }
        return bookingRequestDto;
    }

    @Override
    public ProfileBookingRequestDto getUsersBookingRequest(Integer userId, Integer requestId) {
        BookingRequest bookingRequestToRead = new BookingRequest(requestId);
        List<BookingRequest> bookingRequests = bookingRequestRepository.read(new BookingRequestSpecificationById(bookingRequestToRead));
        if(bookingRequests == null || bookingRequests.isEmpty()){
            throw new ApplicationException("No such BookingRequest: " + requestId,
                    "exception.request.noRequest",
                    requestId.toString());
        }
        BookingRequest bookingRequest = bookingRequests.iterator().next();
        if(!Objects.equals(bookingRequest.getUser().getId(), userId)){
            throw new ApplicationException("Unauthorized to BookingRequest by user: " + userId,
                    "exception.request.cantViewUsersRequest",
                    userId.toString());
        }
        return getProfileBookingRequestDto(bookingRequest);
    }
}
