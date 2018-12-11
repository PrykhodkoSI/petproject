package com.prykhodkosi.petproject.servletbased.hotel.model;


import java.util.Objects;

public class Apartment {
    private Integer id;
    private String name;
    private Integer price;
    private Integer capacity;
    private ApartmentClass apartmentClass;
    private ApartmentStatus apartmentStatus;

    public Apartment() {
        this.id = -1;
    }

    public Apartment(Integer id) {
        this.id = id;
    }

    public Apartment(Integer id, String name, Integer price, Integer capacity, Integer apartmentClassId, Integer apartmentStatusId) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.capacity = capacity;
        this.apartmentClass = new ApartmentClass(apartmentClassId);
        this.apartmentStatus = new ApartmentStatus(apartmentStatusId);
    }

    public Apartment(String name, Integer price, Integer capacity, Integer apartmentClassId, Integer apartmentStatusId) {
        this.name = name;
        this.price = price;
        this.capacity = capacity;
        this.apartmentClass = new ApartmentClass(apartmentClassId);
        this.apartmentStatus = new ApartmentStatus(apartmentStatusId);
    }

    public Apartment(Integer id, String name, Integer price, Integer capacity, ApartmentClass apartmentClass, ApartmentStatus apartmentStatus) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.capacity = capacity;
        this.apartmentClass = apartmentClass==null ? new ApartmentClass() : apartmentClass;
        this.apartmentStatus = apartmentStatus==null ? new ApartmentStatus() : apartmentStatus;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getPrice() {
        return price;
    }

    public String getMoney(){
        float number = this.price / 100;
        float epsilon = 0.004f;
        if (Math.abs(Math.round(number) - number) < epsilon) {
            return String.format("%10.0f", number);
        } else {
            return String.format("%10.2f", number);
        }
    }

    public Integer getCapacity() {
        return capacity;
    }

    public ApartmentClass getApartmentClass() {
        return apartmentClass;
    }

    public ApartmentStatus getApartmentStatus() {
        return apartmentStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Apartment apartment = (Apartment) o;
        return Objects.equals(id, apartment.id) &&
                Objects.equals(name, apartment.name) &&
                Objects.equals(price, apartment.price) &&
                Objects.equals(capacity, apartment.capacity) &&
                Objects.equals(apartmentClass, apartment.apartmentClass) &&
                Objects.equals(apartmentStatus, apartment.apartmentStatus);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name, price, capacity, apartmentClass, apartmentStatus);
    }

    @Override
    public String toString() {
        return "Apartment{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", capacity=" + capacity +
                ", apartmentClass=" + apartmentClass +
                ", apartmentStatus=" + apartmentStatus +
                '}';
    }
}
