package se.ifmo.soa;

import jakarta.ejb.EJB;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import se.ifmo.soa.entites.Route;
import se.ifmo.soa.interfaces.RouteService;

@Path("/routes")

@Produces(MediaType.APPLICATION_XML)
@Consumes(MediaType.APPLICATION_XML)
public class RouteAdditional {
    @EJB
    private RouteService routeService;
    @GET
    @Path("/distance/sum")
    public Response distanceSum(){
        return Response.ok(routeService.getDistance()).build();
    }
    @GET
    @Path("/distance/sum")
    public Response distanceCountLess(@QueryParam("threshold") Integer threshold){
        return Response.ok(routeService.CountDistanceLessThan(threshold)).build();
    }
    @GET
    @Path("/name/start-with")
    public Response nameStartWith(@QueryParam("prefix") String prefix){
        return Response.ok(routeService.getAllbyStartName(prefix)).build();
    }
}
