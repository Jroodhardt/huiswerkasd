package service;

import dao.ILoginDAO;
import domain.Abonnee;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.SQLException;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.Mockito.when;

public class LoginServiceTest {
    @Mock
    ILoginDAO loginDAO;

    @InjectMocks
    LoginService loginService;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }
    public LoginServiceTest() throws SQLException {
    }


    @Test
    public void LoginService() throws SQLException {
        Abonnee abonnee = new Abonnee();
        Abonnee abonneetoken = new Abonnee();

        when(loginDAO.checkUser(abonnee)).thenReturn(abonneetoken);

        //test
        loginService.returnResponse(abonnee);

        //controle
        assertEquals(200, loginService.returnResponse(abonnee).getStatus());
    }
}
