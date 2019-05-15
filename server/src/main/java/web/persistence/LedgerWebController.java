package web.persistence;

import daoLayer.services.LedgerService;
import daoLayer.services.exceptions.LedgerServiceException;
import factory.ServiceFactory;
import models.ledger.Ledger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class LedgerWebController {

    LedgerService ledgerService;

    public LedgerWebController() {
        this.ledgerService = ServiceFactory.getLedgerService();
    }

    @PostMapping("/ledgers")
    public int saveLedger(@RequestBody Ledger ledger) throws LedgerServiceException {
        return this.ledgerService.saveLedger(ledger);
    }

    @PutMapping("/ledgers")
    public boolean updateLedger(@RequestBody Ledger ledger) throws LedgerServiceException {
        return this.ledgerService.updateLedger(ledger);
    }

    @GetMapping("/ledgers")
    public List<Ledger> getAllLedgers() {
        return this.ledgerService.getAllLedgers();
    }

    @GetMapping("/ledgers/{subLedgers}")
    public List<Ledger> getLedgers(@PathVariable boolean subLedgers) {
        if(subLedgers) {
            return this.ledgerService.getSubLedgers();
        } else {
            return this.ledgerService.getLedgers();
        }
    }

    @DeleteMapping("/ledgers/{id}")
    public boolean deleteLedger(@PathVariable int id) {
        return this.ledgerService.deleteLedger(id);
    }
}
