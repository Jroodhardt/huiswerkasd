package daolaag;

import dao.AbonnementenDAO;
import dao.transfer.SearchAbonnementDTO;
import domain.Abonnement;
import org.h2.tools.RunScript;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import daolaag.DatabaseConnectionMock;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.SQLException;
import java.text.ParseException;

import static org.junit.Assert.assertEquals;

public class AbonnementDAOTest {
    public static final String TOKEN = "1234-1234-1234";
    private AbonnementenDAO abonnementenDAO;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();


    public AbonnementDAOTest() throws SQLException {
    }

    @Before
    public void setup() throws SQLException, FileNotFoundException {
        DatabaseConnectionMock databaseC = new DatabaseConnectionMock();
        abonnementenDAO = new AbonnementenDAO();
        abonnementenDAO.dbconnection =  databaseC;
        File sqlScriptFile = new File(getClass().getResource("/SQLScript.sql").getFile());
        RunScript.execute(databaseC.getDC(), new FileReader(sqlScriptFile));
    }


    @Test
    public void doesAddAbonnementActuallyAddAbonnementTest() throws SQLException, ParseException {
        // init
        SearchAbonnementDTO searchAbonnementDTO = new SearchAbonnementDTO();
        searchAbonnementDTO.setId(6);
        searchAbonnementDTO.setDienst("Dienst 6");
        searchAbonnementDTO.setAanbieder("ziggo");
        //test
        abonnementenDAO.addAbonnement(searchAbonnementDTO,TOKEN);
        //controle
        assertEquals(abonnementenDAO.getUserAbonnement(TOKEN,6).getDienst(),"Dienst 6");
        assertEquals(abonnementenDAO.getUserAbonnement(TOKEN,6).getPrijs(),235.00,0.0001);

    }

    @Test
    public void doesAddAbonnementActuallyUpdateTotalPriceTest() throws SQLException, ParseException {
        // init
        SearchAbonnementDTO searchAbonnementDTO = new SearchAbonnementDTO();
        searchAbonnementDTO.setId(6);
        searchAbonnementDTO.setDienst("Kabel-internet");
        searchAbonnementDTO.setAanbieder("ziggo");
        SearchAbonnementDTO searchAbonnementDTO2 = new SearchAbonnementDTO();
        searchAbonnementDTO2.setId(3);
        searchAbonnementDTO2.setDienst("Dienst 6");
        searchAbonnementDTO2.setAanbieder("ziggo");
        String TestToken= "1111-1111-1111";
        //test
        abonnementenDAO.addAbonnement(searchAbonnementDTO,TestToken);
        abonnementenDAO.addAbonnement(searchAbonnementDTO2,TestToken);
        abonnementenDAO.getAbonnementen(TestToken);
        //controle
        assertEquals(abonnementenDAO.getAbonnementen(TestToken).getTotalPrice(),265,0.0001);;
    }
    @Test(expected = NullPointerException.class)
    public void doesAddAbonnementAddNonExistantAbonnementenTest() throws SQLException, ParseException {
        // init
        SearchAbonnementDTO searchAbonnementDTO = new SearchAbonnementDTO();
        searchAbonnementDTO.setId(999999);
        searchAbonnementDTO.setDienst("Test");
        searchAbonnementDTO.setAanbieder("test");

        //test

        //controle
        abonnementenDAO.addAbonnement(searchAbonnementDTO,TOKEN);

    }


}
