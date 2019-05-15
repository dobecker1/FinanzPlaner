package serviceTests;

import daoLayer.services.LedgerService;
import daoLayer.services.daoServices.LedgerDaoService;
import daoLayer.services.exceptions.LedgerServiceException;
import factory.ModelFactory;
import factory.ServiceFactory;
import models.ledger.Ledger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class LedgerServiceTest {

    private LedgerService ledgerService = ServiceFactory.getLedgerService();
    private LedgerDaoService ledgerDaoService = ServiceFactory.getLedgerDaoService();
    private Ledger ledger;

    @BeforeEach
    void createLedger() throws LedgerServiceException {
        this.ledger = ModelFactory.getLedger();
        this.ledger.setLedgerNumber(123);
        this.ledger.setName("LedgerName");
        this.ledger.setDescription("Ledger Description");
        this.ledger.setValue(200);
        this.ledger.setSubLedger(false);
        this.ledger.setId(this.ledgerService.saveLedger(ledger));
    }

    @AfterEach
    void deleteLedger() {
        this.ledgerService.deleteLedger(this.ledger);
    }

    @Test
    void saveLedgerTest() {
        Ledger savedLedger = this.ledgerService.getLedgerById(this.ledger.getId());
        assertEquals(this.ledger.getLedgerNumber(), savedLedger.getLedgerNumber());
        assertEquals(this.ledger.getValue(), savedLedger.getValue());
        assertEquals(this.ledger.getDescription(), savedLedger.getDescription());
        assertEquals(this.ledger.getName(), savedLedger.getName());
    }

    @Test
    void saveLedgerWithDuplicateLedgerNumberTest() {
        Ledger duplicateLedger = ModelFactory.getLedger();
        duplicateLedger.setLedgerNumber(123);
        duplicateLedger.setName("Duplicate");
        duplicateLedger.setValue(0);
        try {
            this.ledgerDaoService.saveLedger(duplicateLedger);
            fail("Exception expected");
        } catch (LedgerServiceException e) {
            assertEquals("Kontonummer existiert bereits: 123", e.getMessage());
        }
    }

    @Test
    void updateLedgerTest() throws LedgerServiceException {
        this.ledger.setName("Updated Name");
        this.ledger.setDescription("Updated Description");
        this.ledger.setLedgerNumber(456);
        this.ledger.setValue(150);
        this.ledgerService.updateLedger(this.ledger);
        Ledger updatedLedger = this.ledgerService.getLedgerById(this.ledger.getId());
        assertEquals("Updated Name", updatedLedger.getName());
        assertEquals("Updated Description", updatedLedger.getDescription());
        assertEquals(456, updatedLedger.getLedgerNumber());
        assertEquals(150, updatedLedger.getValue());
    }

    @Test
    void updateLedgerWithSameNumberTest() throws LedgerServiceException {
        Ledger newLedger = ModelFactory.getLedger();
        newLedger.setLedgerNumber(456);
        newLedger.setName("Test");
        newLedger.setId(this.ledgerService.saveLedger(newLedger));
        this.ledger.setName("Updated Name");
        this.ledger.setDescription("Updated Description");
        this.ledger.setLedgerNumber(456);
        this.ledger.setValue(150);
        try {
            this.ledgerService.updateLedger(this.ledger);
            fail("Exception expected");
        } catch (LedgerServiceException e) {
            assertEquals("Kontonummer existiert bereits: 456", e.getMessage());
        }
        this.ledgerService.deleteLedger(newLedger);
    }

    @Test
    void changeLedgerValuePositiveTest() throws LedgerServiceException {
        this.ledgerService.changeLedgerValue(this.ledger, 300);
        Ledger savedLedger = this.ledgerService.getLedgerById(this.ledger.getId());
        assertEquals(500, savedLedger.getValue());
    }

    @Test
    void changeLedgerValueNegativeTest() throws LedgerServiceException {
        this.ledgerService.changeLedgerValue(this.ledger, -120);
        Ledger savedLedger = this.ledgerService.getLedgerById(this.ledger.getId());
        assertEquals(80, savedLedger.getValue());
    }

    @Test
    void checkIfLedgerNumberAlreadyExistsTest() {
        assertEquals(true, this.ledgerDaoService.checkIfLedgerNumberAlreadyExists(123));
        this.ledgerService.deleteLedger(this.ledger);
        assertEquals(false, this.ledgerDaoService.checkIfLedgerNumberAlreadyExists(123));
    }

}
