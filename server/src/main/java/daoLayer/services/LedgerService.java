package daoLayer.services;

import daoLayer.dao.LedgerDao;
import models.ledger.Ledger;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("ledgerService")
public class LedgerService {

    private LedgerDao ledgerDao;

    public LedgerService() {
        ledgerDao = new LedgerDao();
    }

    public Ledger saveLedger(Ledger ledger) {
        this.ledgerDao.write(ledger);
        return this.ledgerDao.findLedgerByNumber(ledger.getLedgerNumber());
    }

    public List<Ledger> getAllLedgers() {
        return this.ledgerDao.findAllLedgers();
    }

    public Ledger getLedgerByNumber(int ledgerNumber) {
        return this.ledgerDao.findLedgerByNumber(ledgerNumber);
    }
}
