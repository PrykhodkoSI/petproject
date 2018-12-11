package com.prykhodkosi.petproject.servletbased.hotel.repository.Implementation;

import com.prykhodkosi.petproject.servletbased.hotel.config.PropertiesManager;
import com.prykhodkosi.petproject.servletbased.hotel.exception.DatabaseException;
import com.prykhodkosi.petproject.servletbased.hotel.model.ApartmentClass;
import com.prykhodkosi.petproject.servletbased.hotel.repository.Interface.ApartmentClassRepository;
import com.prykhodkosi.petproject.servletbased.hotel.repository.specification.Interface.ApartmentClassSpecification;
import com.prykhodkosi.petproject.servletbased.hotel.repository.util.DbManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcApartmentClassRepository implements ApartmentClassRepository {

    private static final Logger logger = LoggerFactory.getLogger(JdbcApartmentClassRepository.class);
    private PropertiesManager propertiesManager = new PropertiesManager();

    private Connection getConnection(){
        return DbManager.getConnection(propertiesManager.getApplicationProperties());
    }

    @SuppressWarnings("Duplicates")
    @Override
    public Boolean create(ApartmentClass apartmentClass) {
        String sql = "INSERT INTO apartment_classes (name) VALUES (?)";
        try (Connection conn = getConnection()) {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, apartmentClass.getName());

            int rowsInserted = statement.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            logger.warn(e.getMessage());
            throw new DatabaseException(e);
        }
    }

    @Override
    public List<ApartmentClass> read(ApartmentClassSpecification specification) {
        try (Connection conn = getConnection()) {
            Statement stat = conn.createStatement();
            String sql = "SELECT apartment_classes.id, apartment_classes.name from apartment_classes" + specification.toSqlClauses() + ";";
            ResultSet resultSet = stat.executeQuery(sql);
            List<ApartmentClass> apartmentClasses = new ArrayList<>();
            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                apartmentClasses.add(new ApartmentClass(id, name));
            }
            return apartmentClasses;
        } catch (SQLException e) {
            logger.warn(e.getMessage());
            throw new DatabaseException(e);
        }
    }

    @SuppressWarnings("Duplicates")
    @Override
    public Boolean update(ApartmentClass apartmentClass) {
        String sql = "UPDATE apartment_classes SET apartment_classes.name=? WHERE apartment_classes.id=?";
        try (Connection conn = getConnection()) {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, apartmentClass.getName());
            statement.setInt(2, apartmentClass.getId());

            int rowsInserted = statement.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            logger.warn(e.getMessage());
            throw new DatabaseException(e);
        }
    }

    @SuppressWarnings("Duplicates")
    @Override
    public Boolean delete(ApartmentClass apartmentClass) {
        String sql = "DELETE FROM apartment_classes WHERE apartment_classes.id=?";
        try (Connection conn = getConnection()) {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, apartmentClass.getId());

            int rowsInserted = statement.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            logger.warn(e.getMessage());
            throw new DatabaseException(e);
        }
    }
}
