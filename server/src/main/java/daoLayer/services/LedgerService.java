package daoLayer.services;

import daoLayer.services.daoServices.LedgerDaoService;
import daoLayer.services.exceptions.LedgerServiceException;
import factory.ServiceFactory;
import models.ledger.Ledger;

import java.util.List;

public class LedgerService {

    private LedgerDaoService ledgerDaoService;

    public LedgerService() {
        this.ledgerDaoService = ServiceFactory.getLedgerDaoService();
    }

    public int saveLedger(Ledger ledger) throws LedgerServiceException {
        return this.ledgerDaoService.saveLedger(ledger);
    }

    public boolean updateLedger(Ledger ledger) throws LedgerServiceException {
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

    public Ledger getLedgerById(int id) {
        return this.ledgerDaoService.findLedgerById(id);
    }

    public void changeLedgerValue(Ledger ledger, double sum) throws LedgerServiceException {
        ledger.setValue(ledger.getValue() + sum);
        this.ledgerDaoService.updateLedger(ledger);
    }
}
