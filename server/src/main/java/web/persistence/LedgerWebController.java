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

    @GetMapping("/ledgers")
    public List<Ledger> getAllLedgers() {
        return this.ledgerService.getAllLedgers();
    }

    @DeleteMapping("/ledgers/{id}")
    public String deleteLedger(@PathVariable int id) {
        this.ledgerService.deleteLedger(id);
        return "OK";
    }
}
