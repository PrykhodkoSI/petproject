package com.epam.rd.june2018.session.web.dto;

import java.time.LocalDate;
import java.util.Objects;

public class CreateBillDto {
    private Integer price;
    private LocalDate billedDate;
    private Integer userId;
    private Integer bookingRequestId;

    public CreateBillDto() {
    }

    public CreateBillDto(Integer price, LocalDate billedDate, Integer userId, Integer bookingRequestId) {
        this.price = price;
        this.billedDate = billedDate;
        this.userId = userId;
        this.bookingRequestId = bookingRequestId;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public LocalDate getBilledDate() {
        return billedDate;
    }

    public void setBilledDate(LocalDate billedDate) {
        this.billedDate = billedDate;
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
        CreateBillDto that = (CreateBillDto) o;
        return Objects.equals(price, that.price) &&
                Objects.equals(billedDate, that.billedDate) &&
                Objects.equals(userId, that.userId) &&
                Objects.equals(bookingRequestId, that.bookingRequestId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(price, billedDate, userId, bookingRequestId);
    }

    @Override
    public String toString() {
        return "CreateBillDto{" +
                "price=" + price +
                ", billedDate=" + billedDate +
                ", userId=" + userId +
                ", bookingRequestId=" + bookingRequestId +
                '}';
    }
}
