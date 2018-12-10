package com.epam.rd.june2018.session.converter;

import com.epam.rd.june2018.session.model.Role;
import com.epam.rd.june2018.session.model.User;
import com.epam.rd.june2018.session.web.dto.CreateUserDto;
import com.epam.rd.june2018.session.web.dto.ProfileUserDto;

import java.util.List;
import java.util.Objects;

public class UserConverter {

    public ProfileUserDto asUserDto(User user) {
        if(user == null) return null;
        ProfileUserDto dto = new ProfileUserDto();
        dto.setId(user.getId());
        dto.setName(user.getName());
        dto.setSurname(user.getSurname());
        dto.setEmail(user.getEmail());
        dto.setAge(user.getAge());
        dto.setRoles(user.getRoles());
        return dto;
    }

    public ProfileUserDto asUserDto(CreateUserDto user) {
        if(user == null) return null;
        ProfileUserDto dto = new ProfileUserDto();
        dto.setName(user.getName());
        dto.setSurname(user.getSurname());
        dto.setEmail(user.getEmail());
        dto.setAge(user.getAge());
        return dto;
    }

    public User asAppUser(ProfileUserDto userDto) {
        if(userDto == null) return null;
        User user = new User(
                userDto.getId(),
                userDto.getName(),
                userDto.getSurname(),
                userDto.getEmail(),
                userDto.getAge(),
                userDto.getRoles());
        return user;
    }
    public User asAppUser(CreateUserDto userDto) {
        if(userDto == null) return null;
        User user = new User(
                userDto.getName(),
                userDto.getSurname(),
                userDto.getPassword(),
                userDto.getEmail(),
                userDto.getAge());
        return user;
    }

}
