package com.epam.rd.june2018.session.web.dto;


import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class ProfileBookingRequestDto implements Serializable {

    private static final long serialVersionUID = -2723831113787938111L;

    private Integer id;
    private Integer capacity;
    private Integer apartmentClassId;
    private String apartmentClassName;
    private LocalDate arrivalDate;
    private LocalDate departureDate;
    private ProfileUserDto user;
    private ProfileApartmentDto apartment;
    private Boolean accepted;

    public ProfileBookingRequestDto() {
        this.id = -1;
    }

    public ProfileBookingRequestDto(Integer id, Integer capacity, Integer apartmentClassId, LocalDate arrivalDate, LocalDate departureDate, Integer userId, Integer apartmentId, Boolean accepted) {
        this.id = id;
        this.capacity = capacity;
        this.arrivalDate = arrivalDate;
        this.departureDate = departureDate;
        this.apartmentClassId = apartmentClassId;
        this.user = new ProfileUserDto();
        this.user.setId(userId);
        this.apartment = new ProfileApartmentDto(apartmentId);
        this.accepted = accepted;
    }

    public ProfileBookingRequestDto(Integer bookingRequestId) {
        this.id = bookingRequestId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public Integer getApartmentClassId() {
        return apartmentClassId;
    }

    public void setApartmentClassId(Integer apartmentClassId) {
        this.apartmentClassId = apartmentClassId;
    }

    public String getApartmentClassName() {
        return apartmentClassName;
    }

    public void setApartmentClassName(String apartmentClassName) {
        this.apartmentClassName = apartmentClassName;
    }

    public ProfileUserDto getUser() {
        return user;
    }

    public void setUser(ProfileUserDto user) {
        this.user = user;
    }

    public LocalDate getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(LocalDate arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public LocalDate getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(LocalDate departureDate) {
        this.departureDate = departureDate;
    }

    public ProfileApartmentDto getApartment() {
        return apartment;
    }

    public void setApartment(ProfileApartmentDto apartment) {
        this.apartment = apartment;
    }

    public Boolean isAccepted() {
        return accepted;
    }

    public void setAccepted(Boolean accepted) {
        this.accepted = accepted;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProfileBookingRequestDto)) return false;
        ProfileBookingRequestDto that = (ProfileBookingRequestDto) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(capacity, that.capacity) &&
                Objects.equals(apartmentClassId, that.apartmentClassId) &&
                Objects.equals(apartmentClassName, that.apartmentClassName) &&
                Objects.equals(arrivalDate, that.arrivalDate) &&
                Objects.equals(departureDate, that.departureDate) &&
                Objects.equals(user, that.user) &&
                Objects.equals(apartment, that.apartment) &&
                Objects.equals(accepted, that.accepted);
    }

    @Override
    public String toString() {
        return "ProfileBookingRequestDto{" +
                "id=" + id +
                ", capacity=" + capacity +
                ", apartmentClassId=" + apartmentClassId +
                ", apartmentClassName='" + apartmentClassName + '\'' +
                ", arrivalDate=" + arrivalDate +
                ", departureDate=" + departureDate +
                ", user=" + user +
                ", apartment=" + apartment +
                ", accepted=" + accepted +
                '}';
    }
}
