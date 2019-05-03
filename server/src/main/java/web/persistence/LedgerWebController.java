package web.persistence;

import daoLayer.services.LedgerService;
import models.ledger.Ledger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class LedgerWebController {

    @Autowired
    LedgerService ledgerService;

    public LedgerWebController() {
    }

    @PostMapping("/ledgers")
    public Ledger saveLedger(@RequestBody Ledger ledger) {
        return this.ledgerService.saveLedger(ledger);
    }

    @PutMapping("/ledgers")
    public boolean updateLedger(@RequestBody Ledger ledger) {
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
