package daoLayer.services;

import daoLayer.sqlDao.LedgerDao;
import factory.DaoFactory;
import models.ledger.Ledger;

public class LedgerDaoService {

    private LedgerDao ledgerDao;

    public LedgerDaoService() {
        this.ledgerDao = DaoFactory.getLedgerDao();
    }

    public int saveLedger(Ledger ledger) {
        return this.ledgerDao.write(ledger);
    }

    public void deleteLedger(Ledger ledger)  {
        this.ledgerDao.delete(ledger);
    }

    public Ledger findLedgerById(int id) {
        return this.ledgerDao.read(id);
    }

    public Ledger findLedgerByLedgerNumber(int ledgerNumber) {
        return this.ledgerDao.findLedgerByLedgerNumber(ledgerNumber);
    }
}
