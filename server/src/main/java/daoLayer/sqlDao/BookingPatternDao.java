package daoLayer.sqlDao;

import daoLayer.services.BookingPatternItemDaoService;
import daoLayer.services.InputFieldDaoService;
import daoLayer.services.LedgerDaoService;
import factory.ModelFactory;
import jdk.internal.util.xml.impl.Input;
import models.category.Category;
import models.patternBooking.interfaces.BookingInformation;
import models.patternBooking.interfaces.BookingPattern;
import models.patternBooking.interfaces.InputField;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookingPatternDao extends BasicDao {

    private BookingPatternItemDaoService patternItemDaoService = new BookingPatternItemDaoService();
    private InputFieldDaoService inputFieldDaoService = new InputFieldDaoService();
    private LedgerDaoService ledgerDaoService = new LedgerDaoService();

    private static final String BOOKING_PATTERN_TABLE = "BOOKING_PATTERN";

    public int write(BookingPattern bookingPattern) {
        int bookingPatternId = -1;

        try {
            PreparedStatement statement = super.controller.connection.
                    prepareStatement("INSERT INTO BOOKING_PATTERN (NAME, EXECUTIONDATE, EXECUTIONDATEPATTERN, BOOKINGINFORMATIONID, CATEGORYID, PATTERN) VALUES(?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, bookingPattern.getName());
            statement.setDate(2, new Date(bookingPattern.getExecutionDate().getTime()));
            statement.setString(3, bookingPattern.getExecutionDatePattern());
            statement.setInt(4, bookingPattern.getBookingInformation().getId());
            statement.setInt(5, bookingPattern.getCategory().getId());
            statement.setBoolean(6, bookingPattern.isPattern());
            statement.executeUpdate();
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if(generatedKeys.next()) {
                bookingPatternId = generatedKeys.getInt(1);
            }
            generatedKeys.close();
            for(InputField inputField : bookingPattern.getInputFields()) {
                statement = super.controller.connection
                        .prepareStatement("INSERT INTO BOOKING_PATTERN_INPUT_FIELD (BOOKINGPATTERN, INPUTFIELD) VALUES(?,?)");
                statement.setInt(1, bookingPatternIdd);
                statement.setInt(2, inputField.getId());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bookingPatternId;
    }

    public void delete(BookingPattern bookingPattern) {
        super.delete(bookingPattern, BOOKING_PATTERN_TABLE);
        try {
            PreparedStatement statement = super.controller.connection.
                    prepareStatement("delete from BOOKING_PATTERN_INPUT_FIELD where BOOKINGPATTERN = ?");
            statement.setInt(1, bookingPattern.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public BookingPattern findBookingPatternById(int id) {
        try {
            PreparedStatement statement = super.controller.connection.
                    prepareStatement("SELECT * FROM BOOKING_PATTERN BP INNER JOIN BOOKINGINFORMATION BI ON BOOKINGINFORMATIONID = BI.ID" +
                            " INNER JOIN CATEGORY C ON CATEGORYID = C.ID" +
                            " WHERE BP.ID = ?");
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();
            if(result.next()) {
                BookingPattern pattern = ModelFactory.getBookingPattern();
                pattern.setId(result.getInt("id"));
                pattern.setName(result.getString("name"));
                pattern.setPattern(result.getBoolean("pattern"));
                pattern.setExecutionDate(result.getDate("executionDate"));
                pattern.setExecutionDatePattern(result.getString("executionDatePattern"));

                BookingInformation bookingInfo = ModelFactory.getBookingInformation();
                bookingInfo.setId(result.getInt(8));
                bookingInfo.setValue(result.getDouble("value"));
                bookingInfo.setBookingDescription(result.getString("bookingDescription"));
                bookingInfo.setLedgerShould(this.ledgerDaoService.findLedgerById(result.getInt("ledgerShould")));
                bookingInfo.setLedgerHave(this.ledgerDaoService.findLedgerById(result.getInt("ledgerHave")));
                int subLedgerHaveId = result.getInt("subLedgerHave");
                if(subLedgerHaveId > 0) {
                    bookingInfo.setSubLedgerHave(this.ledgerDaoService.findLedgerById(subLedgerHaveId));
                }
                int subLedgerShouldId = result.getInt("subLedgerShould");
                if(subLedgerShouldId > 0) {
                    bookingInfo.setSubLedgerShould(this.ledgerDaoService.findLedgerById(subLedgerShouldId));
                }
                pattern.setBookingInformation(bookingInfo);

                Category category = ModelFactory.getCategory();
                category.setId(result.getInt(15));
                category.setName(result.getString(16));
                pattern.setCategory(category);
                pattern.setInputFields(this.findInputFieldsByPatternId(pattern.getId()));
                return pattern;
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Fehler");
        }
    }

    private List<InputField> findInputFieldsByPatternId(int id) {
        List<InputField> inputFields = new ArrayList<>();
        try {
            PreparedStatement statement = super.controller.connection.
                    prepareStatement("select * from BOOKING_PATTERN_INPUT_FIELD BPIF " +
                            "inner join INPUT_FIELD IF ON INPUTFIELD = IF.ID" +
                            " where BPIF.BOOKINGPATTERN = ?");
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();
            while(result.next()) {
                InputField inputField = ModelFactory.getInputField();
                inputField.setId(result.getInt("id"));
                inputField.setInputFieldType(InputField.InputFieldType.valueOf(result.getString("type")));
                inputField.setName(result.getString("name"));
                inputFields.add(inputField);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return inputFields;
    }
}
