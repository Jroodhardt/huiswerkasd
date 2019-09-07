package service;

import dao.*;
import domain.Abonnee;
import domain.Abonnement;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import java.sql.SQLException;
import java.util.ArrayList;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
@RunWith(MockitoJUnitRunner.class)
public class AbonneeServiceTest {
    public static final String TOKEN = "1234-1234-1234";
    @Mock
    AbonneeDAO abonneeDAO;
    @Mock
    AbonnementenDAO abonnementenDAO;

    @InjectMocks
    AbonneeService abonneeService;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }
    public AbonneeServiceTest() throws SQLException {
    }


    @Test
    public void GetAllAbonneesGoedTest() throws SQLException {
        //init
        ArrayList<Abonnee> abonnees = new ArrayList<>();
        when(abonneeDAO.getAllSubscribers(TOKEN)).thenReturn(abonnees);

        //test
        abonneeService.getAllAbonnees(TOKEN);

        //controle
        assertEquals(200, abonneeService.getAllAbonnees(TOKEN).getStatus());
    }
    @Test
    public void GetAllAbonneesFoutTest() throws SQLException {
        //init
        ArrayList<Abonnee> abonnees = new ArrayList<>();
        when(abonneeDAO.getAllSubscribers(TOKEN)).thenReturn(null);

        //test
        abonneeService.getAllAbonnees(TOKEN);

        //controle
        assertEquals(200, abonneeService.getAllAbonnees(TOKEN).getStatus());
    }
    @Test
    public void ShareAbonnementGoedTest() throws SQLException {
        //init
        Abonnement abonnement = new Abonnement();
        when(abonnementenDAO.shareAbonnement(TOKEN,1, abonnement)).thenReturn(true);


        //test
        abonneeService.shareAbonnement(TOKEN,1, abonnement);

        //controle
        verify(abonnementenDAO, times(1)).shareAbonnement(TOKEN, 1, abonnement);
        assertEquals(200, abonneeService.shareAbonnement(TOKEN,1, abonnement).getStatus());
    }
    @Test
    public void ShareAbonnementFoutTest() throws SQLException {
        //init
        Abonnement abonnement = new Abonnement();
        when(abonnementenDAO.shareAbonnement(TOKEN,1, abonnement)).thenReturn(false);


        //test
        abonneeService.shareAbonnement(TOKEN,1, abonnement);

        //controle
        assertEquals(404, abonneeService.shareAbonnement(TOKEN,1, abonnement).getStatus());
    }
}
