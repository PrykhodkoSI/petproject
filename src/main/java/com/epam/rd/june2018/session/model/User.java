package com.epam.rd.june2018.session.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class User{
    private Integer id;
    private String name;
    private String surname;
    private String password;
    private String email;
    private Integer age;
    private List<Role> roles = new ArrayList<>();


    public User() {
        this.id = -1;
    }

    public User(Integer id) {
        this.id = id;
    }

    public User(String name, String surname, String password, String email, Integer age) {
        this.name = name;
        this.surname = surname;
        this.password = password;
        this.email = email;
        this.age = age;
    }

    public User(int id, String name, String surname, String email, Integer age, List<Role> roles) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.age = age;
        this.roles = roles;
    }

    public User(int id, String name, String surname, String password, String email, Integer age) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.password = password;
        this.email = email;
        this.age = age;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public Integer getAge() {
        return age;
    }

    public List<Role> getRoles() {
        return roles;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id &&
                Objects.equals(name, user.name) &&
                Objects.equals(surname, user.surname) &&
                Objects.equals(password, user.password) &&
                Objects.equals(email, user.email) &&
                Objects.equals(age, user.age);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, surname, password, email, age);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", age=" + age +
                ", roles=" + roles +
                '}';
    }
}