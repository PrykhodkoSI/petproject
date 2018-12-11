package com.prykhodkosi.petproject.servletbased.hotel.validator;

import com.prykhodkosi.petproject.servletbased.hotel.web.dto.CreateUserDto;
import com.prykhodkosi.petproject.servletbased.hotel.web.dto.ProfileUserDto;

public interface UserValidator {
    void validateUserCredentials(String email, String password);
    void validateNewUser(CreateUserDto createDto);
    void validateProfileUserDto(ProfileUserDto createDto);

}
