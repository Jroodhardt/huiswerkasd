package service;

import dao.AbonnementenDAO;
import dao.TokenDAO;
import domain.Abonnement;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
@Path("/abonnementen")
public class AbonnementenService {
    @Inject
    AbonnementenDAO abonnementenDAO;
    @Inject
    TokenDAO tokenDAO;

    public AbonnementenService() throws SQLException {
        // Nodig voor de SQLException
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAbonneeAbonnementen(@QueryParam("token")String token) throws SQLException {
        if(tokenDAO.tokenCheck(token)) {
            return Response.ok(abonnementenDAO.getAbonnementen(token)).build();
        }else{
            return Response.status(403).entity("Invalid token").build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addAbonnement(@QueryParam("token")String token, Abonnement abonnement) throws SQLException {
        if(tokenDAO.tokenCheck(token)) {
            abonnementenDAO.addAbonnement(abonnement,token);
            return Response.ok(abonnementenDAO.getAbonnementen(token)).build();
        }else{
            return Response.status(403).entity("Invalid token").build();
        }
    }
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSpecifiekAbonnement(@QueryParam("token")String token,@PathParam("id") int id) throws SQLException {
        if(tokenDAO.tokenCheck(token)) {
            return Response.ok(abonnementenDAO.getUserAbonnement(token,id)).build();
        }else{
            return Response.status(403).entity("Invalid token").build();
        }
    }
    @DELETE
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response terminateAbonnement(@QueryParam("token")String token,@PathParam("id") int id) throws SQLException {
        if(tokenDAO.tokenCheck(token)) {
            if(abonnementenDAO.terminateAbonnement(token,id)) {
                return Response.ok(abonnementenDAO.getUserAbonnement(token, id)).build();
            } else{
                return Response.status(404).entity("failed to terminate").build();
            }
        }else{
            return Response.status(403).entity("Invalid token").build();
        }
    }

    @POST
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response upgradeAbonnement(@QueryParam("token")String token, @PathParam("id") int id, Abonnement abonnement) throws SQLException {
        if(tokenDAO.tokenCheck(token)) {
            if(abonnementenDAO.upgradeAbonnement(token,id, abonnement)) {
                return Response.ok(abonnementenDAO.getUserAbonnement(token,id)).build();
            } else{
                return Response.status(404).entity("failed to upgrade").build();
            }
        }else{
            return Response.status(403).entity("Invalid token").build();
        }
    }

    @GET
    @Path("/all")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response filterAbonementen(@QueryParam("token")String token,@QueryParam("filter") String filter) throws SQLException {
        if(tokenDAO.tokenCheck(token)) {
            return Response.ok(abonnementenDAO.filterAbonnementen(token,filter)).build();
        }else{
            return Response.status(403).entity("Invalid token").build();
        }
    }

}
