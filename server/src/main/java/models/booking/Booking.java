package models.booking;

import models.basic.BasicModel;
import models.ledger.Ledger;

import java.util.Date;

public interface Booking extends BasicModel {

    public int getId();

    public void setId(int id);

    public Date getDate();

    public void setDate(Date date);

    public String getReferenceNumber();

    public void setReferenceNumber(String referenceNumber);

    public String getBookingDescription();

    public void setBookingDescription(String bookingDescription);

    public Ledger getLedgerShould();

    public void setLedgerShould(Ledger ledgerShould);

    public Ledger getLedgerHave();

    public void setLedgerHave(Ledger ledgerHave);

    public Ledger getSubLedgerShould();

    public void setSubLedgerShould(Ledger subLedgerShould);

    public Ledger getSubLedgerHave();

    public void setSubLedgerHave(Ledger subLedgerHave);

    public double getValue();

    public void setValue(double value);

    public String getReferencePath();

    public void setReferencePath(String referencePath);

    public String getFinancialYear();

    public void setFinancialYear(String financialYear);
}
