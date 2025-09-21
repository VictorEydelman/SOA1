package ru.itmo.service2.resource;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import ru.itmo.service2.domain.Location;
import ru.itmo.service2.domain.Route;
import ru.itmo.service2.service.RouteService;

@Path("/navigator")
@Produces(MediaType.APPLICATION_XML)
@Consumes(MediaType.APPLICATION_XML)
public class RoutesResource {

    @Inject
    private RouteService routeService;

    @GET
    @Path("/routes/{id-from}/{id-to}/{order-by}")
    public Response findRoutes(
            @PathParam("id-from") long idFrom,
            @PathParam("id-to") long idTo,
            @PathParam("order-by") String orderBy
    ) {
        routeService.a();
        var route = new Route();
        route.setTo(new Location());
        route.getTo().setId(idTo);
        return Response.ok(route).build();
    }

    @POST
    @Path("/route/add/{id-from}/{id-to}/{distance}")
    public Response addRoute(
            @PathParam("id-from") long idFrom,
            @PathParam("id-to") long idTo,
            @PathParam("distance") int distance
    ) {
        var route = new Route();
        route.setTo(new Location());
        route.getTo().setId(idTo);
        return Response.ok(route).build();
    }

}