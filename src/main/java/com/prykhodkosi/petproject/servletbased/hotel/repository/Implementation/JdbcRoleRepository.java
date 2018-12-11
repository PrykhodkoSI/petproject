package com.prykhodkosi.petproject.servletbased.hotel.repository.Implementation;

import com.prykhodkosi.petproject.servletbased.hotel.config.PropertiesManager;
import com.prykhodkosi.petproject.servletbased.hotel.exception.DatabaseException;
import com.prykhodkosi.petproject.servletbased.hotel.model.Role;
import com.prykhodkosi.petproject.servletbased.hotel.repository.Interface.RoleRepository;
import com.prykhodkosi.petproject.servletbased.hotel.repository.specification.Interface.RoleSpecification;
import com.prykhodkosi.petproject.servletbased.hotel.repository.util.DbManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.*;

public class JdbcRoleRepository implements RoleRepository {

    private static final Logger logger = LoggerFactory.getLogger(JdbcRoleRepository.class);
    private PropertiesManager propertiesManager = new PropertiesManager();

    private Connection getConnection(){
        return DbManager.getConnection(propertiesManager.getApplicationProperties());//DriverManager.getConnection(url, username, password);
    }

    @SuppressWarnings("Duplicates")
    @Override
    public Boolean create(Role role) {
        String sql = "INSERT INTO roles (name) VALUES (?)";
        try (Connection conn = getConnection()) {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, role.getName());

            int rowsInserted = statement.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            logger.warn(e.getMessage());
            throw new DatabaseException(e);
        }
    }

    @Override
    public List<Role> read(RoleSpecification specification) {
        try (Connection conn = getConnection()) {
            Statement stat = conn.createStatement();
            String sql = "SELECT roles.id, roles.name from roles" + specification.toSqlClauses() + ";";
            ResultSet resultSet = stat.executeQuery(sql);
            List<Role> roles = new ArrayList<>();
            while (resultSet.next()){

                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                roles.add(new Role(id, name));
            }
            return roles;
        } catch (SQLException e){
            logger.warn(e.getMessage());
            throw new DatabaseException(e);
        }
    }

    @SuppressWarnings("Duplicates")
    @Override
    public Boolean update(Role role) {
        String sql = "UPDATE roles SET name=? WHERE roles.id=?";
        try (Connection conn = getConnection()) {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, role.getName());
            statement.setInt(2, role.getId());

            int rowsInserted = statement.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e){
            logger.warn(e.getMessage());
            throw new DatabaseException(e);
        }
    }

    @SuppressWarnings("Duplicates")
    @Override
    public Boolean delete(Role role) {
        String sql = "DELETE FROM roles WHERE roles.id=?";
        try (Connection conn = getConnection()) {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, role.getId());

            int rowsInserted = statement.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e){
            logger.warn(e.getMessage());
            throw new DatabaseException(e);
        }
    }
}