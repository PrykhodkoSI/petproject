package com.prykhodkosi.petproject.servletbased.hotel.repository;

import com.prykhodkosi.petproject.servletbased.hotel.repository.Implementation.*;
import com.prykhodkosi.petproject.servletbased.hotel.repository.Interface.*;

public class RepositoryFabric {

    private static UserRepository userRepository = new JdbcUserRepository();
    private static RoleRepository roleRepository = new JdbcRoleRepository();
    private static BookingRequestRepository bookingRequestRepository = new JdbcBookingRequestRepository();
    private static BillRepository billRepository = new JdbcBillRepository();
    private static ApartmentRepository apartmentRepository = new JdbcApartmentRepository();
    private static ApartmentClassRepository apartmentClassRepository = new JdbcApartmentClassRepository();
    private static ApartmentStatusRepository apartmentStatusRepository = new JdbcApartmentStatusRepository();

    public static UserRepository getUserRepository() {
        return userRepository;
    }

    public static RoleRepository getRoleRepository() {
        return roleRepository;
    }

    public static BookingRequestRepository getBookingRequestRepository() {
        return bookingRequestRepository;
    }

    public static BillRepository getBillRepository() {
        return billRepository;
    }

    public static ApartmentRepository getApartmentRepository() {
        return apartmentRepository;
    }

    public static ApartmentClassRepository getApartmentClassRepository() {
        return apartmentClassRepository;
    }

    public static ApartmentStatusRepository getApartmentStatusRepository() {
        return apartmentStatusRepository;
    }
}
