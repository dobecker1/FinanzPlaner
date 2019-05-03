package daoLayer.services;

import daoLayer.services.daoServices.LedgerDaoService;
import models.ledger.Ledger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("ledgerService")
public class LedgerService {

    @Autowired
    private LedgerDaoService ledgerDaoService;

    public Ledger saveLedger(Ledger ledger) {
        return this.ledgerDaoService.findLedgerById(this.ledgerDaoService.saveLedger(ledger));
    }

    public boolean updateLedger(Ledger ledger) {
        return this.ledgerDaoService.updateLedger(ledger);
    }

    public boolean deleteLedger(int id) {
        return this.ledgerDaoService.deleteLedger(id);
    }

    public void deleteLedger(Ledger ledger) {
        this.ledgerDaoService.deleteLedger(ledger);
    }

    public List<Ledger> getAllLedgers() {
        return this.ledgerDaoService.findAllLedgers();
    }

    public List<Ledger> getLedgers() {
        return this.ledgerDaoService.findLedgers();
    }

    public List<Ledger> getSubLedgers() {
        return this.ledgerDaoService.findSubLedgers();
    }

    public Ledger getLedgerByNumber(int ledgerNumber) {
        return this.ledgerDaoService.findLedgerByLedgerNumber(ledgerNumber);
    }

    public void changeLedgerValue(Ledger ledger, double sum) {
        ledger.setValue(ledger.getValue() + sum);
        this.ledgerDaoService.updateLedger(ledger);
    }
}
