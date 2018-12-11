package com.prykhodkosi.petproject.servletbased.hotel.repository.Implementation;

import com.prykhodkosi.petproject.servletbased.hotel.config.PropertiesManager;
import com.prykhodkosi.petproject.servletbased.hotel.exception.DatabaseException;
import com.prykhodkosi.petproject.servletbased.hotel.model.ApartmentStatus;
import com.prykhodkosi.petproject.servletbased.hotel.repository.Interface.ApartmentStatusRepository;
import com.prykhodkosi.petproject.servletbased.hotel.repository.specification.Interface.ApartmentStatusSpecification;
import com.prykhodkosi.petproject.servletbased.hotel.repository.util.DbManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcApartmentStatusRepository implements ApartmentStatusRepository {

    private static final Logger logger = LoggerFactory.getLogger(JdbcApartmentStatusRepository.class);
    private PropertiesManager propertiesManager = new PropertiesManager();

    private Connection getConnection(){
        return DbManager.getConnection(propertiesManager.getApplicationProperties());
    }

    @SuppressWarnings("Duplicates")
    @Override
    public Boolean create(ApartmentStatus apartmentStatus) {
        String sql = "INSERT INTO apartment_statuses (name) VALUES (?)";
        try (Connection conn = getConnection()) {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, apartmentStatus.getName());

            int rowsInserted = statement.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            logger.warn(e.getMessage());
            throw new DatabaseException(e);
        }
    }

    @Override
    public List<ApartmentStatus> read(ApartmentStatusSpecification specification) {
        try (Connection conn = getConnection()) {
            Statement stat = conn.createStatement();
            String sql = "SELECT apartment_statuses.id, apartment_statuses.name from apartment_statuses" + specification.toSqlClauses() + ";";
            ResultSet resultSet = stat.executeQuery(sql);
            List<ApartmentStatus> apartmentStatuses = new ArrayList<>();
            while (resultSet.next()){

                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                apartmentStatuses.add(new ApartmentStatus(id, name));
            }
            return apartmentStatuses;
        } catch (SQLException e) {
            logger.warn(e.getMessage());
            throw new DatabaseException(e);
        }
    }

    @SuppressWarnings("Duplicates")
    @Override
    public Boolean update(ApartmentStatus apartmentStatus) {
        String sql = "UPDATE apartment_statuses SET name=? WHERE apartment_statuses.id=?";
        try (Connection conn = getConnection()) {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, apartmentStatus.getName());
            statement.setInt(2, apartmentStatus.getId());

            int rowsInserted = statement.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            logger.warn(e.getMessage());
            throw new DatabaseException(e);
        }
    }

    @SuppressWarnings("Duplicates")
    @Override
    public Boolean delete(ApartmentStatus apartmentStatus) {
        String sql = "DELETE FROM apartment_statuses WHERE apartment_statuses.id=?";
        try (Connection conn = getConnection()) {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, apartmentStatus.getId());

            int rowsInserted = statement.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            logger.warn(e.getMessage());
            throw new DatabaseException(e);
        }
    }
}
