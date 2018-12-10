package com.epam.rd.june2018.session.web.dto;

import java.io.Serializable;
import java.util.Objects;

public class ProfileApartmentDto implements Serializable {

    private static final long serialVersionUID = -1200474427338607799L;

    private Integer id;
    private String name;
    private Integer price;
    private Integer capacity;
    private Integer apartmentClassId;
    private String apartmentClassName;
    private Integer apartmentStatusId;
    private String apartmentStatusName;

    public ProfileApartmentDto() {
        this.id = -1;
    }

    public ProfileApartmentDto(Integer id, String name, Integer price, Integer capacity, Integer apartmentClassId, String apartmentClassName, Integer apartmentStatusId, String apartmentStatusName) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.capacity = capacity;
        this.apartmentClassId = apartmentClassId;
        this.apartmentClassName = apartmentClassName;
        this.apartmentStatusId = apartmentStatusId;
        this.apartmentStatusName = apartmentStatusName;
    }

    public ProfileApartmentDto(Integer apartmentId) {
        this.id = apartmentId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getMoney(){
        float number = this.price / 100;
        float epsilon = 0.004f; // 4 tenths of a cent
        if (Math.abs(Math.round(number) - number) < epsilon) {
            return String.format("%10.0f", number); // sdb
        } else {
            return String.format("%10.2f", number); // dj_segfault
        }
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

    public Integer getApartmentStatusId() {
        return apartmentStatusId;
    }

    public void setApartmentStatusId(Integer apartmentStatusId) {
        this.apartmentStatusId = apartmentStatusId;
    }

    public String getApartmentStatusName() {
        return apartmentStatusName;
    }

    public void setApartmentStatusName(String apartmentStatusName) {
        this.apartmentStatusName = apartmentStatusName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProfileApartmentDto)) return false;
        ProfileApartmentDto that = (ProfileApartmentDto) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(price, that.price) &&
                Objects.equals(capacity, that.capacity) &&
                Objects.equals(apartmentClassId, that.apartmentClassId) &&
                Objects.equals(apartmentClassName, that.apartmentClassName) &&
                Objects.equals(apartmentStatusId, that.apartmentStatusId) &&
                Objects.equals(apartmentStatusName, that.apartmentStatusName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, price, capacity, apartmentClassId, apartmentClassName, apartmentStatusId, apartmentStatusName);
    }

    @Override
    public String toString() {
        return "ProfileApartmentDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", capacity=" + capacity +
                ", apartmentClassId=" + apartmentClassId +
                ", apartmentClassName='" + apartmentClassName + '\'' +
                ", apartmentStatusId=" + apartmentStatusId +
                ", apartmentStatusName='" + apartmentStatusName + '\'' +
                '}';
    }
}
