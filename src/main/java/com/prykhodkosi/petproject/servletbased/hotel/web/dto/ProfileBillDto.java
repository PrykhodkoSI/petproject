package com.prykhodkosi.petproject.servletbased.hotel.web.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class ProfileBillDto implements Serializable {

    private static final long serialVersionUID = 63821439429665167L;

    private Integer id;
    private Integer price;
    private Boolean paid;
    private LocalDate billedDate;
    private LocalDate paidDate;
    private ProfileUserDto user;
    private ProfileBookingRequestDto bookingRequest;

    public ProfileBillDto() {
        this.id = -1;
    }

    public ProfileBillDto(Integer id, Integer price, Boolean isPaid, LocalDate billedDate, LocalDate paidDate, Integer userId, Integer bookingRequestId) {
        this.id = id;
        this.price = price;
        this.paid = isPaid;
        this.billedDate = billedDate;
        this.paidDate = paidDate;
        this.user = new ProfileUserDto(userId);
        this.bookingRequest = new ProfileBookingRequestDto(bookingRequestId);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPrice() {
        return price;
    }

    public String getMoney(){
        float number = this.price / 100;
        float epsilon = 0.004f; // 4 tenths of a cent
        if (Math.abs(Math.round(number) - number) < epsilon) {
            return String.format("%10.0f", number); // sdb
        } else {
            return String.format("%10.2f", number); // dj_segfault
        }
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Boolean isPaid() {
        return paid;
    }

    public void setPaid(Boolean paid) {
        this.paid = paid;
    }

    public LocalDate getBilledDate() {
        return billedDate;
    }

    public void setBilledDate(LocalDate billedDate) {
        this.billedDate = billedDate;
    }

    public LocalDate getPaidDate() {
        return paidDate;
    }

    public void setPaidDate(LocalDate paidDate) {
        this.paidDate = paidDate;
    }

    public ProfileUserDto getUser() {
        return user;
    }

    public void setUser(ProfileUserDto user) {
        this.user = user;
    }

    public ProfileBookingRequestDto getBookingRequest() {
        return bookingRequest;
    }

    public void setBookingRequest(ProfileBookingRequestDto bookingRequest) {
        this.bookingRequest = bookingRequest;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProfileBillDto that = (ProfileBillDto) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(price, that.price) &&
                Objects.equals(paid, that.paid) &&
                Objects.equals(billedDate, that.billedDate) &&
                Objects.equals(paidDate, that.paidDate) &&
                Objects.equals(user.getId(), that.user.getId()) &&
                Objects.equals(bookingRequest.getId(), that.bookingRequest.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, price, paid, billedDate, paidDate, user.getId(), bookingRequest.getId());
    }

    @Override
    public String toString() {
        return "ProfileBillDto{" +
                "id=" + id +
                ", price=" + price +
                ", paid=" + paid +
                ", billedDate=" + billedDate +
                ", paidDate=" + paidDate +
                ", user=" + user +
                ", bookingRequest=" + bookingRequest +
                '}';
    }
}
