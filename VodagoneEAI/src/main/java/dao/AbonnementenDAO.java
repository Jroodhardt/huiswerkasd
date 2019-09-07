package dao;

import dao.transfer.AddAbonnementDTO;
import dao.transfer.SearchAbonnementDTO;
import domain.Abonnementen;
import domain.Abonnement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AbonnementenDAO implements ISubscriptionDAO {
    public IDatabaseConnection dbconnection = new DatabaseConnection();
    private Logger LOG;
    private String exception = "Uncaught exception";


    public Abonnementen getAbonnementen(String token) {

        Abonnementen abonnementen = null;
        float totalPrize = 0;

        String selectSubscriptionSQL = "SELECT id,aanbieder,dienst,prijs FROM UserSubscription WHERE token = ?";

        try (
                Connection databaseC = dbconnection.getDC();
                PreparedStatement preparedStatementSelectSubscription = databaseC.prepareStatement(selectSubscriptionSQL);
        ) {
            preparedStatementSelectSubscription.setString(1, token);
            ResultSet subscriptionSQL = preparedStatementSelectSubscription.executeQuery();
            abonnementen = new Abonnementen();
            while (subscriptionSQL.next()) {
                Abonnement abonnement = new Abonnement();
                abonnement.setId(subscriptionSQL.getInt("id"));
                abonnement.setAanbieder(subscriptionSQL.getString("aanbieder"));
                abonnement.setDienst(subscriptionSQL.getString("dienst"));
                abonnement.setPrijs(subscriptionSQL.getFloat("prijs"));
                abonnementen.addAbonnement(abonnement);
            }
        } catch (SQLException e2) {
            LOG.log(Level.SEVERE, "Uncaught exception", e2);
        }
        return abonnementen;
    }

    public boolean addAbonnement(SearchAbonnementDTO searchAbonnementDTO, String token) {

        boolean succes=false;
        AddAbonnementDTO addAbonnementDTO = getAbonnement(searchAbonnementDTO.getId());
        String addSubscriptionSQL = "INSERT INTO UserSubscription(token,id,aanbieder,dienst,prijs,verdubbeling,deelbaar,status) VALUES(?,?,?,?,?,?,?,?)";
        try (
                Connection databaseC = dbconnection.getDC();
                PreparedStatement preparedStatementAddSubscription = databaseC.prepareStatement(addSubscriptionSQL);
        ) {
            preparedStatementAddSubscription.setString(1, token);
            preparedStatementAddSubscription.setInt(2, searchAbonnementDTO.getId());
            preparedStatementAddSubscription.setString(3, searchAbonnementDTO.getAanbieder());
            preparedStatementAddSubscription.setString(4, searchAbonnementDTO.getDienst());
            preparedStatementAddSubscription.setFloat(5, addAbonnementDTO.getPrijs());
            preparedStatementAddSubscription.setString(6, addAbonnementDTO.getVerdubbeling());
            preparedStatementAddSubscription.setBoolean(7, addAbonnementDTO.isDeelbaar());
            preparedStatementAddSubscription.setString(8,addAbonnementDTO.getStatus());
            preparedStatementAddSubscription.executeUpdate();
            succes=true;
        } catch (SQLException e3) {
            LOG.log(Level.SEVERE, "Uncaught exception", e3);
        }
        return succes;
    }

    public AddAbonnementDTO getAbonnement(int id) {

        AddAbonnementDTO abonnement = new AddAbonnementDTO();
        String selectSubsubscriptionSQL = "SELECT prijs,verdubbeling,deelbaar FROM Subscription WHERE  id=? AND aanbieder=? AND";

        try (
                Connection databaseC = dbconnection.getDC();
                PreparedStatement preparedStatementSelectSubsubscription = databaseC.prepareStatement(selectSubsubscriptionSQL);
        ) {
            preparedStatementSelectSubsubscription.setInt(1, id);
            ResultSet subsubscriptionSQL = preparedStatementSelectSubsubscription.executeQuery();
            if (subsubscriptionSQL.first()) {
                abonnement.setPrijs(subsubscriptionSQL.getFloat("prijs"));
                abonnement.setVerdubbeling(subsubscriptionSQL.getString("verdubbeling"));
                abonnement.setDeelbaar(subsubscriptionSQL.getBoolean("deelbaar"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return abonnement;
    }


    public AddAbonnementDTO getUserAbonnement(String token, int id) {
        AddAbonnementDTO abonnement = new AddAbonnementDTO();
        String selectSubsubscriptionSQL = "SELECT id,aanbieder,dienst,prijs,startDatum,verdubbeling,deelbaar,status FROM UserSubscription WHERE token = ? AND id=?";

        try (
                Connection databaseC = dbconnection.getDC();
                PreparedStatement preparedStatementSelectSubsubscription = databaseC.prepareStatement(selectSubsubscriptionSQL);
        ) {
            preparedStatementSelectSubsubscription.setString(1, token);
            preparedStatementSelectSubsubscription.setInt(2, id);
            ResultSet subsubscriptionSQL = preparedStatementSelectSubsubscription.executeQuery();
            while (subsubscriptionSQL.next()) {
                abonnement.setId(subsubscriptionSQL.getInt("id"));
                abonnement.setAanbieder(subsubscriptionSQL.getString("aanbieder"));
                abonnement.setDienst(subsubscriptionSQL.getString("dienst"));
                abonnement.setPrijs(subsubscriptionSQL.getFloat("prijs"));
                abonnement.setStartDatum(subsubscriptionSQL.getString("startDatum"));
                abonnement.setVerdubbeling(subsubscriptionSQL.getString("verdubbeling"));
                abonnement.setDeelbaar(subsubscriptionSQL.getBoolean("deelbaar"));
                abonnement.setStatus(subsubscriptionSQL.getString("status"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return abonnement;
    }

    public boolean terminateAbonnement(String token, int id) {

        boolean succes = false;
        String deleteSubscriptionSQL = "UPDATE UserSubscription SET status ='opgezegd', prijs = 0 WHERE token =? AND id=?";
        try (
                Connection databaseC = dbconnection.getDC();
                PreparedStatement preparedStatementDeleteSubscription = databaseC.prepareStatement(deleteSubscriptionSQL);
        ) {
            preparedStatementDeleteSubscription.setString(1, token);
            preparedStatementDeleteSubscription.setInt(2, id);
            preparedStatementDeleteSubscription.executeUpdate();
             succes = true;

        } catch (SQLException e3) {
            LOG.log(Level.SEVERE, "Uncaught exception", e3);
        }
        return succes;
    }

    public boolean upgradeAbonnement(String token, int id, Abonnement abonnement) {

        String setter = abonnement.getVerdubbeling();
        boolean succes = false;
        String updateSubscriptionSQL = "UPDATE UserSubscription SET verdubbeling = ?, prijs = 1.5 * prijs  WHERE token =? AND id=?";
        try (
                Connection databaseC = dbconnection.getDC();
                PreparedStatement preparedStatementUpgradeSubscription = databaseC.prepareStatement(updateSubscriptionSQL);
        ) {
            preparedStatementUpgradeSubscription.setString(1, setter);
            preparedStatementUpgradeSubscription.setString(2, token);
            preparedStatementUpgradeSubscription.setInt(3, id);
            preparedStatementUpgradeSubscription.executeUpdate();
            succes=true;
        } catch (SQLException e4) {
            LOG.log(Level.SEVERE, "Uncaught exception", e4);
        }
        return succes;
    }

    public ArrayList<Abonnement> filterAbonnementen(String token, String filter) {

        String selectSubscriptionSQL = "SELECT id,aanbieder,dienst FROM Subscription WHERE aanbieder LIKE ? ";
        ArrayList<Abonnement> abonnementen = new ArrayList<Abonnement>();

        try (
                Connection databaseC = dbconnection.getDC();
                PreparedStatement preparedStatementFilterSubscription = databaseC.prepareStatement(selectSubscriptionSQL);
        ) {
            preparedStatementFilterSubscription.setString(1, "%"+filter+"%");
            ResultSet filterSubscriptionSQL = preparedStatementFilterSubscription.executeQuery();
            while (filterSubscriptionSQL.next()) {
                Abonnement abonnement = new Abonnement();
                abonnement.setId(filterSubscriptionSQL.getInt("id"));
                abonnement.setAanbieder(filterSubscriptionSQL.getString("aanbieder"));
                abonnement.setDienst(filterSubscriptionSQL.getString("dienst"));
                abonnementen.add(abonnement);
            }
        } catch (SQLException e2) {
            LOG.log(Level.SEVERE, "Uncaught exception", e2);
        }
        return abonnementen;
    }

    public String getToken(int id) {

        String result = null;
        String getTokenSQL = "SELECT token FROM User WHERE id=?";
        try (
                Connection databaseC = dbconnection.getDC();
                PreparedStatement preparedStatementGetToken = databaseC.prepareStatement(getTokenSQL);
        ) {
            preparedStatementGetToken.setInt(1, id);
            ResultSet resultSetGetToken = preparedStatementGetToken.executeQuery();
            if (resultSetGetToken.first()) {
                result = resultSetGetToken.getString("token");
            }
        } catch (SQLException e3) {
            LOG.log(Level.SEVERE, "Uncaught exception", e3);
        }
        return result;
    }

    public boolean shareAbonnement(String token, int id, Abonnement abonnement) {
        AddAbonnementDTO abonnement2 = getUserAbonnement(token, abonnement.getId());
        String token1 = getToken(id);
        SearchAbonnementDTO searchAbonnementDTO = new SearchAbonnementDTO();
        searchAbonnementDTO.setAanbieder(abonnement2.getAanbieder());
        searchAbonnementDTO.setDienst(abonnement2.getDienst());
        searchAbonnementDTO.setId(abonnement2.getId());
        return addAbonnement(searchAbonnementDTO, getToken(id));
    }
}