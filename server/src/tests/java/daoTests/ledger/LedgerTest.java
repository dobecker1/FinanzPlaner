package daoTests.ledger;


import daoLayer.dao.LedgerDao;
import models.ledger.Ledger;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LedgerTest {

    private final LedgerDao ledgerDao = new LedgerDao();
    private Ledger ledger;

    @BeforeEach
    void createLedger() {
        this.ledger = new Ledger();
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
    void writeLedger() {

        this.ledgerDao.write(ledger);
        Ledger savedLedger = this.ledgerDao.findLedgerByNumber(123);
        assertEquals(ledger.getName(), savedLedger.getName());
        assertEquals(ledger.getLedgerNumber(), savedLedger.getLedgerNumber());
        assertEquals(ledger.getDescription(), savedLedger.getDescription());
        assertEquals(ledger.getValue(), ledger.getValue());


    }
}
