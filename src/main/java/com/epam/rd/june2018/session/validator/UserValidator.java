package com.epam.rd.june2018.session.validator;

import com.epam.rd.june2018.session.web.dto.CreateUserDto;
import com.epam.rd.june2018.session.web.dto.ProfileUserDto;

public interface UserValidator {
    void validateUserCredentials(String email, String password);
    void validateNewUser(CreateUserDto createDto);
    void validateProfileUserDto(ProfileUserDto createDto);

}
