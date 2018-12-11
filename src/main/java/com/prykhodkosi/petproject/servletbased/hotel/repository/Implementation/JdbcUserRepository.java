package com.prykhodkosi.petproject.servletbased.hotel.repository.Implementation;

import com.prykhodkosi.petproject.servletbased.hotel.config.PropertiesManager;
import com.prykhodkosi.petproject.servletbased.hotel.exception.DatabaseException;
import com.prykhodkosi.petproject.servletbased.hotel.model.Role;
import com.prykhodkosi.petproject.servletbased.hotel.model.User;
import com.prykhodkosi.petproject.servletbased.hotel.repository.Interface.UserRepository;
import com.prykhodkosi.petproject.servletbased.hotel.repository.specification.Interface.UserSpecification;
import com.prykhodkosi.petproject.servletbased.hotel.repository.util.DbManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.*;


public class JdbcUserRepository implements UserRepository {

    private static final Logger logger = LoggerFactory.getLogger(JdbcUserRepository.class);

    private static final String ID_COLUMN = "id";
    private static final String NAME_COLUMN = "name";
    private static final String SURNAME_COLUMN = "surname";
    private static final String PASSWORD_COLUMN = "password";
    private static final String EMAIL_COLUMN = "email";
    private static final String AGE_COLUMN = "age";

    private PropertiesManager propertiesManager = new PropertiesManager();

    private Connection getConnection(){
        return DbManager.getConnection(propertiesManager.getApplicationProperties());//DriverManager.getConnection(url, username, password);
    }

    @Override
    public Boolean create(User user) {
        String sql = "INSERT INTO users (name, surname, password, email, age) VALUES (?,?,?,?,?)";
        try (Connection conn = getConnection()) {
            PreparedStatement statement = conn.prepareStatement(sql);
            populateStatementWithUser(user, statement);

            int rowsInserted = statement.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e){
            logger.warn(e.getMessage());
            throw new DatabaseException(e);
        }
    }

    @Override
    public List<User> read(UserSpecification specification) {
        try (Connection conn = getConnection()) {
            Statement stat = conn.createStatement();
            ResultSet resultSet = stat.executeQuery(String.format("SELECT users.id, " +
                            "users.name, " +
                            "users.surname, " +
                            "users.password, " +
                            "users.email, " +
                            "users.age " +
                            "from users" +
                            "%s;",
                    specification.toSqlClauses()));
            List<User> users = new ArrayList<>();
            while (resultSet.next()){
                users.add(getUser(resultSet));
            }
            return users;
        } catch (SQLException e){
            logger.warn(e.getMessage());
            throw new DatabaseException(e);
        }
    }

    @Override
    public Boolean update(User user) {
        String sql = "UPDATE users SET name=?, surname=?, password=?, email=?, age=? WHERE users.id=?";
        try (Connection conn = getConnection()) {
            PreparedStatement statement = conn.prepareStatement(sql);
            populateStatementWithUser(user, statement);
            statement.setInt(6, user.getId());

            statement.execute();
            return statement.getUpdateCount() > 0;
        } catch (SQLException e){
            logger.warn(e.getMessage());
            throw new DatabaseException(e);
        }
    }

    @Override
    public Boolean delete(User user) {
        String sql = "DELETE FROM users WHERE users.id=?";
        try (Connection conn = getConnection()) {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, user.getId());

            statement.executeUpdate();
            return statement.getUpdateCount() > 0;
        } catch (SQLException e){
            logger.warn(e.getMessage());
            throw new DatabaseException(e);
        }
    }

    private void populateStatementWithUser(User user, PreparedStatement statement) throws SQLException {
        statement.setString(1, user.getName());
        statement.setString(2, user.getSurname());
        statement.setString(3, user.getPassword());
        statement.setString(4, user.getEmail());
        statement.setInt(5, user.getAge());
    }

    private User getUser(ResultSet resultSet) throws SQLException {
        int tableId = resultSet.getInt(ID_COLUMN);
        String name = resultSet.getString(NAME_COLUMN);
        String surname = resultSet.getString(SURNAME_COLUMN);
        String password = resultSet.getString(PASSWORD_COLUMN);
        String email = resultSet.getString(EMAIL_COLUMN);
        Integer age = resultSet.getInt(AGE_COLUMN);

        return new User(tableId, name, surname, password, email, age);
    }

    @Override
    public Boolean grantRole(User user, Role role) {
        String sql = "INSERT INTO users_roles (user_id, role_id) VALUES (?, ?)";
        try (Connection conn = getConnection()) {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(2, role.getId());
            statement.setInt(1, user.getId());

            int rowsInserted = statement.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e){
            logger.warn(e.getMessage());
            throw new DatabaseException(e);
        }
    }

    @Override
    public Boolean revokeRole(User user, Role role) {
        String sql = "DELETE FROM users_roles WHERE users_roles.user_id=? AND users_roles.role_id=?";
        try (Connection conn = getConnection()) {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, user.getId());
            statement.setInt(2, role.getId());

            int rowsInserted = statement.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e){
            logger.warn(e.getMessage());
            throw new DatabaseException(e);
        }
    }
}
