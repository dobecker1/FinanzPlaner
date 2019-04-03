package models.patternBooking.impl;

import models.ledger.Ledger;
import models.ledger.LedgerImpl;
import models.patternBooking.interfaces.BookingInformation;

import javax.persistence.*;

@Entity
@Table(name = "BOOKING_INFORMATION")
public class BookingInformationImpl implements BookingInformation {

    private int id;

    private Ledger ledgerShould;
    private Ledger ledgerHave;
    private Ledger subLedgerShould;
    private Ledger subLedgerHave;

    private String bookingDescription;
    private double value;

    @Override
    @Id @GeneratedValue
    @Column(name = "id")
    public int getId() {
        return this.id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    @Override
    @OneToOne(fetch = FetchType.EAGER, targetEntity = LedgerImpl.class)
    @JoinColumn(name = "ledgerShould", nullable = false)
    public Ledger getLedgerShould() {
        return this.ledgerShould;
    }

    @Override
    public void setLedgerShould(Ledger ledger) {
        this.ledgerShould = ledger;
    }

    @Override
    @OneToOne(fetch = FetchType.EAGER, targetEntity = LedgerImpl.class)
    @JoinColumn(name = "ledgerHave", nullable = false)
    public Ledger getLedgerHave() {
        return this.ledgerHave;
    }

    @Override
    public void setLedgerHave(Ledger ledger) {
        this.ledgerHave = ledger;
    }

    @Override
    @OneToOne(fetch = FetchType.EAGER, targetEntity = LedgerImpl.class)
    @JoinColumn(name = "subLedgerShould")
    public Ledger getSubLedgerShould() {
        return this.subLedgerShould;
    }

    @Override
    public void setSubLedgerShould(Ledger ledger) {
        this.subLedgerShould = ledger;
    }

    @Override
    @OneToOne(fetch = FetchType.EAGER, targetEntity = LedgerImpl.class)
    @JoinColumn(name = "subLedgerHave")
    public Ledger getSubLedgerHave() {
        return this.subLedgerHave;
    }

    @Override
    public void setSubLedgerHave(Ledger ledger) {
        this.subLedgerHave = ledger;
    }

    @Override
    @Column(name = "bookingDescription")
    public String getBookingDescription() {
        return this.bookingDescription;
    }

    @Override
    public void setBookingDescription(String description) {
        this.bookingDescription = description;
    }

    @Override
    @Column(name = "value")
    public double getValue() {
        return this.value;
    }

    @Override
    public void setValue(double value) {
        this.value = value;
    }
}
