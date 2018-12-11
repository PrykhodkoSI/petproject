package com.prykhodkosi.petproject.servletbased.hotel.repository.Implementation;

import com.prykhodkosi.petproject.servletbased.hotel.config.PropertiesManager;
import com.prykhodkosi.petproject.servletbased.hotel.exception.DatabaseException;
import com.prykhodkosi.petproject.servletbased.hotel.model.BookingRequest;
import com.prykhodkosi.petproject.servletbased.hotel.repository.Interface.BookingRequestRepository;
import com.prykhodkosi.petproject.servletbased.hotel.repository.specification.Interface.BookingRequestSpecification;
import com.prykhodkosi.petproject.servletbased.hotel.repository.util.DbManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class JdbcBookingRequestRepository implements BookingRequestRepository {
    private static final Logger logger = LoggerFactory.getLogger(JdbcBookingRequestRepository.class);
    private static final String ID_COLUMN = "id";
    private static final String CAPACITY_COLUMN = "capacity";
    private static final String APARTMENT_CLASS_ID_COLUMN = "apartment_class_id";
    private static final String ARRIVAL_DATE_COLUMN = "arrival_date";
    private static final String DEPARTURE_DATE_COLUMN = "departure_date";
    private static final String USER_ID_COLUMN = "user_id";
    private static final String APARTMENT_ID_COLUMN = "apartment_id";
    private static final String IS_ACCEPTED_ID_COLUMN = "is_accepted";
    private PropertiesManager propertiesManager = new PropertiesManager();

    private Connection getConnection(){
        return DbManager.getConnection(propertiesManager.getApplicationProperties());
    }

    @SuppressWarnings("Duplicates")
    @Override
    public Boolean create(BookingRequest bookingRequest) {
        String sql = "INSERT INTO booking_requests (capacity, apartment_class_id, arrival_date, departure_date, user_id, apartment_id, is_accepted) VALUES (?,?,?,?,?,?,?)";
        try (Connection conn = getConnection()) {
            PreparedStatement statement = conn.prepareStatement(sql);
            populateStatementWithBookingRequest(bookingRequest, statement);

            int rowsInserted = statement.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e){
            logger.warn(e.getMessage());
            throw new DatabaseException(e);
        }
    }

    @Override
    public List<BookingRequest> read(BookingRequestSpecification specification) {
        try (Connection conn = getConnection()) {
            Statement stat = conn.createStatement();
            String sql = "SELECT booking_requests.id, booking_requests.capacity, booking_requests.apartment_class_id, booking_requests.arrival_date, booking_requests.departure_date, booking_requests.user_id, booking_requests.apartment_id, booking_requests.is_accepted from booking_requests" + specification.toSqlClauses() + ";";
            ResultSet resultSet = stat.executeQuery(sql);
            List<BookingRequest> apartmentStatuses = new ArrayList<>();
            while (resultSet.next()){
                apartmentStatuses.add(getBookingRequest(resultSet));
            }
            return apartmentStatuses;
        } catch (SQLException e){
            logger.warn(e.getMessage());
            throw new DatabaseException(e);
        }
    }

    @SuppressWarnings("Duplicates")
    @Override
    public Boolean update(BookingRequest bookingRequest) {
        String sql = "UPDATE booking_requests SET capacity=?, apartment_class_id=?, arrival_date=?, departure_date=?, user_id=?, apartment_id=?, is_accepted=? WHERE booking_requests.id=?";
        try (Connection conn = getConnection()) {
            PreparedStatement statement = conn.prepareStatement(sql);
            populateStatementWithBookingRequest(bookingRequest, statement);
            statement.setInt(8, bookingRequest.getId());

            int rowsInserted = statement.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e){
            logger.warn(e.getMessage());//can throw MyRuntimeException
            throw new DatabaseException(e);
        }
    }

    @SuppressWarnings("Duplicates")
    @Override
    public Boolean delete(BookingRequest bookingRequest) {
        String sql = "DELETE FROM booking_requests WHERE booking_requests.id=?";
        try (Connection conn = getConnection()) {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, bookingRequest.getId());

            int rowsInserted = statement.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e){
            logger.warn(e.getMessage());//can throw MyRuntimeException
            throw new DatabaseException(e);
        }
    }

    private void populateStatementWithBookingRequest(BookingRequest bookingRequest, PreparedStatement statement) throws SQLException {
        statement.setInt(1, bookingRequest.getCapacity());
        statement.setInt(2, bookingRequest.getApartmentClass().getId());
        statement.setObject(3, bookingRequest.getArrivalDate());  //JDBCType.DATE
        statement.setObject(4, bookingRequest.getDepartureDate());//JDBCType.DATE
        statement.setInt(5, bookingRequest.getUser().getId());
        statement.setObject(6, bookingRequest.getApartment().getId());
        statement.setBoolean(7, bookingRequest.isAccepted());
    }

    private BookingRequest getBookingRequest(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt(ID_COLUMN);
        int capacity = resultSet.getInt(CAPACITY_COLUMN);
        int apartmentClassId = resultSet.getInt(APARTMENT_CLASS_ID_COLUMN);
        LocalDate arrivalDate = resultSet.getObject(ARRIVAL_DATE_COLUMN, LocalDate.class);
        LocalDate departureDate = resultSet.getObject(DEPARTURE_DATE_COLUMN, LocalDate.class);
        int userId = resultSet.getInt(USER_ID_COLUMN);
        Integer apartmentId = resultSet.getObject(APARTMENT_ID_COLUMN, Integer.class);
        boolean isAccepted = resultSet.getBoolean(IS_ACCEPTED_ID_COLUMN);

        if(apartmentId == null || apartmentId == 0){
            apartmentId = null;
        }

        return new BookingRequest(id, capacity, apartmentClassId, arrivalDate, departureDate, userId, apartmentId, isAccepted);
    }
}
