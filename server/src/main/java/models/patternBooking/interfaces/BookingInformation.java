package models.patternBooking.interfaces;

import models.basic.BasicModel;
import models.ledger.Ledger;

public interface BookingInformation extends BasicModel {

    public int getId();

    public void setId(int id);

    public Ledger getLedgerShould();

    public void setLedgerShould(Ledger ledger);

    public Ledger getLedgerHave();

    public void setLedgerHave(Ledger ledger);

    public Ledger getSubLedgerShould();

    public void setSubLedgerShould(Ledger ledger);

    public Ledger getSubLedgerHave();

    public void setSubLedgerHave(Ledger ledger);

    public String getBookingDescription();

    public void setBookingDescription(String description);

    public double getValue();

    public void setValue(double value);
}
