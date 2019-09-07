package dao;

import domain.Abonnee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AbonneeDAO implements ISubscriberDAO{
    public IDatabaseConnection dbconnection = new DatabaseConnection();
    private static final Logger LOG = Logger.getLogger("com.library.Books");

    public ArrayList<Abonnee> getAllSubscribers(String token) {

            String selectAllAbonneesSQL = "SELECT id,name,email FROM User WHERE token!=?";
            ArrayList<Abonnee> abonnees = new ArrayList<Abonnee>();

            try (
                    Connection databaseC = dbconnection.getDC();
                    PreparedStatement preparedStatementAllAbonnees = databaseC.prepareStatement(selectAllAbonneesSQL);
            ) {
                preparedStatementAllAbonnees.setString(1, token);
                ResultSet getAllSubscribersSQL = preparedStatementAllAbonnees.executeQuery();
                if (getAllSubscribersSQL.next()) {
                    Abonnee abonnee = new Abonnee();
                    abonnee.setId(getAllSubscribersSQL.getInt("id"));
                    abonnee.setName(getAllSubscribersSQL.getString("name"));
                    abonnee.setEmail(getAllSubscribersSQL.getString("email"));
                    abonnees.add(abonnee);
                }
            } catch (SQLException e2) {
                LOG.log(Level.SEVERE, "Uncaught exception", e2);
            }
            return abonnees;
        }
    }

