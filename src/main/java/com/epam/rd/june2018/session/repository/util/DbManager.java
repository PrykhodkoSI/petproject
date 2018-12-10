package com.epam.rd.june2018.session.repository.util;

import com.epam.rd.june2018.session.exception.ApplicationException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DbManager {
    public static Connection getConnection(Properties properties) {
        try {
            String url = properties.getProperty("url");
            String username = properties.getProperty("username");
            String password = properties.getProperty("password");
            return DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            throw new ApplicationException("Failed to open the DB connection", e);
        }
    }
}
