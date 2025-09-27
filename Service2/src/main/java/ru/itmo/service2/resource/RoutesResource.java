package ru.itmo.service2.resource;

import jakarta.inject.Inject;
import jakarta.validation.constraints.NotNull;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
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
        var res = routeService.findRoutes(idFrom, idTo, orderBy);
        return Response.ok(res).build();
    }

    @POST
    @Path("/route/add/{id-from}/{id-to}/{distance}")
    public Response addRoute(
            @PathParam("id-from") long idFrom,
            @PathParam("id-to") long idTo,
            @PathParam("distance") int distance,
            @NotNull @QueryParam("name") String name,
            @NotNull @QueryParam("coordinates-x") Integer x,
            @NotNull @QueryParam("coordinates-y") Float y
    ) {
        var res = routeService.addRoute(idFrom, idTo, distance, name, x, y);
        return Response.status(Response.Status.CREATED).entity(res).build();
    }

}
