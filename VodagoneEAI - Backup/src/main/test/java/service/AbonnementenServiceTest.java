package service;

import dao.AbonnementenDAO;
import domain.Abonnement;
import domain.Abonnementen;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.SQLException;
import java.util.ArrayList;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.Mockito.when;

public class AbonnementenServiceTest {

    private static final String TOKEN = "1234-1234-1234";
    @Mock
    AbonnementenDAO abonnementenDAO;

    @InjectMocks
    AbonnementenService abonnementenService;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }
    public AbonnementenServiceTest() throws SQLException {
    }


    @Test
    public void AbonneeAbonnementenTest() throws SQLException {
        //init
        Abonnementen abonnementen = new Abonnementen();
        when(abonnementenDAO.getAbonnementen(TOKEN)).thenReturn(abonnementen);

        //test
        abonnementenService.getAbonneeAbonnementen(TOKEN);

        //controle
        assertEquals(200, abonnementenService.getAbonneeAbonnementen(TOKEN).getStatus());
    }
    @Test
    public void AddAbonnementenTest() throws SQLException {
        //init
        Abonnement abonnement = new Abonnement();
        Abonnementen abonnementen = new Abonnementen();
        when(abonnementenDAO.getAbonnementen(TOKEN)).thenReturn(abonnementen);

        //test
        abonnementenService.addAbonnement(TOKEN,abonnement);

        //controle
        assertEquals(200, abonnementenService.addAbonnement(TOKEN,abonnement).getStatus());
    }
    @Test
    public void getSpecifiekAbonnementTest() throws SQLException {
        //init
        Abonnement abonnement = new Abonnement();

        when(abonnementenDAO.getAbonnement(1)).thenReturn(abonnement);

        //test
        abonnementenService.getSpecifiekAbonnement(TOKEN,1);

        //controle
        assertEquals(200, abonnementenService.getSpecifiekAbonnement(TOKEN,1).getStatus());
    }
    @Test
    public void terminateAbonnementGoedTest() throws SQLException {
        //init
        Abonnement abonnement = new Abonnement();

        when(abonnementenDAO.terminateAbonnement(TOKEN,1)).thenReturn(true);

        //test
        abonnementenService.getSpecifiekAbonnement(TOKEN,1);

        //controle
        assertEquals(200, abonnementenService.getSpecifiekAbonnement(TOKEN,1).getStatus());
    }
    @Test
    public void terminateAbonnementFoutTest() throws SQLException {
        //init
        when(abonnementenDAO.terminateAbonnement(TOKEN,1)).thenReturn(false);

        //test
        abonnementenService.terminateAbonnement(TOKEN,1);

        //controle
        assertEquals(404, abonnementenService.terminateAbonnement(TOKEN,1).getStatus());
    }
    @Test
    public void upgradeAbonnementGoedTest() throws SQLException {
        //init
        Abonnement abonnement=new Abonnement();
        when(abonnementenDAO.upgradeAbonnement(TOKEN,1, abonnement)).thenReturn(true);

        //test
        abonnementenService.terminateAbonnement(TOKEN,1);

        //controle
        assertEquals(404, abonnementenService.terminateAbonnement(TOKEN,1).getStatus());
    }
    @Test
    public void upgradeAbonnementFoutTest() throws SQLException {
        //init
        Abonnement abonnement=new Abonnement();
        when(abonnementenDAO.upgradeAbonnement(TOKEN,1, abonnement)).thenReturn(true);

        //test
        abonnementenService.upgradeAbonnement(TOKEN,1,abonnement);

        //controle
        assertEquals(200, abonnementenService.upgradeAbonnement(TOKEN,1,abonnement).getStatus());
    }
    @Test
    public void getAllAbonnementenTest() throws SQLException {
        //init

        ArrayList<Abonnement> abonnementen=new ArrayList<Abonnement>();
        when(abonnementenDAO.filterAbonnementen(TOKEN,"vodafone")).thenReturn(abonnementen);

        //test
        abonnementenService.filterAbonementen(TOKEN,"vodafone");

        //controle
        assertEquals(200, abonnementenService.filterAbonementen(TOKEN,"vodafone").getStatus());
    }
    @Test
    public void getAbonnementTest() throws SQLException {
        //init

        ArrayList<Abonnement> abonnementen=new ArrayList<Abonnement>();
        when(abonnementenDAO.filterAbonnementen(TOKEN,"vodafone")).thenReturn(abonnementen);

        //test
        abonnementenService.filterAbonementen(TOKEN,"vodafone");

        //controle
        assertEquals(200, abonnementenService.filterAbonementen(TOKEN,"vodafone").getStatus());
    }

}
