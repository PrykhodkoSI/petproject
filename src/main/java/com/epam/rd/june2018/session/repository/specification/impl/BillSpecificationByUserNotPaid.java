package com.epam.rd.june2018.session.repository.specification.impl;

import com.epam.rd.june2018.session.exception.DatabaseException;
import com.epam.rd.june2018.session.model.User;
import com.epam.rd.june2018.session.repository.specification.Interface.BillSpecification;

public class BillSpecificationByUserNotPaid implements BillSpecification {
    private User user;

    public BillSpecificationByUserNotPaid(User user) {
        this.user = user;
    }

    @Override
    public String toSqlClauses() {
        if(user != null ){
            if(user.getId() != null && user.getId() > 0)
                return " where bills.user_id=" + user.getId() + " and bills.is_paid='false'";
            else if(user.getEmail() != null){
                return " left JOIN users on bills.user_id = users.id " +
                        "where users.email='" + user.getEmail() + "' " +
                        "and bills.is_paid='false'";
            }
        }
        throw new DatabaseException("Bad specification");
    }
}
