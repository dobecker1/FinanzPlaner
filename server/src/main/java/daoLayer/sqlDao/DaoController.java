package daoLayer.sqlDao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DaoController {

    private String driver = "org.apache.derby.jdbc.EmbeddedDriver";
    private String protocol = "jdbc:derby:";
    private static final DaoController daoController = new DaoController();

    protected Connection connection;

    DaoController() {
        this.initDBConnection();
    }

    public static DaoController getInstance() {
        return daoController;
    }

    public void initDBConnection() {
        try {
            Class.forName(driver).newInstance();
            connection = DriverManager.getConnection(this.protocol + "finance;create=true");
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Runtime.getRuntime().addShutdownHook(new Thread() {
            public void run() {
                try {
                    if (!connection.isClosed() && connection != null) {
                        connection.close();
                        if (connection.isClosed())
                            System.out.println("Connection to Database closed");
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void closeDBConnection() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createLedgerTable() {
        Statement statement = null;
        try {
            statement = this.connection.createStatement();
            String sql = "CREATE TABLE LEDGER (id INTEGER not NULL GENERATED ALWAYS AS IDENTITY, " +
                    "name VARCHAR(255), " +
                    "ledgerNumber INTEGER UNIQUE, " +
                    "description VARCHAR(255), " +
                    "subLedger BOOLEAN, " +
                    "value DOUBLE, " +
                    "PRIMARY KEY (id))";
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createBookingTable() {
        Statement statement = null;
        try {
            statement = this.connection.createStatement();
            String sql = "CREATE TABLE BOOKING (id INTEGER not NULL GENERATED ALWAYS AS IDENTITY, " +
                    "date DATE, " +
                    "referenceNumber VARCHAR(255), " +
                    "bookingDescription VARCHAR(255), " +
                    "ledgerShould INTEGER, " +
                    "subLedgerShould INTEGER, " +
                    "ledgerHave INTEGER, " +
                    "subLedgerHave INTEGER, " +
                    "value DOUBLE, " +
                    "referencePath VARCHAR(255), " +
                    "financialYear VARCHAR(255), " +
                    "PRIMARY KEY (id))";
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createCategoryTable() {
        Statement statement = null;
        try {
            statement = this.connection.createStatement();
            String sql = "CREATE TABLE CATEGORY (id INTEGER not NULL GENERATED ALWAYS AS IDENTITY, " +
                    "name VARCHAR(255), " +
                    "PRIMARY KEY (id))";
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createInputFieldTable() {
        Statement statement = null;
        try {
            statement = this.connection.createStatement();
            String sql = "CREATE TABLE INPUT_FIELD (id INTEGER not NULL GENERATED ALWAYS AS IDENTITY, " +
                    "name VARCHAR(255), " +
                    "type VARCHAR(255), " +
                    "PRIMARY KEY (id))";
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createBookingInfomrationTable() {
        Statement statement = null;
        try {
            statement = this.connection.createStatement();
            String sql = "CREATE TABLE BOOKINGINFORMATION (id INTEGER not NULL GENERATED ALWAYS AS IDENTITY, " +
                    "bookingDescription VARCHAR(255), " +
                    "ledgerShould INTEGER, " +
                    "subLedgerShould INTEGER, " +
                    "ledgerHave INTEGER, " +
                    "subLedgerHave INTEGER, " +
                    "value DOUBLE, " +
                    "PRIMARY KEY (id))";
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
