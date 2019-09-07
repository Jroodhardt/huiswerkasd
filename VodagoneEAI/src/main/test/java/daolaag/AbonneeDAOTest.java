package daolaag;

import dao.IDatabaseConnection;
import dao.AbonneeDAO;
import daolaag.DatabaseConnectionMock;
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
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class AbonneeDAOTest {
    private AbonneeDAO abonneeDAO;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();


    public AbonneeDAOTest() throws SQLException {
    }

    @Before
    public void setup() throws SQLException, FileNotFoundException {
        IDatabaseConnection databaseC = new DatabaseConnectionMock();
        abonneeDAO = new AbonneeDAO();
        abonneeDAO.dbconnection =  databaseC;
        File sqlScriptFile = new File(getClass().getResource("/SQLScript.sql").getFile());
        RunScript.execute(databaseC.getDC(), new FileReader(sqlScriptFile));
    }
    @Test
    public void checkLengteLijstAlleAbonneesTest() throws SQLException, ParseException {
        // init
        ArrayList<Abonnee> subject = abonneeDAO.getAllSubscribers("1234-1234-1234");

        //test
        abonneeDAO.getAllSubscribers("1234-1234-1234");

        //controle
        assertEquals(1,subject.size());
    }
    @Test
    public void checkInhoudLijstAlleAbonneesTest() throws SQLException, ParseException {
        // init
        ArrayList<Abonnee> subject = abonneeDAO.getAllSubscribers("1234-1234-1234");

        //test
        abonneeDAO.getAllSubscribers("1234-1234-1234");

        //controle
        assertEquals("Meron",subject.get(0).getName());
        assertEquals(1,subject.get(0).getId());
        assertEquals("meron@23.com",subject.get(0).getEmail());
    }
}
