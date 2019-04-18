package models.ledger;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import models.basic.BasicModel;

@JsonDeserialize(as=LedgerImpl.class)
public interface Ledger extends BasicModel {

    public int getId();

    public void setId(int id);

    public String getName();

    public void setName(String name);

    public int getLedgerNumber();

    public void setLedgerNumber(int ledgerNumber);

    public String getDescription();

    public void setDescription(String description);

    public double getValue();

    public void setValue(double value);

    public boolean isSubLedger();

    public void setSubLedger(boolean subLedger);
}
