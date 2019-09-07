package ica.di;

import ica.di.DB.Passenger;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App 
{


    public static void main( String[] args )
    {
        Printer printer=new Printer();
        Scanner scanner = new Scanner(System.in);

        printer.printRequest();
        String answer = scanner.nextLine();

        if(answer.equals("yes")){

            printer.printDesks();

            int chosenDesk= scanner.nextInt();
            scanner.nextLine();
            printer.printChosendesk(chosenDesk);
                printer.printSearchName();
                String name=scanner.nextLine();

                printer.printSearchFlightnumber();
                int flightnumber=scanner.nextInt();
                scanner.nextLine();

                printer.printSearchDestination();
                String destination=scanner.nextLine();

                printer.printSearchCompany();
                String company=scanner.nextLine();

                printer.printSearchDeparture();
                String departure=scanner.nextLine();


                ArrayList<Passenger> passengers;
                passengers=printer.printListOfPassengers(name,flightnumber,destination,company,departure);
                printer.printChoosePassenger();
                int checkInPassenger=scanner.nextInt();
                scanner.nextLine();

                Passenger passenger;
                passenger=passengers.get(checkInPassenger);
                printer.printMoreInfoPassenger(passenger);

                printer.printPassengerLuggage(passenger.getPassengernumber(),passenger.getFlightnumber());
                printer.printEnterSeat();
                int seat=scanner.nextInt();
                scanner.nextLine();

                printer.printEnterCheckintime();
                String checkintime = scanner.nextLine();

                printer.printCheckIn(passenger.getPassengernumber(),passenger.getFlightnumber(),chosenDesk,seat,checkintime);

                printer.printAddLuggage();

            String add = scanner.nextLine();
            if(add.equals("yes")) {
                printer.printAdd();
                int luggageweight = scanner.nextInt();
                scanner.nextLine();
                printer.printAddLuggageWeight(passenger.getPassengernumber(), passenger.getFlightnumber(), luggageweight);
            }

                printer.printDeleteLuggage();
                String delete = scanner.nextLine();
                if(delete.equals("yes")) {
                    printer.printDelete();
                    int id = scanner.nextInt();
                    scanner.nextLine();
                    printer.printDeleteLuggageID(passenger.getPassengernumber(), passenger.getFlightnumber(), id);
                }
        }

    }

}
