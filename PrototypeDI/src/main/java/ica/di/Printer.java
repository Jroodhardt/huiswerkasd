package ica.di;

import com.sun.org.apache.bcel.internal.generic.LUSHR;
import ica.di.DB.Luggage;
import ica.di.DB.Passenger;
import ica.di.DB.SearchPassengerDAO;

import java.util.ArrayList;

public class Printer {
    void printRequest(){
        System.out.println("Do you want to check in at a desk?");
    }
    void printDesks(){
        System.out.println("These desks are available:");
        System.out.println("--------------------------------------------");
        SearchPassengerDAO searchPassengerDAO=new SearchPassengerDAO();
        ArrayList<Integer> desks = new ArrayList<Integer>();
        desks=searchPassengerDAO.getAllDesks();
        for (int o: desks) {
            System.out.println("Desk: "+ o);
        }
        System.out.println("--------------------------------------------");

    }
    void printChosendesk(int desknumber) {
        System.out.println("You checked in at desk: "+ desknumber);
        System.out.println("--------------------------------------------");
    }
    void printSearchName(){System.out.println("Enter passengername:"); }
    void printSearchFlightnumber(){System.out.println("Enter flightnumber:"); }
    void printSearchDestination(){System.out.println("Enter destination:");}
    void printSearchCompany(){System.out.println("Enter company:");}
    void printSearchDeparture(){System.out.println("Enter departure:");}
    ArrayList<Passenger> printListOfPassengers(String naam,int flightnumber, String destination,String company, String departuretime) {
        SearchPassengerDAO searchPassengerDAO = new SearchPassengerDAO();
        ArrayList<Passenger> passengers = new ArrayList<Passenger>();
        passengers = searchPassengerDAO.getPassenger(naam, flightnumber, destination, company, departuretime);

        if(!passengers.isEmpty()) {
            for (Passenger o : passengers) {
                System.out.println("Is this passenger correct?");
                System.out.println("--------------------------------------------");
                System.out.println("Option: "+passengers.indexOf(o));
                System.out.println("passengernumber: " + o.getPassengernumber());
                System.out.println("name: " + o.getName());
                System.out.println("flightnumber: " + o.getFlightnumber());
                System.out.println("--------------------------------------------");
            }
            return passengers;
        } else {
            System.out.println("Passenger not found");
            return null;
        }
    }
    void printMoreInfoPassenger(Passenger passenger){
        System.out.println("Passenger info");

        System.out.println("--------------------------------------------");
        System.out.println("passengernumber: " + passenger.getPassengernumber());
        System.out.println("name: " + passenger.getName());
        System.out.println("flightnumber: " + passenger.getFlightnumber());
        System.out.println("country: " + passenger.getCountry());
        System.out.println("companyname: " + passenger.getCompany());
        System.out.println("departuretime: " + passenger.getDeparturetime());
        System.out.println("--------------------------------------------");
    }
    void printPassengerLuggage(int passengernumber, int flightnumber ){
        SearchPassengerDAO searchPassengerDAO = new SearchPassengerDAO();
        ArrayList<Luggage> luggages = new ArrayList<Luggage>();
        luggages=searchPassengerDAO.getPassengerLuggage(passengernumber, flightnumber);
        System.out.println("Passenger items:");
        System.out.println("--------------------------------------------");
        for (Luggage l : luggages) {
        System.out.println("object: " + l.getObjectid());
        System.out.println("weight: " + l.getObjectweight());
        System.out.println("--------------------------------------------");
    }}
    void printChoosePassenger(){System.out.println("Choose a passenger" );}
    void printEnterSeat(){System.out.println("Enter the seat" );}
    void printEnterCheckintime(){System.out.println("Enter the check-in time" );}
    void printAddLuggage(){

        System.out.println("Do you want to enter luggage?" );

    }
    void printAdd(){
        System.out.println("Enter item weight" );
    }
    void printAddLuggageWeight(int passengernumber, int flightnumber, int weight){
        SearchPassengerDAO searchPassengerDAO = new SearchPassengerDAO();
        searchPassengerDAO.addLuggage(passengernumber,flightnumber,weight);
        System.out.println("Luggage has been added" );
    }
    void printCheckIn(int passengernumber,int flightnumber, int desknumber, int seat, String checkintim){
        SearchPassengerDAO searchPassengerDAO = new SearchPassengerDAO();
        searchPassengerDAO.checkin(passengernumber,flightnumber,desknumber,seat,checkintim);
        System.out.println("Passenger "+passengernumber+" has been checked in");
    }

    void printDeleteLuggageID(int passengernumber, int flightnumber, int id){
        SearchPassengerDAO searchPassengerDAO = new SearchPassengerDAO();
        searchPassengerDAO.deleteLuggage(passengernumber,flightnumber,id);
        System.out.println("Luggage has been deleted" );
    }

     void printDeleteLuggage() {
        System.out.println("Do you want to delete luggage?" );

    }
    void printDelete(){
        System.out.println("Enter object id" );
    }
}
