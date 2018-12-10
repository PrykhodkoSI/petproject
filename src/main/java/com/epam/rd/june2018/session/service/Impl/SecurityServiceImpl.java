package com.epam.rd.june2018.session.service.Impl;

import com.epam.rd.june2018.session.Util.StringEncryptor;
import com.epam.rd.june2018.session.model.User;
import com.epam.rd.june2018.session.service.Interface.SecurityService;

public class SecurityServiceImpl implements SecurityService {
    @Override
    public boolean isCorrectPassword(User user, String password) {
        return user.getPassword().equals(StringEncryptor.encryptString(password));
    }
}
