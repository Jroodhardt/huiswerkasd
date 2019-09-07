package dao;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import domain.Abonnee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TokenDAO {
    public IDatabaseConnection dbconnection = new DatabaseConnection();
    private static final Logger LOG = Logger.getLogger("com.library.Books");

    public boolean tokenCheck(String token){
        String selectTokenSQL = "SELECT token FROM User WHERE token = ?";

        boolean bestaandToken = false;
        try (
                Connection databaseC = dbconnection.getDC();
                PreparedStatement preparedStatementControleerToken = databaseC.prepareStatement(selectTokenSQL)
        ) {
            preparedStatementControleerToken.setString(1, token);
            ResultSet gebruikerSQL = preparedStatementControleerToken.executeQuery();

            if (gebruikerSQL.first()) {
                bestaandToken=true;
            }

        } catch (SQLException e1) {
            LOG.log(Level.SEVERE, "Uncaught exception", e1);
        }
        return bestaandToken;
    }
}
