package com.prykhodkosi.petproject.servletbased.hotel.service;

import com.prykhodkosi.petproject.servletbased.hotel.service.Impl.*;
import com.prykhodkosi.petproject.servletbased.hotel.service.Interface.*;

public class ServiceFabric {
    private static UserService userService;
    private static RoleService roleService;
    private static BookingRequestService bookingRequestService;
    private static BillService billService;
    private static ApartmentService apartmentService;
    private static ApartmentClassService apartmentClassService;
    private static ApartmentStatusService apartmentStatusService;

    public static UserService getUserService() {
        if(userService == null){
            userService = new UserServiceImpl();
        }
        return userService;
    }

    public static RoleService getRoleService() {
        if(roleService == null){
            roleService = new RoleServiceImpl();
        }
        return roleService;
    }

    public static BookingRequestService getBookingRequestService() {
        if(bookingRequestService == null){
            bookingRequestService = new BookingRequestServiceImpl();
        }
        return bookingRequestService;
    }

    public static BillService getBillService() {
        if(billService == null){
            billService = new BillServiceImpl();
        }
        return billService;
    }

    public static ApartmentService getApartmentService() {
        if(apartmentService == null){
            apartmentService = new ApartmentServiceImpl();
        }
        return apartmentService;
    }

    public static ApartmentClassService getApartmentClassService() {
        if(apartmentClassService == null){
            apartmentClassService = new ApartmentClassServiceImpl();
        }
        return apartmentClassService;
    }

    public static ApartmentStatusService getApartmentStatusService() {
        if(apartmentStatusService == null){
            apartmentStatusService = new ApartmentStatusServiceImpl();
        }
        return apartmentStatusService;
    }

    public static OutdatedBillManagerService getOutdateBillManagerService(){
            return OutdatedBillManagerServiceImpl.getInstance();
    }
}
