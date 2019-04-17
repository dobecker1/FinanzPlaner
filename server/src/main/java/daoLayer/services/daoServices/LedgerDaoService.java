package daoLayer.services.daoServices;

import daoLayer.sqlDao.LedgerDao;
import factory.DaoFactory;
import factory.ModelFactory;
import models.ledger.Ledger;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("ledgerDaoService")
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

    public void deleteLedger(int id) {
        this.ledgerDao.delete(id);
    }

    public Ledger findLedgerById(int id) {
        return this.ledgerDao.read(id);
    }

    public void updateLedger(Ledger newLedger) {
        this.ledgerDao.updateLedger(newLedger);
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
}
