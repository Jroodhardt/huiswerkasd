package service;

import dao.AbonneeDAO;
import dao.AbonnementenDAO;
import dao.TokenDAO;
import domain.Abonnement;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;

@Path("/abonnees")
public class AbonneeService {
    @Inject
    AbonneeDAO abonneeDAO;
    @Inject
    AbonnementenDAO abonnementenDAO;
    @Inject
    TokenDAO tokenDAO;


    public AbonneeService() throws SQLException {
        // Nodig voor de SQLException
    }

    @GET

    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllAbonnees(@QueryParam("token") String token) throws SQLException {
        if (tokenDAO.tokenCheck(token))
            return Response.ok(abonneeDAO.getAllSubscribers(token)).build();
        else {
            return Response.status(403).entity("Token doesn't exist").build();
        }
    }

    @POST
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response shareAbonnement(@QueryParam("token") String token, @PathParam("id") int id, Abonnement abonnement) throws SQLException {
        if (tokenDAO.tokenCheck(token)) {
            if (abonnementenDAO.shareAbonnement(token, id, abonnement)) {
                return Response.ok().build();
            } else {return Response.status(404).entity("Sharing the subscription failed").build();}
        }else {
            return Response.status(403).entity("Token doesn't exist").build();
        }
    }
}
