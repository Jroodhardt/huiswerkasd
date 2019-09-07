package daolaag;

import dao.IDatabaseConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnectionMock implements IDatabaseConnection {

    private final String DB_DRIVER = "org.h2.Driver";
    private final String DB_CONNECTION = "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1;";
    private final String DB_USER = "";
    private final String DB_PASSWORD = "";


    public Connection getDC() {
        Connection dbConnection = null;
        try {
            Class.forName(DB_DRIVER);
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        try {
            dbConnection = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);
            return dbConnection;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return dbConnection;
    }
}