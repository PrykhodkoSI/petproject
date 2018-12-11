package com.prykhodkosi.petproject.servletbased.hotel.service.Impl;

import com.prykhodkosi.petproject.servletbased.hotel.service.Interface.SecurityService;
import com.prykhodkosi.petproject.servletbased.hotel.Util.StringEncryptor;
import com.prykhodkosi.petproject.servletbased.hotel.model.User;

public class SecurityServiceImpl implements SecurityService {
    @Override
    public boolean isCorrectPassword(User user, String password) {
        return user.getPassword().equals(StringEncryptor.encryptString(password));
    }
}
