package daoLayer.sqlDao;

import factory.ModelFactory;
import models.booking.Booking;
import models.booking.metadata.BookingMetadata;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Component("bookingDao")
public class BookingDao extends BasicDao{

    private LedgerDao ledgerDao = new LedgerDao();

    private static final String BOOKING_TABLE = "BOOKING";

    public int write(Booking booking) {
        int bookingId = -1;

        try {
            PreparedStatement statement = super.controller.connection.prepareStatement
                    ("INSERT INTO BOOKING(DATE, REFERENCENUMBER, BOOKINGDESCRIPTION," +
                            " LEDGERSHOULD, SUBLEDGERSHOULD, LEDGERHAVE, SUBLEDGERHAVE, VALUE, REFERENCEPATH, FINANCIALYEAR) VALUES(?,?,?,?,?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
            statement.setDate(1, Date.valueOf(booking.getDate()));
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
                booking.setDate(result.getDate("date").toLocalDate());
                booking.setValue(result.getDouble("value"));
                booking.setFinancialYear(result.getString("financialYear"));
                booking.setReferencePath(result.getString("referencePath"));
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
        super.delete(booking, BOOKING_TABLE);
    }

    public boolean delete(int id) {
        return super.delete(id, BOOKING_TABLE);
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
                booking.setDate(result.getDate("date").toLocalDate());
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

    public List<BookingMetadata> findAllBookingMetadata() {
        List<BookingMetadata> bookings = new ArrayList<>();
        try {
            PreparedStatement statement = super.controller.connection
                    .prepareStatement("select * from BOOKING");
            ResultSet result = statement.executeQuery();
            while(result.next()) {
                BookingMetadata booking = ModelFactory.getBookingMetadata();
                booking.setId(result.getInt("id"));
                booking.setReferenceNumber(result.getString("referenceNumber"));
                booking.setBookingDescription(result.getString("bookingDescription"));
                booking.setDate(result.getDate("date").toLocalDate());
                booking.setValue(result.getDouble("value"));
                booking.setFinancialYear(result.getString("financialYear"));
                booking.setLedgerShould(result.getInt("ledgerShould"));
                booking.setSubLedgerShould(result.getInt("subLedgerShould"));
                booking.setLedgerHave(result.getInt("ledgerHave"));
                booking.setSubLedgerHave(result.getInt("subLedgerHave"));
                bookings.add(booking);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bookings;
    }

    public boolean updateBooking(Booking booking) {
        try {
            PreparedStatement statement = super.controller.connection.
                    prepareStatement("UPDATE BOOKING " +
                            "SET REFERENCENUMBER = ?, BOOKINGDESCRIPTION = ?, DATE = ?, VALUE = ?, FINANCIALYEAR = ?, REFERENCEPATH = ?, " +
                            "LEDGERSHOULD = ?, SUBLEDGERSHOULD = ?, LEDGERHAVE = ?, SUBLEDGERHAVE = ? WHERE ID = ?");
            statement.setString(1, booking.getReferenceNumber());
            statement.setString(2, booking.getBookingDescription());
            statement.setDate(3, Date.valueOf(booking.getDate()));
            statement.setDouble(4, booking.getValue());
            statement.setString(5, booking.getFinancialYear());
            statement.setString(6, booking.getReferencePath());
            statement.setInt(7, booking.getLedgerShould().getId());
            if(booking.getSubLedgerShould() != null) {
                statement.setInt(8, booking.getSubLedgerShould().getId());
            } else {
                statement.setInt(8, -1);
            }
            statement.setInt(9, booking.getLedgerHave().getId());
            if(booking.getSubLedgerHave() != null) {
                statement.setInt(10, booking.getSubLedgerHave().getId());
            } else {
                statement.setInt(10, -1);
            }
            statement.setInt(11, booking.getId());
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<Booking> findBookingsByStartEndDate(LocalDate start, LocalDate end) {
        try {
            PreparedStatement statement = super.controller.connection.
                    prepareStatement("SELECT * FROM BOOKING where DATE >= ? and DATE <= ?");
            statement.setDate(1, Date.valueOf(start));
            statement.setDate(2, Date.valueOf(end));
            return this.findMultipleBookings(statement);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Fehler");
        }
    }

    private List<Booking> findMultipleBookings(PreparedStatement statement) {
        List<Booking> bookings = new ArrayList<>();
        try {
            ResultSet result = statement.executeQuery();
            while(result.next()) {
                Booking booking = ModelFactory.getBooking();
                booking.setId(result.getInt("id"));
                booking.setReferenceNumber(result.getString("referenceNumber"));
                booking.setBookingDescription(result.getString("bookingDescription"));
                booking.setDate(result.getDate("date").toLocalDate());
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
