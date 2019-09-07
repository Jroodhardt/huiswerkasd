package dao;

import domain.Abonnee;

import java.sql.SQLException;

public interface ILoginDAO {
    Abonnee checkUser(Abonnee abonnee) throws SQLException;

}
