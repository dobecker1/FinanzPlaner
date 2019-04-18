package models.patternBooking.impl;

import models.ledger.Ledger;
import models.patternBooking.interfaces.BookingInformation;

public class BookingInformationImpl implements BookingInformation {

    private int id;

    private Ledger ledgerShould;
    private Ledger ledgerHave;
    private Ledger subLedgerShould;
    private Ledger subLedgerHave;

    private String bookingDescription;
    private double value;

    @Override
    public int getId() {
        return this.id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public Ledger getLedgerShould() {
        return this.ledgerShould;
    }

    @Override
    public void setLedgerShould(Ledger ledger) {
        this.ledgerShould = ledger;
    }

    @Override
    public Ledger getLedgerHave() {
        return this.ledgerHave;
    }

    @Override
    public void setLedgerHave(Ledger ledger) {
        this.ledgerHave = ledger;
    }

    @Override
    public Ledger getSubLedgerShould() {
        return this.subLedgerShould;
    }

    @Override
    public void setSubLedgerShould(Ledger ledger) {
        this.subLedgerShould = ledger;
    }

    @Override
    public Ledger getSubLedgerHave() {
        return this.subLedgerHave;
    }

    @Override
    public void setSubLedgerHave(Ledger ledger) {
        this.subLedgerHave = ledger;
    }

    @Override
    public String getBookingDescription() {
        return this.bookingDescription;
    }

    @Override
    public void setBookingDescription(String description) {
        this.bookingDescription = description;
    }

    @Override
    public double getValue() {
        return this.value;
    }

    @Override
    public void setValue(double value) {
        this.value = value;
    }
}
