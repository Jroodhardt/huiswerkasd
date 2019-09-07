package ica.di.DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SearchPassengerDAO {

    private static final Logger LOG = Logger.getLogger("com.library.Books");

    public ArrayList<Integer> getAllDesks() {
        IDatabaseConnection dbconnection = new DatabaseConnection();
        String selectAllDesks = "SELECT desknumber FROM Desk";
        ArrayList<Integer> desks = new ArrayList<Integer>();

        try (
                Connection databaseC = dbconnection.getDC();
                PreparedStatement preparedStatementDesks = databaseC.prepareStatement(selectAllDesks);
        ) {

            ResultSet getAllDesksSQL = preparedStatementDesks.executeQuery();
            while (getAllDesksSQL.next()) {
                int desk = getAllDesksSQL.getInt("desknumber");
                desks.add(desk);
            }
        } catch (SQLException e2) {
            LOG.log(Level.SEVERE, "Uncaught exception", e2);
        }
        return desks;
    }
    public ArrayList<Passenger> getPassenger(String naam, int flightnumber, String desination, String company, String departuredate) {
        IDatabaseConnection dbconnection = new DatabaseConnection();
        String selectPassenger = "EXEC SP_SelectPassengerInfo ?,?,?,?,?";
        ArrayList<Passenger> passengers = new ArrayList<Passenger>();

        try (
                Connection databaseC = dbconnection.getDC();
                PreparedStatement preparedStatementPassenger = databaseC.prepareStatement(selectPassenger);
        ) {
            preparedStatementPassenger.setString(1,naam);
            preparedStatementPassenger.setInt(2,flightnumber);
            preparedStatementPassenger.setString(3,desination);
            preparedStatementPassenger.setString(4,company);
            preparedStatementPassenger.setString(5,departuredate);
            ResultSet selectPassengerSQL = preparedStatementPassenger.executeQuery();
            while (selectPassengerSQL.next()) {
                Passenger passenger=new Passenger();
                passenger.setPassengernumber(selectPassengerSQL.getInt("passengernumber"));
                passenger.setName(selectPassengerSQL.getString("name"));
                passenger.setFlightnumber(selectPassengerSQL.getInt("flightnumber"));
                passenger.setCountry(selectPassengerSQL.getString("country"));
                passenger.setCompany(selectPassengerSQL.getString("companyname"));
                passenger.setDeparturetime(selectPassengerSQL.getString("departuretime"));
                passengers.add(passenger);
            }
        } catch (SQLException e2) {
            LOG.log(Level.SEVERE, "Uncaught exception", e2);
        }
        return passengers;
    }
    public ArrayList<Luggage> getPassengerLuggage(int passengernumber, int flightnumber) {
        IDatabaseConnection dbconnection = new DatabaseConnection();
        String selectPassenger = "EXEC SP_GetPassengerLuggage ?,?";
        ArrayList<Luggage> luggages = new ArrayList<Luggage>();

        try (
                Connection databaseC = dbconnection.getDC();
                PreparedStatement preparedStatementPassenger = databaseC.prepareStatement(selectPassenger);
        ) {
            preparedStatementPassenger.setInt(1,passengernumber);
            preparedStatementPassenger.setInt(2,flightnumber);

            ResultSet selectPassengerSQL = preparedStatementPassenger.executeQuery();
            while (selectPassengerSQL.next()) {
                Luggage luggage=new Luggage();
                luggage.setObjectid(selectPassengerSQL.getInt("id"));
                luggage.setObjectweight(selectPassengerSQL.getInt("weight"));
                luggages.add(luggage);
            }
        } catch (SQLException e2) {
            LOG.log(Level.SEVERE, "Uncaught exception", e2);
        }
        return luggages;
    }
    public void checkin(int passengernumber,int flightnumber, int desknumber, int seat, String checkintime) {
        IDatabaseConnection dbconnection = new DatabaseConnection();
        String selectPassenger = "EXEC SP_CheckInPassenger ?,?,?,?,?";
        ArrayList<Luggage> luggages = new ArrayList<Luggage>();

        try (
                Connection databaseC = dbconnection.getDC();
                PreparedStatement preparedStatementPassenger = databaseC.prepareStatement(selectPassenger);
        ) {
            preparedStatementPassenger.setInt(1,passengernumber);
            preparedStatementPassenger.setInt(2,flightnumber);
            preparedStatementPassenger.setInt(3,desknumber);
            preparedStatementPassenger.setInt(4,seat);
            preparedStatementPassenger.setString(5,checkintime);
            preparedStatementPassenger.executeUpdate();
        } catch (SQLException e2) {
            LOG.log(Level.SEVERE, "Uncaught exception", e2);
        }
    }
    public void addLuggage(int passengernumber, int flightnumber, int weight) {
        IDatabaseConnection dbconnection = new DatabaseConnection();
        String selectPassenger = "EXEC SP_AddLuggae ?,?,?";
        try (
                Connection databaseC = dbconnection.getDC();
                PreparedStatement preparedStatementPassenger = databaseC.prepareStatement(selectPassenger);
        ) {
            preparedStatementPassenger.setInt(1,passengernumber);
            preparedStatementPassenger.setInt(2,flightnumber);
            preparedStatementPassenger.setInt(3,weight);;;

            preparedStatementPassenger.executeUpdate();
        } catch (SQLException e2) {
            LOG.log(Level.SEVERE, "Uncaught exception", e2);
        }
    }

    public void deleteLuggage(int passengernumber, int flightnumber, int id) {
        IDatabaseConnection dbconnection = new DatabaseConnection();
        String selectPassenger = "EXEC SP_DeleteLuggae ?,?,?";
        try (
                Connection databaseC = dbconnection.getDC();
                PreparedStatement preparedStatementLuggage = databaseC.prepareStatement(selectPassenger);
        ) {
            preparedStatementLuggage.setInt(1,passengernumber);
            preparedStatementLuggage.setInt(2,flightnumber);
            preparedStatementLuggage.setInt(3,id);;
            preparedStatementLuggage.executeUpdate();
        } catch (SQLException e2) {
            LOG.log(Level.SEVERE, "Uncaught exception", e2);
        }
    }

}
