package daoTests;

import daoLayer.dao.LedgerDao;
import factory.ModelFactory;
import models.ledger.Ledger;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.After;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class LedgerTest {

    private final LedgerDao ledgerDao = new LedgerDao();
    private Ledger ledger;

    @BeforeEach
    void createLedger() {
        this.ledger = ModelFactory.getLedger();
        this.ledger.setLedgerNumber(123);
        this.ledger.setName("TestLedger");
        this.ledger.setDescription("Test Description");
        this.ledger.setValue(0);
    }

    @AfterEach
    void deleteLedger() {
        Ledger savedLedger = this.ledgerDao.findLedgerByNumber(this.ledger.getLedgerNumber());
        this.ledgerDao.deleteLedger(savedLedger.getId());
    }

    @Test
    void writeLedgerTest() {
        this.ledgerDao.write(ledger);
        Ledger savedLedger = this.ledgerDao.findLedgerByNumber(123);
        assertEquals(ledger.getName(), savedLedger.getName());
        assertEquals(ledger.getLedgerNumber(), savedLedger.getLedgerNumber());
        assertEquals(ledger.getDescription(), savedLedger.getDescription());
        assertEquals(ledger.getValue(), ledger.getValue());
    }

    @Test
    void findAllLedgersTest() {
        this.ledgerDao.write(ledger);
        Ledger secondLedger = ModelFactory.getLedger();
        secondLedger.setLedgerNumber(456);
        secondLedger.setName("SecondLedger");
        secondLedger.setValue(20);
        secondLedger.setDescription("Second Ledger Description");
        this.ledgerDao.write(secondLedger);
        List<Ledger> ledgers = this.ledgerDao.findAllLedgers();
        assertEquals(2, ledgers.size());
        this.ledgerDao.deleteLedger(secondLedger.getId());
   }

}
