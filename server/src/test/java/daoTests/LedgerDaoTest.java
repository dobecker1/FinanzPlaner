package daoTests;

import daoLayer.sqlDao.LedgerDao;
import factory.ModelFactory;
import models.ledger.Ledger;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.After;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class LedgerDaoTest {

    //private final LedgerDao ledgerDao = new LedgerDao();
    private final LedgerDao ledgerDao = new LedgerDao();
    private Ledger ledger;

    @BeforeEach
    void createLedger() {
        this.ledger = ModelFactory.getLedger();
        this.ledger.setLedgerNumber(123);
        this.ledger.setName("TestLedger");
        this.ledger.setDescription("Test Description");
        this.ledger.setValue(0);
        this.ledger.setSubLedger(false);
    }

    @AfterEach
    void deleteLedger() {
        Ledger savedLedger = this.ledgerDao.read(this.ledger.getId());
        this.ledgerDao.delete(savedLedger);
    }

    @Test
    void writeLedgerTest() {
       this.ledger.setId(this.ledgerDao.write(this.ledger));
        Ledger savedLedger = this.ledgerDao.findLedgerByLedgerNumber(123);
        assertEquals(ledger.getName(), savedLedger.getName());
        assertEquals(ledger.getLedgerNumber(), savedLedger.getLedgerNumber());
        assertEquals(ledger.getDescription(), savedLedger.getDescription());
        assertEquals(ledger.getValue(), savedLedger.getValue());
        assertEquals(ledger.isSubLedger(), savedLedger.isSubLedger());
    }

    @Test
    void findAllLedgersTest() {
        this.ledger.setId(this.ledgerDao.write(ledger));
        Ledger secondLedger = ModelFactory.getLedger();
        secondLedger.setLedgerNumber(456);
        secondLedger.setName("SecondLedger");
        secondLedger.setValue(20);
        secondLedger.setDescription("Second Ledger Description");
        this.ledgerDao.write(secondLedger);
        List<Ledger> ledgers = this.ledgerDao.findAllLedgers();
        assertEquals(2, ledgers.size());
        Ledger savedLedger = this.ledgerDao.findLedgerByLedgerNumber(secondLedger.getLedgerNumber());
        this.ledgerDao.delete(savedLedger);
   }

   @Test
   void updateLedgerTest() {
        this.ledger.setId(this.ledgerDao.write(this.ledger));
        int number = this.ledger.getLedgerNumber();
        String descr = this.ledger.getDescription();
        double value = this.ledger.getValue();
        this.ledger.setLedgerNumber(111);
        this.ledger.setDescription("changed description");
        this.ledger.setValue(value + 50);
        this.ledgerDao.updateLedger(this.ledger);
        Ledger savedLedger = this.ledgerDao.read(this.ledger.getId());
        assertNotEquals(number, savedLedger.getLedgerNumber());
        assertNotEquals(descr, savedLedger.getDescription());
        assertNotEquals(value, savedLedger.getValue());
        assertEquals(this.ledger.getName(), savedLedger.getName());
   }

}
