package com.epam.rd.june2018.session.repository.Implementation;

import com.epam.rd.june2018.session.config.PropertiesManager;
import com.epam.rd.june2018.session.exception.DatabaseException;
import com.epam.rd.june2018.session.model.Bill;
import com.epam.rd.june2018.session.repository.Interface.BillRepository;
import com.epam.rd.june2018.session.repository.specification.Interface.BillSpecification;
import com.epam.rd.june2018.session.repository.util.DbManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class JdbcBillRepository implements BillRepository {
    private static final Logger logger = LoggerFactory.getLogger(JdbcBillRepository.class);
    private static final String ID_COLUMN = "id";
    private static final String PRICE_COLUMN = "price";
    private static final String IS_PAID_COLUMN = "is_paid";
    private static final String BILLED_DATE_COLUMN = "billed_date";
    private static final String PAID_DATE_COLUMN = "paid_date";
    private static final String USER_ID_COLUMN = "user_id";
    private static final String BOOKING_REQUEST_ID_COLUMN = "booking_request_id";

    private PropertiesManager propertiesManager = new PropertiesManager();

    private Connection getConnection(){
        return DbManager.getConnection(propertiesManager.getApplicationProperties());
    }

    @SuppressWarnings("Duplicates")
    @Override
    public Boolean create(Bill bill) {
        String sql = "INSERT INTO bills (price, is_paid, billed_date, paid_date, user_id, booking_request_id) VALUES (?,?,?,?,?,?)";
        try (Connection conn = getConnection()) {
            PreparedStatement statement = conn.prepareStatement(sql);
            populateStatementWithBill(bill, statement);

            int rowsInserted = statement.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            logger.warn(e.getMessage());
            throw new DatabaseException(e);
        }
    }



    @Override
    public List<Bill> read(BillSpecification specification) {
        try (Connection conn = getConnection()) {
            Statement stat = conn.createStatement();
            String sql = "SELECT bills.id, bills.price, bills.is_paid, bills.billed_date, bills.paid_date, bills.user_id, bills.booking_request_id from bills" + specification.toSqlClauses() + ";";
            //System.out.println(sql);
            ResultSet resultSet = stat.executeQuery(sql);
            List<Bill> apartmentStatuses = new ArrayList<>();
            while (resultSet.next()){
                apartmentStatuses.add(getBill(resultSet));
            }
            return apartmentStatuses;
        } catch (SQLException e) {
            logger.warn(e.getMessage());
            throw new DatabaseException(e);
        }
    }

    @SuppressWarnings("Duplicates")
    @Override
    public Boolean update(Bill bill) {
        String sql = "UPDATE bills SET price=?, is_paid=?, billed_date=?, paid_date=?, user_id=?, booking_request_id=? WHERE bills.id=?";
        try (Connection conn = getConnection()) {
            PreparedStatement statement = conn.prepareStatement(sql);
            populateStatementWithBill(bill, statement);
            statement.setInt(7, bill.getId());

            int rowsInserted = statement.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e){
            logger.warn(e.getMessage());//can throw MyRuntimeException
            throw new DatabaseException(e);
        }
    }

    @SuppressWarnings("Duplicates")
    @Override
    public Boolean delete(Bill bill) {
        String sql = "DELETE FROM bills WHERE bills.id=?";
        try (Connection conn = getConnection()) {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, bill.getId());

            int rowsInserted = statement.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            logger.warn(e.getMessage());//can throw MyRuntimeException
            throw new DatabaseException(e);
        }
    }

    private void populateStatementWithBill(Bill bill, PreparedStatement statement) throws SQLException {
        statement.setInt(1, bill.getPrice());
        statement.setObject(2, bill.isPaid());//, Types.BOOLEAN
        statement.setObject(3, bill.getBilledDate());//, JDBCType.DATE
        statement.setObject(4, bill.getPaidDate()  );//, JDBCType.DATE
        statement.setInt(5, bill.getUser().getId());
        statement.setInt(6, bill.getBookingRequest().getId());
    }

    private Bill getBill(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt(ID_COLUMN);
        int price = resultSet.getInt(PRICE_COLUMN);
        boolean isPaid = resultSet.getBoolean(IS_PAID_COLUMN);
        LocalDate billedDate = resultSet.getObject(BILLED_DATE_COLUMN, LocalDate.class);
        LocalDate paidDate = resultSet.getObject(PAID_DATE_COLUMN, LocalDate.class);
        int userId = resultSet.getInt(USER_ID_COLUMN);
        int bookingRequestId = resultSet.getInt(BOOKING_REQUEST_ID_COLUMN);

        return new Bill(id, price, isPaid, billedDate, paidDate, userId, bookingRequestId);
    }
}
