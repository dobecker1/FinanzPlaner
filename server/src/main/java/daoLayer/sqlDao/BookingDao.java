package daoLayer.sqlDao;

import factory.ModelFactory;
import models.booking.Booking;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


@Component("bookingDao")
public class BookingDao extends BasicDao{

    private LedgerDao ledgerDao = new LedgerDao();

    public int write(Booking booking) {
        int bookingId = -1;

        try {
            PreparedStatement statement = super.controller.connection.prepareStatement
                    ("INSERT INTO BOOKING(DATE, REFERENCENUMBER, BOOKINGDESCRIPTION," +
                            " LEDGERSHOULD, SUBLEDGERSHOULD, LEDGERHAVE, SUBLEDGERHAVE, VALUE, REFERENCEPATH, FINANCIALYEAR) VALUES(?,?,?,?,?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
            statement.setDate(1, new Date(booking.getDate().getTime()));
            statement.setString(2, booking.getReferenceNumber());
            statement.setString(3, booking.getBookingDescription());
            statement.setInt(4, booking.getLedgerShould().getId());
            if(booking.getSubLedgerShould() != null) {
                statement.setInt(5, booking.getSubLedgerShould().getId());
            } else {
                statement.setInt(5,  -1);
            }
            statement.setInt(6, booking.getLedgerHave().getId());
            if(booking.getSubLedgerHave() != null) {
                statement.setInt(7, booking.getSubLedgerHave().getId());
            } else {
                statement.setInt(7, -1);
            }
            statement.setDouble(8, booking.getValue());
            statement.setString(9, booking.getReferencePath());
            statement.setString(10, booking.getFinancialYear());
            statement.executeUpdate();
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if(generatedKeys.next()) {
                bookingId = generatedKeys.getInt(1);
            }
            generatedKeys.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bookingId;
    }

    public Booking read(int id) {
        try {
            PreparedStatement statement = super.controller.connection
                    .prepareStatement("SELECT * FROM BOOKING WHERE id = ?");
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();
            if(result.next()) {
                Booking booking = ModelFactory.getBooking();
                booking.setId(result.getInt("id"));
                booking.setReferenceNumber(result.getString("referenceNumber"));
                booking.setBookingDescription(result.getString("bookingDescription"));
                booking.setDate(result.getDate("date"));
                booking.setValue(result.getDouble("value"));
                booking.setFinancialYear(result.getString("financialYear"));
                booking.setLedgerShould(this.ledgerDao.read(result.getInt("ledgerShould")));
                booking.setSubLedgerShould(this.ledgerDao.read(result.getInt("subLedgerShould")));
                booking.setLedgerHave(this.ledgerDao.read(result.getInt("ledgerHave")));
                booking.setSubLedgerHave(this.ledgerDao.read(result.getInt("subLedgerHave")));
                return booking;
            } else {
                return  null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Fehler");
        }
    }

    public void delete(Booking booking) {
        try {
            PreparedStatement statement = super.controller.connection.
                    prepareStatement("DELETE FROM BOOKING WHERE id = ?");
            statement.setInt(1, booking.getId());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Booking> findAllBookings() {
        List<Booking> bookings = new ArrayList<>();
        try {
            PreparedStatement statement = super.controller.connection.
                    prepareStatement("SELECT * FROM BOOKING");
            ResultSet result = statement.executeQuery();
            while(result.next()) {
                Booking booking = ModelFactory.getBooking();
                booking.setId(result.getInt("id"));
                booking.setReferenceNumber(result.getString("referenceNumber"));
                booking.setBookingDescription(result.getString("bookingDescription"));
                booking.setDate(result.getDate("date"));
                booking.setValue(result.getDouble("value"));
                booking.setFinancialYear(result.getString("financialYear"));
                booking.setLedgerShould(this.ledgerDao.read(result.getInt("ledgerShould")));
                int subShouldID = result.getInt("subLedgerShould");
                if(subShouldID > 0) {
                    booking.setSubLedgerShould(this.ledgerDao.read(subShouldID));
                }
                booking.setLedgerHave(this.ledgerDao.read(result.getInt("ledgerHave")));
                int subHaveID = result.getInt("subLedgerHave");
                if(subHaveID > 0) {
                    booking.setSubLedgerHave(this.ledgerDao.read(subHaveID));
                }
                bookings.add(booking);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Fehler");
        }
        return bookings;
    }
}
