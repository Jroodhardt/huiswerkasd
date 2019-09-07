package domain;

import java.math.BigDecimal;
import java.util.ArrayList;

public class Abonnementen {
    private ArrayList<Abonnement> abonnementen = new ArrayList<Abonnement>();
    private float totalPrice;
    public Abonnementen(){
    }

    public void addAbonnement(Abonnement abonnement) {
        abonnementen.add(abonnement);
        totalPrice+=abonnement.getPrijs();
    }
    public ArrayList<Abonnement> getAbonnementen() {
        return abonnementen;
    }

    public float getTotalPrice() {
        return totalPrice;
    }
}
