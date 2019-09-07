package java.persistance;

import dao.IDatabaseConnection;
import dao.AbonnementenDAO;
import domain.Abonnement;
import domain.Abonnementen;
import junit.framework.TestCase;
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
import static org.mockito.Mockito.when;

public class AbonnementenDAOTest {
    public static final String TOKEN = "1234-1234-1234";
    private AbonnementenDAO abonnementenDAO;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();


    public AbonnementenDAOTest() throws SQLException {
    }

    @Before
    public void setup() throws SQLException, FileNotFoundException {
        IDatabaseConnection databaseC = new persistance.DatabaseConnectionMock();
        abonnementenDAO = new AbonnementenDAO();
        abonnementenDAO.dbconnection =  databaseC;
        File sqlScriptFile = new File(getClass().getResource("/SQLScript.sql").getFile());
        RunScript.execute(databaseC.getDC(), new FileReader(sqlScriptFile));
    }
    @Test
    public void getAbonnementenPrizeTest() throws SQLException, ParseException {
        // init
        float totalprice=  50.00f;
        //test
        abonnementenDAO.getAbonnementen(TOKEN);
        //controle
    }
    @Test
    public void getAbonnementenSizeTest() throws SQLException, ParseException {
        // init
        int size=2;
        //test
        abonnementenDAO.getAbonnementen(TOKEN);
        //controle
        assertEquals(size,abonnementenDAO.getAbonnementen(TOKEN).getAbonnementen().size());
    }

    @Test
    public void getAbonnement() throws SQLException {
        //init
        Abonnement abonnement = new Abonnement();
        abonnement.setId(1);
        abonnement.setAanbieder("vodafone");
        abonnement.setDienst("Mobiele telefonie 250");

        //test

    }

}
