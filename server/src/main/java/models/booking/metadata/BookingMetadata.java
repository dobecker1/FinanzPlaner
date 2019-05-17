package models.booking.metadata;

import java.time.LocalDate;

public class BookingMetadata {

    private int id;
    private LocalDate date;
    private String referenceNumber;
    private String bookingDescription;
    private int ledgerShould;
    private int ledgerHave;
    private int subLedgerShould;
    private int subLedgerHave;
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

    public int getLedgerShould() {
        return ledgerShould;
    }

    public void setLedgerShould(int ledgerShould) {
        this.ledgerShould = ledgerShould;
    }

    public int getLedgerHave() {
        return ledgerHave;
    }

    public void setLedgerHave(int ledgerHave) {
        this.ledgerHave = ledgerHave;
    }

    public int getSubLedgerShould() {
        return subLedgerShould;
    }

    public void setSubLedgerShould(int subLedgerShould) {
        this.subLedgerShould = subLedgerShould;
    }

    public int getSubLedgerHave() {
        return subLedgerHave;
    }

    public void setSubLedgerHave(int subLedgerHave) {
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
