package daoLayer.sqlDao;

import daoLayer.services.BookingDaoService;
import daoLayer.services.BookingPatternPayloadDaoService;
import factory.ModelFactory;
import models.patternBooking.interfaces.BookingPatternItem;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class BookingPatternItemDao extends BasicDao {

    private BookingDaoService bookingService = new BookingDaoService();
    private BookingPatternPayloadDaoService patternPayloadDaoService = new BookingPatternPayloadDaoService();

    private static final String BOOKING_PATTERN_ITEM_TABLE = "BOOKING_PATTERN_ITEM";

    public int write(BookingPatternItem patternItem) {
        int patternItemId = -1;
        try {
            PreparedStatement statement = super.controller.connection.
                    prepareStatement("INSERT INTO BOOKING_PATTERN_ITEM(BOOKINGID, PAYLOADID) VALUES(?,?)", Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, patternItem.getBooking().getId());
            statement.setInt(2, patternItem.getPayload().getId());
            statement.executeUpdate();
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if(generatedKeys.next()) {
                patternItemId = generatedKeys.getInt(1);
            }
            generatedKeys.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return patternItemId;
    }

    public void delete(BookingPatternItem patternItem) {
        super.delete(patternItem, BOOKING_PATTERN_ITEM_TABLE);
    }

    public BookingPatternItem read(int id) {
        try {
            PreparedStatement statement = super.controller.connection.
                    prepareStatement("SELECT * FROM BOOKING_PATTERN_ITEM WHERE id = ?");
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();
            if(result.next()) {
                BookingPatternItem patternItem = ModelFactory.getBookingPatternItem();
                patternItem.setId(result.getInt("id"));
                patternItem.setBooking(this.bookingService.findBookingById(result.getInt("bookingId")));
                patternItem.setPayload(this.patternPayloadDaoService.findPatternPayloadById(result.getInt("payloadId")));
                return patternItem;
            } else  {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Fehler");
        }
    }

    //public List<BookingPatternItem> getPatternItemsByPattern
}
