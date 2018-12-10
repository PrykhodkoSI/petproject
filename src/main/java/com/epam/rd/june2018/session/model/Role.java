package com.epam.rd.june2018.session.model;


import java.io.Serializable;
import java.util.Objects;

public class Role implements Serializable {
    private static final long serialVersionUID = -7285418549835615534L;
    private Integer id;
    private String name;

    public Role() {
        this.id = -1;
    }

    public Role(Integer id) {
        this.id = id;
    }

    public Role(String name) {
        this.name = name;
    }

    public Role(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }


    public String getName() {
        return name;
    }


    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        if (!(o instanceof Role)) return false;
        Role role = (Role) o;
        return id == role.id &&
                Objects.equals(name, role.name);
    }

    @Override
    public int hashCode(){
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
