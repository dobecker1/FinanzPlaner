package models.booking;

import models.ledger.Ledger;

import java.time.LocalDate;

public class BookingImpl implements Booking {

    private int id;

    private LocalDate date;

    private String referenceNumber;

    private String bookingDescription;

    private Ledger ledgerShould;

    private Ledger ledgerHave;

    private Ledger subLedgerShould;

    private Ledger subLedgerHave;

    private double value;

    private String referencePath;

    private String financialYear;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
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

    public String getFinancialYear() {
        return financialYear;
    }

    public void setFinancialYear(String financialYear) {
        this.financialYear = financialYear;
    }
}
