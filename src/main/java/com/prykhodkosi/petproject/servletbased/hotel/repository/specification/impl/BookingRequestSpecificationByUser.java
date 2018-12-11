package com.prykhodkosi.petproject.servletbased.hotel.repository.specification.impl;

import com.prykhodkosi.petproject.servletbased.hotel.repository.specification.Interface.BookingRequestSpecification;
import com.prykhodkosi.petproject.servletbased.hotel.exception.DatabaseException;
import com.prykhodkosi.petproject.servletbased.hotel.model.User;

public class BookingRequestSpecificationByUser implements BookingRequestSpecification {
    private User user;

    public BookingRequestSpecificationByUser(User user) {
        this.user = user;
    }

    @Override
    public String toSqlClauses() {
        if(user != null ){
            if(user.getId() != null && user.getId() > 0)
                return " where booking_requests.user_id=" + user.getId();
            else if(user.getEmail() != null){
                return " left JOIN users on booking_requests.user_id = users.id " +
                        "where users.email='" + user.getEmail() + "'";
            }
        }
        throw new DatabaseException("Bad specification");
    }
}
