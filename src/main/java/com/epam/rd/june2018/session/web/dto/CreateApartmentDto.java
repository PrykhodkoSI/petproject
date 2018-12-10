package com.epam.rd.june2018.session.web.dto;

import java.util.Objects;

public class CreateApartmentDto {
    private String name;
    private Integer price;
    private Integer capacity;
    private Integer apartmentClassId;
    private Integer apartmentStatusId;

    public CreateApartmentDto() {
    }

    public CreateApartmentDto(String name, Integer price, Integer capacity, Integer apartmentClassId, Integer apartmentStatusId) {
        this.name = name;
        this.price = price;
        this.capacity = capacity;
        this.apartmentClassId = apartmentClassId;
        this.apartmentStatusId = apartmentStatusId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
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

    public Integer getApartmentStatusId() {
        return apartmentStatusId;
    }

    public void setApartmentStatusId(Integer apartmentStatusId) {
        this.apartmentStatusId = apartmentStatusId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreateApartmentDto that = (CreateApartmentDto) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(price, that.price) &&
                Objects.equals(capacity, that.capacity) &&
                Objects.equals(apartmentClassId, that.apartmentClassId) &&
                Objects.equals(apartmentStatusId, that.apartmentStatusId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, price, capacity, apartmentClassId, apartmentStatusId);
    }

    @Override
    public String toString() {
        return "CreateApartmentDto{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", capacity=" + capacity +
                ", apartmentClassId=" + apartmentClassId +
                ", apartmentStatusId=" + apartmentStatusId +
                '}';
    }
}
