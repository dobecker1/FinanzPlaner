package daoLayer.sqlDao;

import factory.ModelFactory;
import models.patternBooking.interfaces.BookingInformation;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class BookingInformationDao extends BasicDao {

    public static String BOOKING_INFO_TABLE = "BOOKINGINFORMATION";
    private LedgerDao ledgerDao = new LedgerDao();

    public int write(BookingInformation bookingInformation) {
        int bookingInfoId = -1;

        try {
            PreparedStatement statement = super.controller.connection.prepareStatement
                    ("INSERT INTO BOOKINGINFORMATION (BOOKINGDESCRIPTION," +
                            " LEDGERSHOULD, SUBLEDGERSHOULD, LEDGERHAVE, SUBLEDGERHAVE, VALUE) VALUES(?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, bookingInformation.getBookingDescription());
            statement.setInt(2, bookingInformation.getLedgerShould().getId());
            if(bookingInformation.getSubLedgerShould() != null) {
                statement.setInt(3, bookingInformation.getSubLedgerShould().getId());
            } else {
                statement.setInt(3,  -1);
            }
            statement.setInt(4, bookingInformation.getLedgerHave().getId());
            if(bookingInformation.getSubLedgerHave() != null) {
                statement.setInt(5, bookingInformation.getSubLedgerHave().getId());
            } else {
                statement.setInt(5, -1);
            }
            statement.setDouble(6, bookingInformation.getValue());
            statement.executeUpdate();
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if(generatedKeys.next()) {
                bookingInfoId = generatedKeys.getInt(1);
            }
            generatedKeys.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bookingInfoId;
    }

    public void delete(BookingInformation bookingInformation) {
        super.delete(bookingInformation, BOOKING_INFO_TABLE);
    }

    public BookingInformation read(int id) {
        try {
            PreparedStatement statement = super.controller.connection.
                    prepareStatement("select * from BOOKINGINFORMATION where id = ?");
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();
            if(result.next()) {
                BookingInformation bookingInfo = ModelFactory.getBookingInformation();
                bookingInfo.setId(result.getInt("id"));
                bookingInfo.setBookingDescription(result.getString("bookingDescription"));
                bookingInfo.setValue(result.getDouble("value"));
                bookingInfo.setLedgerShould(this.ledgerDao.read(result.getInt("ledgerShould")));
                int subShouldID = result.getInt("subLedgerShould");
                if(subShouldID > 0) {
                    bookingInfo.setSubLedgerShould(this.ledgerDao.read(subShouldID));
                }
                bookingInfo.setLedgerHave(this.ledgerDao.read(result.getInt("ledgerHave")));
                int subHaveID = result.getInt("subLedgerHave");
                if(subHaveID > 0) {
                    bookingInfo.setSubLedgerHave(this.ledgerDao.read(subHaveID));
                }
                return bookingInfo;
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Fehler");
        }
    }

    public List<BookingInformation> findAllBookingInfos() {
        List<BookingInformation> bookingInfos = new ArrayList<>();
        try {
            PreparedStatement statement = super.controller.connection.
                    prepareStatement("SELECT * FROM BOOKINGINFORMATION");
            ResultSet result = statement.executeQuery();
            while(result.next()) {
                BookingInformation bookingInfo = ModelFactory.getBookingInformation();
                bookingInfo.setId(result.getInt("id"));
                bookingInfo.setBookingDescription(result.getString("bookingDescription"));
                bookingInfo.setValue(result.getDouble("value"));
                bookingInfo.setLedgerShould(this.ledgerDao.read(result.getInt("ledgerShould")));
                int subShouldID = result.getInt("subLedgerShould");
                if(subShouldID > 0) {
                    bookingInfo.setSubLedgerShould(this.ledgerDao.read(subShouldID));
                }
                bookingInfo.setLedgerHave(this.ledgerDao.read(result.getInt("ledgerHave")));
                int subHaveID = result.getInt("subLedgerHave");
                if(subHaveID > 0) {
                    bookingInfo.setSubLedgerHave(this.ledgerDao.read(subHaveID));
                }
                bookingInfos.add(bookingInfo);
            }
            return bookingInfos;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Fehler");
        }
    }
}
