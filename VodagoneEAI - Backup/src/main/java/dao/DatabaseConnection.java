package dao;


import dao.util.DatabaseProperties;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DatabaseConnection implements IDatabaseConnection{

    private Connection con;
    private static final Logger LOG = Logger.getLogger("com.library.Books");

    DatabaseConnection() {
        // Deze constructor is nodig voor het maken van Databaseconnection objecten
    }

    public Connection getDC() throws SQLException, NullPointerException {

        if (con == null) {
            try {
                DatabaseProperties dp = new DatabaseProperties();
                Class.forName(dp.getDriver());
                con = DriverManager.getConnection(dp.getConnectionString());
            } catch (Exception e) {
                LOG.log(Level.SEVERE, "Uncaught exception", e);
            }
        }
        return con;
    }
}