package domain;


public class Abonnement {

    private int id;
    private String aanbieder;
    private String dienst;
    private float prijs;
    private String startDatum;
    private String verdubbeling;
    private boolean deelbaar;
    private String status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Abonnement withId(int id) {
        this.id = id;
        return this;
    }

    public String getAanbieder() {
        return aanbieder;
    }

    public void setAanbieder(String aanbieder) {
        this.aanbieder = aanbieder;
    }

    public Abonnement withAanbieder(String aanbieder) {
        this.aanbieder = aanbieder;
        return this;
    }

    public String getDienst() {
        return dienst;
    }

    public void setDienst(String dienst) {
        this.dienst = dienst;
    }

    public Abonnement withDienst(String dienst) {
        this.dienst = dienst;
        return this;
    }

    public float getPrijs() {
        return prijs;
    }

    public void setPrijs(float prijs) {
        this.prijs = prijs;
    }

    public Abonnement withPrijs(float prijs) {
        this.prijs = prijs;
        return this;
    }

    public String getStartDatum() {
        return startDatum;
    }

    public void setStartDatum(String startDatum) {
        this.startDatum = startDatum;
    }

    public Abonnement withStartDatum(String startDatum) {
        this.startDatum = startDatum;
        return this;
    }

    public String getVerdubbeling() {
        return verdubbeling;
    }

    public void setVerdubbeling(String verdubbeling) {
        this.verdubbeling = verdubbeling;
    }

    public Abonnement withVerdubbeling(String verdubbeling) {
        this.verdubbeling = verdubbeling;
        return this;
    }

    public boolean isDeelbaar() {
        return deelbaar;
    }

    public void setDeelbaar(boolean deelbaar) {
        this.deelbaar = deelbaar;
    }

    public Abonnement withDeelbaar(boolean deelbaar) {
        this.deelbaar = deelbaar;
        return this;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Abonnement withStatus(String status) {
        this.status = status;
        return this;
    }


}