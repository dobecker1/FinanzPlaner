package daoLayer.sqlDao;

import daoLayer.services.daoServices.*;
import factory.ModelFactory;
import models.category.Category;
import models.patternBooking.interfaces.BookingInformation;
import models.patternBooking.interfaces.BookingPattern;
import models.patternBooking.interfaces.BookingPatternItem;
import models.patternBooking.interfaces.InputField;
import models.patternBooking.metaData.BookingPatternMetadata;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookingPatternDao extends BasicDao {

    private BookingPatternItemDaoService patternItemDaoService = new BookingPatternItemDaoService();
    private InputFieldDaoService inputFieldDaoService = new InputFieldDaoService();
    private BookingDaoService bookingDaoService = new BookingDaoService();
    private BookingPatternPayloadDaoService payloadDaoService = new BookingPatternPayloadDaoService();
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
                statement.setInt(1, bookingPatternId);
                statement.setInt(2, inputField.getId());
                statement.executeUpdate();
            }
            for(BookingPatternItem patternItem : bookingPattern.getBookingPatternItems()) {
                statement = super.controller.connection.
                        prepareStatement("INSERT INTO BOOKING_PATTERN_BOOKING_ITEM (BOOKINGPATTERN, BOOKINGITEM) VALUES(?,?)");
                statement.setInt(1, bookingPatternId);
                statement.setInt(2, patternItem.getId());
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
            statement = super.controller.connection.
                    prepareStatement("delete from BOOKING_PATTERN_BOOKING_ITEM where BOOKINGPATTERN = ?");
            statement.setInt(1, bookingPattern.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int id) {
        super.delete(id, BOOKING_PATTERN_TABLE);
        try {
            PreparedStatement statement = super.controller.connection.
                    prepareStatement("delete from BOOKING_PATTERN_INPUT_FIELD where BOOKINGPATTERN = ?");
            statement.setInt(1, id);
            statement.executeUpdate();
            statement = super.controller.connection.
                    prepareStatement("delete from BOOKING_PATTERN_BOOKING_ITEM where BOOKINGPATTERN = ?");
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public BookingPattern read(int id) {
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
                pattern.setBookingPatternItems(this.findPatternItemsByPatternId(pattern.getId()));
                return pattern;
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Fehler");
        }
    }

    public List<BookingPatternMetadata> findAllBookingPatternMetadata() {

        try {
            PreparedStatement statement = super.controller.connection.
                    prepareStatement("SELECT * FROM " + BOOKING_PATTERN_TABLE);
            return this.executeBookingMetadataStatement(statement);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Fehler");
        }
    }

    private List<BookingPatternMetadata> executeBookingMetadataStatement(PreparedStatement statement) {
        List<BookingPatternMetadata> bookingPatternMetadata = new ArrayList<>();
        try {
            ResultSet result = statement.executeQuery();
            while(result.next()) {
                BookingPatternMetadata patternMetadata = ModelFactory.getBookingPatternMetadata();
                patternMetadata.setId(result.getInt("id"));
                patternMetadata.setBookingInformation(result.getInt("bookinginformationId"));
                patternMetadata.setCategory(result.getInt("categoryId"));
                patternMetadata.setExecutionDate(result.getDate("executionDate"));
                patternMetadata.setExecutionDatePattern(result.getString("executionDatePattern"));
                patternMetadata.setName(result.getString("name"));
                patternMetadata.setPattern(result.getBoolean("pattern"));

                bookingPatternMetadata.add(patternMetadata);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bookingPatternMetadata;
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

    private List<BookingPatternItem> findPatternItemsByPatternId(int id) {
        List<BookingPatternItem> patternItems = new ArrayList<>();
        try {
            PreparedStatement statement = super.controller.connection.
                    prepareStatement("select * from BOOKING_PATTERN_BOOKING_ITEM BPBI " +
                            "inner join BOOKING_PATTERN_ITEM BPI ON BOOKINGITEM = BPI.ID " +
                            "where BPBI.BOOKINGPATTERN = ?");
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();
            while(result.next()) {
                BookingPatternItem bookingPatternItem = ModelFactory.getBookingPatternItem();
                bookingPatternItem.setId(result.getInt("id"));
                bookingPatternItem.setBooking(this.bookingDaoService.findBookingById(result.getInt("bookingId")));
                bookingPatternItem.setPayload(this.payloadDaoService.findPatternPayloadById(result.getInt("payloadId")));
                patternItems.add(bookingPatternItem);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return patternItems;
    }


}
