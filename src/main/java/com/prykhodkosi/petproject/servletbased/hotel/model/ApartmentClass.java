package com.prykhodkosi.petproject.servletbased.hotel.model;


import java.io.Serializable;
import java.util.Objects;

public class ApartmentClass implements Serializable {
    private static final long serialVersionUID = 4396295283168094494L;
    private Integer id;
    private String name;

    public ApartmentClass() {
        this.id = -1;
    }

    public ApartmentClass(Integer id) {
        this.id = id;
    }

    public ApartmentClass(String name) {
        this.name = name;
    }

    public ApartmentClass(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ApartmentClass that = (ApartmentClass) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "ApartmentClass{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

}
