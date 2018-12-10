package com.epam.rd.june2018.session.service.Interface;

import com.epam.rd.june2018.session.model.User;

public interface SecurityService {
    boolean isCorrectPassword(User user, String password);
}
