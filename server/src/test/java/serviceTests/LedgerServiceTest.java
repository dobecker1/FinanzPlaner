package serviceTests;

import daoLayer.services.LedgerService;
import factory.ModelFactory;
import factory.ServiceFactory;
import models.ledger.Ledger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LedgerServiceTest {

    private LedgerService ledgerService = ServiceFactory.getLedgerService();
    private Ledger ledger;

    @BeforeEach
    void createLedger() {
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
    void updateLedgerTest() {
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
    void changeLedgerValuePositiveTest() {
        this.ledgerService.changeLedgerValue(this.ledger, 300);
        Ledger savedLedger = this.ledgerService.getLedgerById(this.ledger.getId());
        assertEquals(500, savedLedger.getValue());

    }

    @Test
    void changeLedgerValueNegativeTest() {
        this.ledgerService.changeLedgerValue(this.ledger, -120);
        Ledger savedLedger = this.ledgerService.getLedgerById(this.ledger.getId());
        assertEquals(80, savedLedger.getValue());
    }

}
