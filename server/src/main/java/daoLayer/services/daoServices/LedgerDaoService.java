package daoLayer.services.daoServices;

import daoLayer.services.exceptions.LedgerServiceException;
import daoLayer.sqlDao.LedgerDao;
import factory.DaoFactory;
import factory.ModelFactory;
import models.ledger.Ledger;
import org.springframework.stereotype.Service;

import java.util.List;

public class LedgerDaoService {

    private LedgerDao ledgerDao;

    public LedgerDaoService() {
        this.ledgerDao = DaoFactory.getLedgerDao();
    }

    public int saveLedger(Ledger ledger) throws LedgerServiceException {
        if(!checkIfLedgerNumberAlreadyExists(ledger.getLedgerNumber())) {
            return this.ledgerDao.write(ledger);
        } else {
            throw new LedgerServiceException("Kontonummer existiert bereits: " + ledger.getLedgerNumber());
        }

    }

    public void deleteLedger(Ledger ledger)  {
        this.ledgerDao.delete(ledger);
    }

    public boolean deleteLedger(int id) {
        return this.ledgerDao.delete(id);
    }

    public Ledger findLedgerById(int id) {
        return this.ledgerDao.read(id);
    }

    public boolean updateLedger(Ledger newLedger) throws LedgerServiceException {
        if(checkChangedLedgerNumberIsValid(newLedger)) {
            return this.ledgerDao.updateLedger(newLedger);
        } else {
            throw new LedgerServiceException(("Kontonummer existiert bereits: " + newLedger.getLedgerNumber()));
        }

    }

    public Ledger findLedgerByLedgerNumber(int ledgerNumber) {
        return this.ledgerDao.findLedgerByLedgerNumber(ledgerNumber);
    }

    public List<Ledger> findAllLedgers() {
        return this.ledgerDao.findAllLedgers();
    }

    public List<Ledger> findLedgers() {
        return this.ledgerDao.findLedgers();
    }

    public List<Ledger> findSubLedgers() {
        return this.ledgerDao.findSubLedgers();
    }

    public boolean checkIfLedgerNumberAlreadyExists(int ledgerNumber) {
        Ledger ledger = this.ledgerDao.findLedgerByLedgerNumber(ledgerNumber);
        if(ledger != null) {
            return true;
        } else {
            return false;
        }
    }

    private boolean checkChangedLedgerNumberIsValid(Ledger ledger) {
        Ledger oldLedger = this.findLedgerById(ledger.getId());
        if(ledger.getLedgerNumber() != oldLedger.getLedgerNumber()) {
            return !this.checkIfLedgerNumberAlreadyExists(ledger.getLedgerNumber());
        } else {
            return true;
        }
    }
}
