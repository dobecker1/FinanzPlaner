package models.ledger;

import javax.persistence.*;

@Entity
@Table(name = "LEDGER")
public class Ledger {

    @Id @GeneratedValue
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "ledgerNumber", unique = true)
    private int ledgerNumber;

    @Column(name = "description")
    private String description;

    @Column(name = "value")
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
}
