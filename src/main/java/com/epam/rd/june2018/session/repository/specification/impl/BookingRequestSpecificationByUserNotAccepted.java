package com.epam.rd.june2018.session.repository.specification.impl;

import com.epam.rd.june2018.session.exception.DatabaseException;
import com.epam.rd.june2018.session.model.User;
import com.epam.rd.june2018.session.repository.specification.Interface.BookingRequestSpecification;

public class BookingRequestSpecificationByUserNotAccepted implements BookingRequestSpecification {
    private User user;

    public BookingRequestSpecificationByUserNotAccepted(User user) {
        this.user = user;
    }

    @Override
    public String toSqlClauses() {
        if(user != null ){
            if(user.getId() != null && user.getId() > 0)
                return " where booking_requests.user_id=" + user.getId() + " and booking_requests.is_accepted='false'";
            else if(user.getEmail() != null){
                return " left JOIN users on booking_requests.user_id = users.id " +
                        "where users.email='" + user.getEmail() + "' " +
                        "and booking_requests.is_accepted='false'";
            }
        }
        throw new DatabaseException("Bad specification");
    }
}
