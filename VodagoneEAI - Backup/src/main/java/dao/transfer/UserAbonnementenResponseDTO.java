package dao.transfer;

import domain.Abonnement;

import java.util.ArrayList;

public class UserAbonnementenResponseDTO {
    private ArrayList<Abonnement> abonnementen = new ArrayList<Abonnement>();
    private float totalPrice;
    public UserAbonnementenResponseDTO(){
        this.totalPrice = totalPrice;
    }

    public void setAbonnementen(Abonnement abonnement) {
        abonnementen.add(abonnement);
    }
    public ArrayList<Abonnement> getAbonnementen() {
        return abonnementen;
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }
}
