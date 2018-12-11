package com.prykhodkosi.petproject.servletbased.hotel.web.dto;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Objects;

public class CreateBookingRequestDto {

    private LocalDate arrivalDate;
    private LocalDate departureDate;
    private ProfileUserDto user;
    private Integer apartmentClassId;
    private String apartmentClassName;
    private Integer capacity;

    public CreateBookingRequestDto(LocalDate arrivalDate, LocalDate departureDate, ProfileUserDto user, Integer apartmentClassId, String apartmentClassName, Integer capacity) {
        this.arrivalDate = arrivalDate;
        this.departureDate = departureDate;
        this.user = user;
        this.apartmentClassId = apartmentClassId;
        this.apartmentClassName = apartmentClassName;
        this.capacity = capacity;
    }

    public static CreateBookingRequestDto getBookingRequest(HttpServletRequest req) {
        String arrivalDateString = req.getParameter("arrivalDate");
        String departureDateString = req.getParameter("departureDate");
        String capacityString = req.getParameter("capacity");
        String apartmentClassIdString = req.getParameter("apartmentClassId");
        String apartmentClassName = req.getParameter("apartmentClassName");
        ProfileUserDto user = ProfileUserDto.getUserFromSession(req);
        Integer capacity = getInteger(capacityString);
        Integer apartmentClassId = getInteger(apartmentClassIdString);
        LocalDate arrivalDate = getDate(arrivalDateString);
        LocalDate departureDate = getDate(departureDateString);
        return new CreateBookingRequestDto(arrivalDate,departureDate, user, apartmentClassId, apartmentClassName, capacity);
    }

    private static Integer getInteger(String parameter) {
        if (parameter != null && !parameter.isEmpty()) {
            return Integer.valueOf(parameter);
        }
        return null;
    }

    private static LocalDate getDate(String parameter) {
        try {
            if (parameter != null && !parameter.isEmpty()) {
                return LocalDate.parse(parameter, DateTimeFormatter.ISO_DATE);
            }
            return null;
        }catch (DateTimeParseException e){
            return null;
        }
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

    public ProfileUserDto getUser() {
        return user;
    }

    public void setUser(ProfileUserDto user) {
        this.user = user;
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

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CreateBookingRequestDto)) return false;
        CreateBookingRequestDto that = (CreateBookingRequestDto) o;
        return Objects.equals(arrivalDate, that.arrivalDate) &&
                Objects.equals(departureDate, that.departureDate) &&
                Objects.equals(user, that.user) &&
                Objects.equals(apartmentClassId, that.apartmentClassId) &&
                Objects.equals(apartmentClassName, that.apartmentClassName) &&
                Objects.equals(capacity, that.capacity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(arrivalDate, departureDate, user, apartmentClassId, apartmentClassName, capacity);
    }

    @Override
    public String toString() {
        return "CreateBookingRequestDto{" +
                "arrivalDate=" + arrivalDate +
                ", departureDate=" + departureDate +
                ", user=" + user +
                ", apartmentClassId=" + apartmentClassId +
                ", apartmentClassName='" + apartmentClassName + '\'' +
                ", capacity=" + capacity +
                '}';
    }
}
