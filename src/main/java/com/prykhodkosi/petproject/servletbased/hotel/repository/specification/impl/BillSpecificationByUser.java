package com.prykhodkosi.petproject.servletbased.hotel.repository.specification.impl;

import com.prykhodkosi.petproject.servletbased.hotel.repository.specification.Interface.BillSpecification;
import com.prykhodkosi.petproject.servletbased.hotel.exception.DatabaseException;
import com.prykhodkosi.petproject.servletbased.hotel.model.User;

public class BillSpecificationByUser implements BillSpecification {
    private User user;

    public BillSpecificationByUser(User user) {
        this.user = user;
    }

    @Override
    public String toSqlClauses() {
        if(user != null ){
            if(user.getId() != null && user.getId() > 0)
                return " where bills.user_id=" + user.getId();
            else if(user.getEmail() != null){
                return " left JOIN users on bills.user_id = users.id " +
                        "where users.email='" + user.getEmail() + "'";
            }
        }
        throw new DatabaseException("Bad specification");
    }
}
