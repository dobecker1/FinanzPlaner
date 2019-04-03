package models.booking;

import models.basic.BasicModel;
import models.ledger.Ledger;
import models.ledger.LedgerImpl;

import javax.persistence.*;
import java.awt.print.Book;
import java.util.Date;

@Entity
@Table(name = "BOOKING")
public class BookingImpl implements Booking {

    private int id;

    private Date date;

    private String referenceNumber;

    private String bookingDescription;

    private Ledger ledgerShould;

    private Ledger ledgerHave;

    private Ledger subLedgerShould;

    private Ledger subLedgerHave;

    private double value;

    private String referencePath;

    private String financialYear;

    @Id @GeneratedValue
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Column(name = "referenceNumber", nullable = false)
    public String getReferenceNumber() {
        return referenceNumber;
    }

    public void setReferenceNumber(String referenceNumber) {
        this.referenceNumber = referenceNumber;
    }

    @Column(name = "bookingDescription")
    public String getBookingDescription() {
        return bookingDescription;
    }

    public void setBookingDescription(String bookingDescription) {
        this.bookingDescription = bookingDescription;
    }

    @OneToOne(fetch = FetchType.EAGER, targetEntity = LedgerImpl.class)
    @JoinColumn(name = "ledgerShould", nullable = false)
    public Ledger getLedgerShould() {
        return ledgerShould;
    }

    public void setLedgerShould(Ledger ledgerShould) {
        this.ledgerShould = ledgerShould;
    }

    @OneToOne(fetch = FetchType.EAGER, targetEntity = LedgerImpl.class)
    @JoinColumn(name = "ledgerHave", nullable = false)
    public Ledger getLedgerHave() {
        return ledgerHave;
    }

    public void setLedgerHave(Ledger ledgerHave) {
        this.ledgerHave = ledgerHave;
    }

    @OneToOne(fetch = FetchType.EAGER, targetEntity = LedgerImpl.class)
    @JoinColumn(name = "subLedgerShould")
    public Ledger getSubLedgerShould() {
        return subLedgerShould;
    }

    public void setSubLedgerShould(Ledger subLedgerShould) {
        this.subLedgerShould = subLedgerShould;
    }

    @OneToOne(fetch = FetchType.EAGER, targetEntity = LedgerImpl.class)
    @JoinColumn(name = "subLedgerHave")
    public Ledger getSubLedgerHave() {
        return subLedgerHave;
    }

    public void setSubLedgerHave(Ledger subLedgerHave) {
        this.subLedgerHave = subLedgerHave;
    }

    @Column(name = "value", nullable = false)
    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    @Column(name = "referencePath", nullable = false)
    public String getReferencePath() {
        return referencePath;
    }

    public void setReferencePath(String referencePath) {
        this.referencePath = referencePath;
    }

    @Column(name = "financialYear")
    public String getFinancialYear() {
        return financialYear;
    }

    public void setFinancialYear(String financialYear) {
        this.financialYear = financialYear;
    }
}
