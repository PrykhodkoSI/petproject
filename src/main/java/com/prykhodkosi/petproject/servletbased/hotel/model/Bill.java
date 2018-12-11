package com.prykhodkosi.petproject.servletbased.hotel.model;


import java.time.LocalDate;
import java.util.Objects;

public class Bill {
    private Integer id;
    private Integer price;
    private Boolean paid;
    private LocalDate billedDate;
    private LocalDate paidDate;
    private User user;
    private BookingRequest bookingRequest;

    public Bill() {
        this.id = -1;
    }

    public Bill(Integer id) {
        this.id = id;
    }

    public Bill(Integer id, Integer price, Boolean isPaid, LocalDate billedDate, LocalDate paidDate, Integer userId, Integer bookingRequestId) {
        this.id = id;
        this.price = price;
        this.paid = isPaid;
        this.billedDate = billedDate;
        this.paidDate = paidDate;
        this.user = new User(userId);
        this.bookingRequest = new BookingRequest(bookingRequestId);
    }

    public Bill(Integer id, Integer price, Boolean isPaid, LocalDate billedDate, LocalDate paidDate, User user, BookingRequest bookingRequest) {
        this.id = id;
        this.price = price;
        this.paid = isPaid;
        this.billedDate = billedDate;
        this.paidDate = paidDate;
        this.user = user==null ? new User() : user;
        this.bookingRequest = bookingRequest==null ? new BookingRequest() : bookingRequest;
    }

    public Bill(Integer price, boolean isPaid, LocalDate billedDate, User user, BookingRequest bookingRequest) {
        this.price = price;
        this.paid = isPaid;
        this.billedDate = billedDate;
        this.user = user;
        this.bookingRequest = bookingRequest==null ? new BookingRequest() : bookingRequest;
    }

    public Integer getId() {
        return id;
    }

    public Integer getPrice() {
        return price;
    }

    public Boolean isPaid() {
        return paid;
    }

    public LocalDate getBilledDate() {
        return billedDate;
    }

    public LocalDate getPaidDate() {
        return paidDate;
    }

    public User getUser() {
        return user;
    }

    public BookingRequest getBookingRequest() {
        return bookingRequest;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bill bill = (Bill) o;
        return Objects.equals(id, bill.id) &&
                Objects.equals(price, bill.price) &&
                Objects.equals(paid, bill.paid) &&
                Objects.equals(billedDate, bill.billedDate) &&
                Objects.equals(paidDate, bill.paidDate) &&
                Objects.equals(user, bill.user) &&
                Objects.equals(bookingRequest, bill.bookingRequest);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, price, paid, billedDate, paidDate, user, bookingRequest);
    }

    @Override
    public String toString() {
        return "Bill{" +
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
