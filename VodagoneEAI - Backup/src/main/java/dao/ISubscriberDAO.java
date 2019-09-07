package dao;

import domain.Abonnee;

import java.util.ArrayList;

public interface ISubscriberDAO {
    public ArrayList<Abonnee> getAllSubscribers(String token);
}
