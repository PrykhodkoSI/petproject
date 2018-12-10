package com.epam.rd.june2018.session.model;


import java.time.LocalDate;
import java.util.Objects;

public class BookingRequest {
    private Integer id;
    private Integer capacity;
    private ApartmentClass apartmentClass;
    private LocalDate arrivalDate;
    private LocalDate departureDate;
    private User user;
    private Apartment apartment;
    private Boolean accepted;

    public BookingRequest() {
        this.id = -1;
        this.user = new User();
        this.apartmentClass = new ApartmentClass((Integer)null);
        this.apartment = new Apartment(null);
    }

    public BookingRequest(Integer id) {
        this.id = id;
        this.user = new User();
        this.apartmentClass = new ApartmentClass((Integer)null);
        this.apartment = new Apartment(null);
    }

    public BookingRequest(Integer id, Integer capacity, Integer apartmentClassId, LocalDate arrivalDate, LocalDate departureDate, Integer userId, Integer apartmentId, Boolean accepted) {
        this.id = id;
        this.capacity = capacity;
        this.arrivalDate = arrivalDate;
        this.departureDate = departureDate;

        this.apartmentClass = new ApartmentClass(apartmentClassId);
        this.user = new User(userId);
        this.apartment = new Apartment(apartmentId);
        this.accepted = accepted;
    }

    public BookingRequest(Integer id, Integer capacity, ApartmentClass apartmentClass, LocalDate arrivalDate, LocalDate departureDate, User user, Apartment apartment, Boolean accepted) {
        Objects.requireNonNull(user);
        this.id = id;
        this.capacity = capacity;
        this.arrivalDate = arrivalDate;
        this.departureDate = departureDate;
        this.apartmentClass = apartmentClass;
        this.user = user;
        this.apartment = apartment==null ? new Apartment(null) : apartment;
        this.accepted = accepted;
    }

    public BookingRequest(Integer capacity, ApartmentClass apartmentClass, LocalDate arrivalDate, LocalDate departureDate, User user, boolean accepted) {
        Objects.requireNonNull(user);
        this.capacity = capacity;
        this.arrivalDate = arrivalDate;
        this.departureDate = departureDate;
        this.apartmentClass = apartmentClass==null ? new ApartmentClass() : apartmentClass;
        this.user = user;
        this.apartment = new Apartment(null);
        this.accepted = accepted;
    }

    public Integer getId() {
        return id;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public ApartmentClass getApartmentClass() {
        return apartmentClass;
    }

    public LocalDate getArrivalDate() {
        return arrivalDate;
    }

    public LocalDate getDepartureDate() {
        return departureDate;
    }

    public User getUser() {
        return user;
    }

    public Apartment getApartment() {
        return apartment;
    }

    public Boolean isAccepted() {
        return accepted;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookingRequest that = (BookingRequest) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(capacity, that.capacity) &&
                Objects.equals(apartmentClass, that.apartmentClass) &&
                Objects.equals(arrivalDate, that.arrivalDate) &&
                Objects.equals(departureDate, that.departureDate) &&
                Objects.equals(user, that.user) &&
                Objects.equals(apartment, that.apartment) &&
                Objects.equals(accepted, that.accepted);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, capacity, apartmentClass, arrivalDate, departureDate, user, apartment, accepted);
    }

    @Override
    public String toString() {
        return "BookingRequest{" +
                "id=" + id +
                ", capacity=" + capacity +
                ", apartmentClass=" + apartmentClass +
                ", arrivalDate=" + arrivalDate +
                ", departureDate=" + departureDate +
                ", user=" + user +
                ", apartment=" + apartment +
                ", accepted=" + accepted +
                '}';
    }
}
