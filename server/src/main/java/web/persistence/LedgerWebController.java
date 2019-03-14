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
    public String saveLedger(@RequestBody Ledger ledger) {
        this.ledgerService.saveLedger(ledger);
        return "OK";
    }

    @GetMapping("/getAllLedgers")
    public List<Ledger> getAllLedgers() {
        return this.ledgerService.getAllLedgers();
    }
}
