package com.prykhodkosi.petproject.servletbased.hotel.service.Interface;

import com.prykhodkosi.petproject.servletbased.hotel.web.dto.CreateUserDto;
import com.prykhodkosi.petproject.servletbased.hotel.web.dto.ProfileUserDto;

import java.util.List;

public interface UserService {

    ProfileUserDto getUser(Integer id);
    ProfileUserDto getUserByEmail(String email);
    List<ProfileUserDto> getUsers();
    ProfileUserDto registerUser(CreateUserDto user);
    ProfileUserDto loginUser(String email, String password);
    ProfileUserDto putUser(ProfileUserDto user);
    ProfileUserDto deleteUser(Integer id);
}
