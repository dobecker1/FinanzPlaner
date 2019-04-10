package daoTests;

import daoLayer.services.daoServices.*;
import daoLayer.sqlDao.*;
import factory.ModelFactory;
import models.booking.Booking;
import models.category.Category;
import models.ledger.Ledger;
import models.patternBooking.interfaces.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BookingPatternDaoTest extends BasicDao {

    private BookingDaoService bookingDaoService = new BookingDaoService();
    private BookingPatternPayloadDaoService patternPayloadDaoService = new BookingPatternPayloadDaoService();
    private LedgerDaoService ledgerDaoService = new LedgerDaoService();
    private CategoryDaoService categoryDaoService = new CategoryDaoService();
    private BookingInformationDaoService bookingInformationDaoService = new BookingInformationDaoService();
    private BookingPatternItemDaoService patternItemDaoService = new BookingPatternItemDaoService();
    private InputFieldDaoService inputFieldDaoService = new InputFieldDaoService();

    private BookingPatternDao patternDao = new BookingPatternDao();

    private Ledger ledgerShould;
    private Ledger ledgerHave;
    private Category category;

    private Booking booking;
    private BookingPatternPayload patternPayload;
    private BookingPatternItem patternItem;
    private BookingPatternItem patternItem1;
    private BookingInformation bookingInformation;
    private InputField inputField;
    private InputField inputField1;


    @BeforeEach
    void createBookingPatternObjects() {
        this.category = ModelFactory.getCategory();
        this.category.setName("Test Kategorie");

        this.category.setId(this.categoryDaoService.saveCategory(category));

        this.ledgerShould = ModelFactory.getLedger();
        this.ledgerHave = ModelFactory.getLedger();

        this.ledgerShould.setName("LedgerShould");
        this.ledgerShould.setDescription("LedgerShould Description");
        this.ledgerShould.setValue(0);
        this.ledgerShould.setLedgerNumber(123);

        this.ledgerHave.setName("LedgerHave");
        this.ledgerHave.setDescription("LedgerHave Description");
        this.ledgerHave.setValue(10);
        this.ledgerHave.setLedgerNumber(456);

        this.ledgerShould.setId(this.ledgerDaoService.saveLedger(this.ledgerShould));
        this.ledgerHave.setId(this.ledgerDaoService.saveLedger(this.ledgerHave));

        this.booking = ModelFactory.getBooking();
        this.booking.setLedgerShould(this.ledgerShould);
        this.booking.setLedgerHave(this.ledgerHave);
        this.booking.setValue(200);
        this.booking.setDate(new Date());
        this.booking.setBookingDescription("Booking Description");
        this.booking.setReferenceNumber("S03");
        this.booking.setId(this.bookingDaoService.saveBooking(this.booking));

        this.patternPayload = ModelFactory.getBookingPatternPayload();
        Map<String, String> payload = new HashMap<>();
        payload.put("inputName", "inputValue");
        payload.put("inputDate", new Date().toString());
        payload.put("inputNumber", "9");
        this.patternPayload.setBookingPatternPayload(payload);
        this.patternPayload.setId(this.patternPayloadDaoService.saveBookingPatternPayload(this.patternPayload));

        this.bookingInformation = ModelFactory.getBookingInformation();
        this.bookingInformation.setLedgerHave(this.ledgerHave);
        this.bookingInformation.setLedgerShould(this.ledgerShould);
        this.bookingInformation.setBookingDescription(this.booking.getBookingDescription());
        this.bookingInformation.setValue(555.5);
        this.bookingInformation.setId(this.bookingInformationDaoService.saveBookingInformation(this.bookingInformation));

        this.inputField = ModelFactory.getInputField();
        this.inputField.setName("input 1");
        this.inputField.setInputFieldType(InputField.InputFieldType.TEXT);
        this.inputField.setId(this.inputFieldDaoService.saveInputField(this.inputField));

        this.inputField1 = ModelFactory.getInputField();
        inputField1.setName("input 2");
        inputField1.setInputFieldType(InputField.InputFieldType.TEXT);
        this.inputField1.setId(this.inputFieldDaoService.saveInputField(this.inputField1));

        this.patternItem = ModelFactory.getBookingPatternItem();
        this.patternItem.setPayload(this.patternPayload);
        this.patternItem.setBooking(this.booking);
        this.patternItem.setId(this.patternItemDaoService.savePatternItem(this.patternItem));

        this.patternItem1 = ModelFactory.getBookingPatternItem();
        this.patternItem1.setPayload(this.patternPayload);
        this.patternItem1.setBooking(this.booking);
        this.patternItem1.setId(this.patternItemDaoService.savePatternItem(this.patternItem1));

    }

    @AfterEach
    void deleteBookingPatternObjects() {
        this.bookingDaoService.deleteBooking(this.booking);
        this.ledgerDaoService.deleteLedger(this.ledgerHave);
        this.ledgerDaoService.deleteLedger(this.ledgerShould);
        this.categoryDaoService.deleteCategory(this.category);
        this.patternPayloadDaoService.deleteBookingPayload(this.patternPayload);
        this.bookingInformationDaoService.deleteBookingInfo(this.bookingInformation);
        this.inputFieldDaoService.deleteInputField(this.inputField);
        this.inputFieldDaoService.deleteInputField(this.inputField1);
        this.patternItemDaoService.deletePatternItem(this.patternItem);
        this.patternItemDaoService.deletePatternItem(this.patternItem1);
    }

    @Test
    void writeBookingPatternTest() {
        BookingPattern pattern = ModelFactory.getBookingPattern();
        pattern.setBookingInformation(this.bookingInformation);
        pattern.setCategory(this.category);
        pattern.setExecutionDate(new Date());
        pattern.setExecutionDatePattern("date pattern");
        pattern.setName("testName");
        pattern.setPattern(false);
        List<InputField> inputFields = new ArrayList<>();
        inputFields.add(this.inputField);
        inputFields.add(this.inputField1);
        pattern.setInputFields(inputFields);
        List<BookingPatternItem> patternItems = new ArrayList<>();
        patternItems.add(this.patternItem);
        patternItems.add(this.patternItem1);
        pattern.setBookingPatternItems(patternItems);

        int patternId = this.patternDao.write(pattern);
        BookingPattern savedPattern = this.patternDao.read(patternId);
        assertEquals(pattern.getName(), savedPattern.getName());
        assertEquals(pattern.getBookingInformation().getValue(), savedPattern.getBookingInformation().getValue());
        assertEquals(pattern.getCategory().getName(), savedPattern.getCategory().getName());
        assertEquals(pattern.getInputFields().size(), savedPattern.getInputFields().size());
        assertEquals(pattern.getInputFields().get(0).getName(), savedPattern.getInputFields().get(0).getName());
        assertEquals(pattern.getBookingPatternItems().size(), savedPattern.getBookingPatternItems().size());
        assertEquals(pattern.getBookingPatternItems().get(0).getBooking().getValue(), savedPattern.getBookingPatternItems().get(0).getBooking().getValue());
        assertEquals(pattern.getBookingPatternItems().get(0).getPayload().getBookingPatternPayload().size(), savedPattern.getBookingPatternItems().get(0).getPayload().getBookingPatternPayload().size());
        this.patternDao.delete(savedPattern);
    }
}
