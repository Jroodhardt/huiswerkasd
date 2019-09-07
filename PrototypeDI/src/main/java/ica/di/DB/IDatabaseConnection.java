package ica.di.DB;

import java.sql.Connection;
import java.sql.SQLException;

public interface IDatabaseConnection {
    public Connection getDC() throws SQLException;
}
