package com.epam.rd.june2018.session.repository.Implementation;

import com.epam.rd.june2018.session.config.PropertiesManager;
import com.epam.rd.june2018.session.exception.DatabaseException;
import com.epam.rd.june2018.session.model.Apartment;
import com.epam.rd.june2018.session.repository.Interface.ApartmentRepository;
import com.epam.rd.june2018.session.repository.specification.Interface.ApartmentSpecification;
import com.epam.rd.june2018.session.repository.util.DbManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcApartmentRepository implements ApartmentRepository {

    private static final Logger logger = LoggerFactory.getLogger(JdbcApartmentRepository.class);
    private static final String ID_COLUMN = "id";
    private static final String NAME_COLUMN = "name";
    private static final String PRICE_COLUMN = "price";
    private static final String CAPACITY_COLUMN = "capacity";
    private static final String APARTMENT_CLASS_ID_COLUMN = "apartment_class_id";
    private static final String APARTMENT_STATUS_ID_COLUMN = "apartment_status_id";
    private PropertiesManager propertiesManager = new PropertiesManager();

    private Connection getConnection(){
        return DbManager.getConnection(propertiesManager.getApplicationProperties());
    }

    @SuppressWarnings("Duplicates")
    @Override
    public Boolean create(Apartment apartment) {
        String sql = "INSERT INTO apartments (name, price, capacity, apartment_class_id, apartment_status_id) VALUES (?,?,?,?,?)";
        try (Connection conn = getConnection()) {
            PreparedStatement statement = conn.prepareStatement(sql);
            populateStatementWithApartment(apartment, statement);

            int rowsInserted = statement.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            logger.warn(e.getMessage());
            throw new DatabaseException(e);
        }
    }

    @Override
    public List<Apartment> read(ApartmentSpecification specification) {
        try (Connection conn = getConnection()) {
            Statement stat = conn.createStatement();
            String sql = "SELECT apartments.id, apartments.name, apartments.price, apartments.capacity, apartments.apartment_class_id, apartments.apartment_status_id from apartments" + specification.toSqlClauses() + ";";
            //System.out.println(sql);
            ResultSet resultSet = stat.executeQuery(sql);
            List<Apartment> apartmentStatuses = new ArrayList<>();
            while (resultSet.next()){
                apartmentStatuses.add(getApartment(resultSet));
            }
            return apartmentStatuses;
        } catch (SQLException e) {
            logger.warn(e.getMessage());
            throw new DatabaseException(e);
        }
    }

    @SuppressWarnings("Duplicates")
    @Override
    public Boolean update(Apartment apartment) {
        String sql = "UPDATE apartments SET name=?, price=?, capacity=?, apartment_class_id=?, apartment_status_id=? WHERE apartments.id=?";
        try (Connection conn = getConnection()) {
            PreparedStatement statement = conn.prepareStatement(sql);
            populateStatementWithApartment(apartment, statement);
            statement.setInt(6, apartment.getId());

            int rowsInserted = statement.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            logger.warn(e.getMessage());//can throw MyRuntimeException
            throw new DatabaseException(e);
        }
    }

    @SuppressWarnings("Duplicates")
    @Override
    public Boolean delete(Apartment apartment) {
        String sql = "DELETE FROM apartments WHERE apartments.id=?";
        try (Connection conn = getConnection()) {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, apartment.getId());

            int rowsInserted = statement.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            logger.warn(e.getMessage());//can throw MyRuntimeException
            throw new DatabaseException(e);
        }
    }

    private void populateStatementWithApartment(Apartment apartment, PreparedStatement statement) throws SQLException {
        statement.setString(1, apartment.getName());
        statement.setInt(2, apartment.getPrice());
        statement.setInt(3, apartment.getCapacity());
        statement.setInt(4, apartment.getApartmentClass().getId());
        statement.setInt(5, apartment.getApartmentStatus().getId());
    }

    private Apartment getApartment(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt(ID_COLUMN);
        String name = resultSet.getString(NAME_COLUMN);
        int price = resultSet.getInt(PRICE_COLUMN);
        int capacity = resultSet.getInt(CAPACITY_COLUMN);
        int apartmentClassId = resultSet.getInt(APARTMENT_CLASS_ID_COLUMN);
        int apartmentStatusId = resultSet.getInt(APARTMENT_STATUS_ID_COLUMN);

        return new Apartment(id, name, price,capacity,apartmentClassId,apartmentStatusId);
    }
}
