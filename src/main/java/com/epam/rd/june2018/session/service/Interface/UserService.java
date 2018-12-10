package com.epam.rd.june2018.session.service.Interface;

import com.epam.rd.june2018.session.web.dto.CreateUserDto;
import com.epam.rd.june2018.session.web.dto.ProfileUserDto;

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
