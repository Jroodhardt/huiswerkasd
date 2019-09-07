package dao;

import domain.Abonnementen;
import domain.Abonnement;

import java.util.ArrayList;

public interface ISubscriptionDAO {
    public Abonnementen getAbonnementen(String token);
    public boolean addAbonnement(Abonnement abonnement, String token);
    public Abonnement getUserAbonnement(String token, int id);
    public boolean terminateAbonnement(String token, int id);
    public boolean upgradeAbonnement(String token, int id, Abonnement abonnement);
    public ArrayList<Abonnement> filterAbonnementen(String token, String filter);
    public String getToken(int id);
    public boolean shareAbonnement(String token,int id, Abonnement abonnement);
    }
