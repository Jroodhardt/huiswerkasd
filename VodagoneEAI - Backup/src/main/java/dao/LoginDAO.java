package dao;

import domain.Abonnee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoginDAO implements ILoginDAO {
    public IDatabaseConnection dbconnection = new DatabaseConnection();
    private static final Logger LOG = Logger.getLogger("com.library.Books");



    public Abonnee checkUser(Abonnee abonnee) {

        String selectGebruikerSQL = "SELECT token,password,user FROM User WHERE user = ? AND password = ?";

        Abonnee succesGebruiker = null;
        try (
                Connection databaseC = dbconnection.getDC();
                PreparedStatement preparedStatementControleerGebruiker = databaseC.prepareStatement(selectGebruikerSQL)
        ) {
            preparedStatementControleerGebruiker.setString(1, abonnee.getUser());
            preparedStatementControleerGebruiker.setString(2, abonnee.getPassword());
            ResultSet gebruikerSQL = preparedStatementControleerGebruiker.executeQuery();

            if (gebruikerSQL.first()) {
                succesGebruiker = new Abonnee();
                succesGebruiker.setToken(gebruikerSQL.getString("token"));
                succesGebruiker.setUser(abonnee.getUser());
            }

        } catch (SQLException e1) {
            LOG.log(Level.SEVERE, "Uncaught exception", e1);
        }

        return succesGebruiker;
    }
}
