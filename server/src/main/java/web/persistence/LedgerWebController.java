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

    @PostMapping("/saveLedger")
    public Ledger saveLedger(@RequestBody Ledger ledger) {
        return this.ledgerService.saveLedger(ledger);
    }

    @GetMapping("/getAllLedgers")
    public List<Ledger> getAllLedgers() {
        return this.ledgerService.getAllLedgers();
    }

    @DeleteMapping("/deleteLedger")
    public String deleteLedger(@RequestParam (value="id") int id) {
        this.ledgerService.deleteLedger(id);
        return "OK";
    }
}
