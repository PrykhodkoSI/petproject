package com.prykhodkosi.petproject.servletbased.hotel.web.dto;

import com.prykhodkosi.petproject.servletbased.hotel.Util.StringDecoder;

import javax.servlet.http.HttpServletRequest;

public class CreateUserDto {

    private String name;
    private String surname;
    private String password;
    private String email;
    private Integer age;

    public CreateUserDto(String name, String surname, String password, String email, Integer age) {
        this.name = name;
        this.surname = surname;
        this.password = password;
        this.email = email;
        this.age = age;
    }

    public static CreateUserDto getUser(HttpServletRequest req) {
        String name = StringDecoder.decodeISO88591(req.getParameter("name"));
        String surname = StringDecoder.decodeISO88591(req.getParameter("surname"));
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String ageParam = req.getParameter("age");
        Integer age = null;
        if (ageParam != null && !ageParam.isEmpty()) {
            age = Integer.valueOf(ageParam);
        }
        return new CreateUserDto(name, surname,password , email, age);
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
