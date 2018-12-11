package com.prykhodkosi.petproject.servletbased.hotel.web.dto;

import java.time.LocalDate;
import java.util.Objects;

public class UpdateBillDto {
    private Integer id;
    private Integer price;
    private Boolean paid;
    private LocalDate billedDate;
    private LocalDate paidDate;
    private Integer userId;
    private Integer bookingRequestId;

    public UpdateBillDto() {
        this.id = -1;
    }

    public UpdateBillDto(Integer id, Integer price, Boolean isPaid, LocalDate billedDate, LocalDate paidDate, Integer userId, Integer bookingRequestId) {
        this.id = id;
        this.price = price;
        this.paid = isPaid;
        this.billedDate = billedDate;
        this.paidDate = paidDate;
        this.userId = userId;
        this.bookingRequestId = bookingRequestId;
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

    public Integer getUser() {
        return userId;
    }

    public void setUser(Integer userId) {
        this.userId = userId;
    }

    public Integer getBookingRequest() {
        return bookingRequestId;
    }

    public void setBookingRequest(Integer bookingRequestId) {
        this.bookingRequestId = bookingRequestId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UpdateBillDto that = (UpdateBillDto) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(price, that.price) &&
                Objects.equals(paid, that.paid) &&
                Objects.equals(billedDate, that.billedDate) &&
                Objects.equals(paidDate, that.paidDate) &&
                Objects.equals(userId, that.userId) &&
                Objects.equals(bookingRequestId, that.bookingRequestId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, price, paid, billedDate, paidDate, userId, bookingRequestId);
    }

    @Override
    public String toString() {
        return "UpdateBillDto{" +
                "id=" + id +
                ", price=" + price +
                ", paid=" + paid +
                ", billedDate=" + billedDate +
                ", paidDate=" + paidDate +
                ", userId=" + userId +
                ", bookingRequestId=" + bookingRequestId +
                '}';
    }
}
