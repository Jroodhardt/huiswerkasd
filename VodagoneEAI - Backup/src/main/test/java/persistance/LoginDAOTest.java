package persistance;

import dao.IDatabaseConnection;
import dao.LoginDAO;
import domain.Abonnee;
import org.h2.tools.RunScript;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.SQLException;
import java.text.ParseException;

import static org.junit.Assert.assertEquals;

public class LoginDAOTest {
    private LoginDAO loginDAO;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();


    public LoginDAOTest() throws SQLException {
    }

    @Before
    public void setup() throws SQLException, FileNotFoundException {
        IDatabaseConnection databaseC = new DatabaseConnectionMock();
        loginDAO = new LoginDAO();
        loginDAO.dbconnection =  databaseC;
        File sqlScriptFile = new File(getClass().getResource("/SQLScript.sql").getFile());
        RunScript.execute(databaseC.getDC(), new FileReader(sqlScriptFile));
    }
    @Test
    public void checkFouteInlogTest() throws SQLException, ParseException {
        // init
        Abonnee abonnee=new Abonnee();
        abonnee.setUser("user");
        abonnee.setPassword("onjuistwachtwoord");

        //test
        loginDAO.checkUser(abonnee);

        //controle
        assertEquals(null, loginDAO.checkUser(abonnee));
    }
    @Test
    public void checkGoedeInlogTest() throws SQLException, ParseException {
        // init
        Abonnee abonnee=new Abonnee();
        abonnee.setUser("user");
        abonnee.setPassword("wachtwoord");
        Abonnee testtoken=loginDAO.checkUser(abonnee);

        //test
        loginDAO.checkUser(abonnee);

        //controle
        assertEquals("1111-1111-1111", testtoken.getToken());
        assertEquals("user", testtoken.getUser());
    }
    @Test
    public void checkNietBestaandeInlogTest() throws SQLException, ParseException {
        // init
        Abonnee abonnee=new Abonnee();
        abonnee.setUser("randomletters");
        abonnee.setPassword("randomletters");

        //test
        loginDAO.checkUser(abonnee);

        //controle
        assertEquals(null, loginDAO.checkUser(abonnee));
    }
}
