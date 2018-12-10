package com.epam.rd.june2018.session.web.dto;

import com.epam.rd.june2018.session.model.Role;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ProfileUserDto implements Serializable {

    private static final long serialVersionUID = -4410598592237851348L;
    private static final Role managerRole = new Role(3, "manager");
    private static final Role clientRole = new Role(2, "client");

    private Integer id;
    private String name;
    private String surname;
    private Integer age;
    private String email;
    private List<Role> roles = new ArrayList<>();

    public ProfileUserDto(Integer userId) {
        this.id = userId;
    }

    public static ProfileUserDto getUserFromSession(HttpServletRequest req) {
        HttpSession session = req.getSession();
        if(session != null){
            ProfileUserDto user = (ProfileUserDto) session.getAttribute("user");
            return user != null ? user : new ProfileUserDto();
        }
        return new ProfileUserDto();
    }

    public ProfileUserDto() {
        id = -1;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Role> getRoles() {
        return new ArrayList<>(roles);
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public boolean isManager(){
        return roles.contains(managerRole);
    }
    public boolean isClient(){
        return roles.contains(clientRole);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProfileUserDto that = (ProfileUserDto) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(surname, that.surname) &&
                Objects.equals(age, that.age) &&
                Objects.equals(email, that.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, surname, age, email);
    }

    @Override
    public String toString() {
        return "ProfileUserDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", age=" + age +
                ", email='" + email + '\'' +
                ", roles=" + roles +
                '}';
    }
}
