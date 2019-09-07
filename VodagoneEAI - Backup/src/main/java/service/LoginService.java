package service;

import dao.ILoginDAO;
import domain.Abonnee;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
@Path("")
public class LoginService {

    @Inject
    ILoginDAO loginDAO;

    public LoginService() throws SQLException {
        // Nodig voor de SQLException
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/login")
    public Response returnResponse(Abonnee abonnee) throws SQLException {
        return Response.ok(loginDAO.checkUser(abonnee)).build();
    }
}
