package daoLayer.sqlDao;

import factory.ModelFactory;
import models.ledger.Ledger;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Component("ledgerDao")
public class LedgerDao extends BasicDao{

    public static final String LEDGER_TABLE = "LEDGER";

    public int write(Ledger ledger) {
        int ledgerId = -1;

        try {
            PreparedStatement statement = super.controller.connection.
                    prepareStatement("INSERT INTO LEDGER(NAME, LEDGERNUMBER, DESCRIPTION, SUBLEDGER, VALUE) VALUES(?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, ledger.getName());
            statement.setInt(2, ledger.getLedgerNumber());
            statement.setString(3, ledger.getDescription());
            statement.setBoolean(4, ledger.isSubLedger());
            statement.setDouble(5, ledger.getValue());
            statement.executeUpdate();
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if(generatedKeys.next()) {
                ledgerId = generatedKeys.getInt(1);
            }
            generatedKeys.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ledgerId;
    }

    public Ledger read(int id) {
        String sql = "SELECT * FROM LEDGER WHERE id = ?";
        return this.findSingleLedger(sql, id);
    }

    public void delete(Ledger ledger) {
        super.delete(ledger, LEDGER_TABLE);
    }

    public Ledger findLedgerByLedgerNumber(int ledgerNumber) {
        String sql = "SELECT * FROM LEDGER WHERE ledgerNumber = ?";
        return this.findSingleLedger(sql, ledgerNumber);
    }

    private Ledger findSingleLedger(String sql, int value) {
        try {
            PreparedStatement statement = super.controller.connection
                    .prepareStatement(sql);
            statement.setInt(1, value);
            ResultSet result = statement.executeQuery();
            if(result.next()) {
                Ledger ledger = ModelFactory.getLedger();
                ledger.setId(result.getInt("id"));
                ledger.setLedgerNumber(result.getInt("ledgerNumber"));
                ledger.setName(result.getString("name"));
                ledger.setValue(result.getDouble("value"));
                ledger.setDescription(result.getString("description"));
                ledger.setSubLedger(result.getBoolean("subLedger"));
                result.close();
                return ledger;
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Fehler");
        }
    }

    public List<Ledger> findAllLedgers() {
        List<Ledger> ledgers = new ArrayList<>();
        try {
            PreparedStatement statement = super.controller.connection
                    .prepareStatement("SELECT * FROM LEDGER");
            ResultSet result = statement.executeQuery();
            while(result.next()) {
                Ledger ledger = ModelFactory.getLedger();
                ledger.setId(result.getInt("id"));
                ledger.setLedgerNumber(result.getInt("ledgerNumber"));
                ledger.setName(result.getString("name"));
                ledger.setValue(result.getDouble("value"));
                ledger.setDescription(result.getString("description"));
                ledger.setSubLedger(result.getBoolean("subLedger"));
                ledgers.add(ledger);
            }
            return ledgers;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Fehler");
        }
    }
}
