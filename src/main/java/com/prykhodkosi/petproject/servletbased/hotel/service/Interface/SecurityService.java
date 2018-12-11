package com.prykhodkosi.petproject.servletbased.hotel.service.Interface;

import com.prykhodkosi.petproject.servletbased.hotel.model.User;

public interface SecurityService {
    boolean isCorrectPassword(User user, String password);
}
