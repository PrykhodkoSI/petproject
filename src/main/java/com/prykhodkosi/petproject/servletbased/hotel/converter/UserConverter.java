package com.prykhodkosi.petproject.servletbased.hotel.converter;

import com.prykhodkosi.petproject.servletbased.hotel.model.User;
import com.prykhodkosi.petproject.servletbased.hotel.web.dto.CreateUserDto;
import com.prykhodkosi.petproject.servletbased.hotel.web.dto.ProfileUserDto;

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
