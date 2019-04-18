package models.ledger;

public class LedgerImpl implements Ledger {

    private int id;

    private String name;

    private int ledgerNumber;

    private String description;

    private boolean subLedger;

    private double value;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLedgerNumber() {
        return ledgerNumber;
    }

    public void setLedgerNumber(int ledgerNumber) {
        this.ledgerNumber = ledgerNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public boolean isSubLedger() {
        return subLedger;
    }

    public void setSubLedger(boolean subLedger) {
        this.subLedger = subLedger;
    }
}
