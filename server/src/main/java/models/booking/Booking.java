package models.booking;

import models.basic.BasicModel;
import models.ledger.Ledger;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "BOOKING")
public class Booking implements BasicModel {

    @Id @GeneratedValue
    @Column(name = "id")
    private int id;

    @Column(name = "date")
    private Date date;

    @Column(name = "referenceNumber")
    private String referenceNumber;

    @Column(name = "bookingDescription")
    private String bookingDescription;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ledgerShould")
    private Ledger ledgerShould;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ledgerHave")
    private Ledger ledgerHave;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "subLedgerShould")
    private Ledger subLedgerShould;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "subLedgerHave")
    private Ledger subLedgerHave;

    @Column(name = "value")
    private double value;

    @Column(name = "referencePath")
    private String referencePath;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getReferenceNumber() {
        return referenceNumber;
    }

    public void setReferenceNumber(String referenceNumber) {
        this.referenceNumber = referenceNumber;
    }

    public String getBookingDescription() {
        return bookingDescription;
    }

    public void setBookingDescription(String bookingDescription) {
        this.bookingDescription = bookingDescription;
    }

    public Ledger getLedgerShould() {
        return ledgerShould;
    }

    public void setLedgerShould(Ledger ledgerShould) {
        this.ledgerShould = ledgerShould;
    }

    public Ledger getLedgerHave() {
        return ledgerHave;
    }

    public void setLedgerHave(Ledger ledgerHave) {
        this.ledgerHave = ledgerHave;
    }

    public Ledger getSubLedgerShould() {
        return subLedgerShould;
    }

    public void setSubLedgerShould(Ledger subLedgerShould) {
        this.subLedgerShould = subLedgerShould;
    }

    public Ledger getSubLedgerHave() {
        return subLedgerHave;
    }

    public void setSubLedgerHave(Ledger subLedgerHave) {
        this.subLedgerHave = subLedgerHave;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public String getReferencePath() {
        return referencePath;
    }

    public void setReferencePath(String referencePath) {
        this.referencePath = referencePath;
    }
}
