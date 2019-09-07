package ica.di.DB;

public class Passenger {
    private int passengernumber;
    private String name;
    private int flightnumber;
    private String country;
    private String company;
    private String departuretime;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getFlightnumber() {
        return flightnumber;
    }

    public void setFlightnumber(int flightnumber) {
        this.flightnumber = flightnumber;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getDeparturetime() {
        return departuretime;
    }

    public void setDeparturetime(String departuretime) {
        this.departuretime = departuretime;
    }

    public int getPassengernumber() {
        return passengernumber;
    }

    public void setPassengernumber(int passengernumber) {
        this.passengernumber = passengernumber;
    }
}
