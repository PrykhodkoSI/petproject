package com.epam.rd.june2018.session.model;


import java.util.Objects;

public class ApartmentStatus {

    private Integer id;
    private String name;

    public ApartmentStatus() {
        this.id = -1;
    }

    public ApartmentStatus(Integer id) {
        this.id = id;
    }

    public ApartmentStatus(String name) {
        this.name = name;
    }

    public ApartmentStatus(Integer id, String name) {
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
        ApartmentStatus that = (ApartmentStatus) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "ApartmentStatus{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
