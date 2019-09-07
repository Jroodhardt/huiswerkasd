package dao;


import com.mchange.v2.c3p0.ComboPooledDataSource;
import dao.util.DatabaseProperties;

import javax.sql.ConnectionPoolDataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DatabaseConnection implements IDatabaseConnection{
    private ComboPooledDataSource cpds;
    private Connection con;
    private static final Logger LOG = Logger.getLogger("com.library.Books");

    DatabaseConnection() {
    }

    public Connection getDC() throws SQLException, NullPointerException {
        if (con == null) {
            try {
                DatabaseProperties dp = new DatabaseProperties();
                cpds = new ComboPooledDataSource();
                cpds.setDriverClass( dp.getDriver() ); //loads the jdbc driver
                cpds.setJdbcUrl( dp.getConnectionString() );
                cpds.setUser(dp.getUsername());
                cpds.setPassword(dp.getPassword());
                cpds.setMinPoolSize(1);
                cpds.setAcquireIncrement(5);
                cpds.setMaxPoolSize(20);
                cpds.setMaxStatements(180);

            } catch (Exception e) {
                LOG.log(Level.SEVERE, "Uncaught exception", e);
            }
        }
        return cpds.getConnection();
    }
}