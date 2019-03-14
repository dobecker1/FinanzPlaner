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

    public void saveLedger(Ledger ledger) {
        this.ledgerDao.saveLedger(ledger);
    }

    public List<Ledger> getAllLedgers() {
        return this.ledgerDao.findAllLedgers();
    }
}
