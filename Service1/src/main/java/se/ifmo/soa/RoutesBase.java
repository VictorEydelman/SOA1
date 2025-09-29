package se.ifmo.soa;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import jakarta.ejb.EJB;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import se.ifmo.soa.DAO.RouteList;
import se.ifmo.soa.DAO.RoutesDAO;
import se.ifmo.soa.entites.Coordinates;
import se.ifmo.soa.entites.Location;
import se.ifmo.soa.entites.Route;
import se.ifmo.soa.interfaces.LocationService;
import se.ifmo.soa.interfaces.RouteService;

import java.io.IOException;
import java.io.InputStream;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;


@Path("/routes")
@Produces(MediaType.APPLICATION_XML)
@Consumes(MediaType.APPLICATION_XML)
public class RoutesBase {
    @EJB
    private RouteService routeService;
    @EJB
    private LocationService locationService;

    private final XmlMapper xmlMapper = new XmlMapper();

    @GET
    public Response getRoutes(
            @QueryParam("page") Integer page,
            @QueryParam("size") Integer size,
            @QueryParam("sort") String sort,
            @QueryParam("id") Integer id,
            @QueryParam("name") String name,
            @QueryParam("distance") Integer distance,
            @QueryParam("coordinates-x") Integer coordinatesX,
            @QueryParam("coordinates-y") Float coordinatesY,
            @QueryParam("to-name") String toName,
            @QueryParam("to-x") Integer toX,
            @QueryParam("to-y") String toY,
            @QueryParam("to-z") Integer toZ,
            @QueryParam("from-name") String fromName,
            @QueryParam("from-x") Integer fromX,
            @QueryParam("from-y") String fromY,
            @QueryParam("from-z") Integer fromZ) {

        if (page == null) page = 0;
        if (size == null) size = 20;

        List<Route> routes = routeService.getByParameters(
                page, size, sort, id, name, distance, coordinatesX,
                coordinatesY, toName, toX, toY, toZ, fromName,
                fromX, fromY, fromZ);
        return Response.ok(new RouteList(routes)).build();
    }

    @POST
    public Response addRoutes(InputStream routeXML) throws IOException {
        RoutesDAO routeDAO = xmlMapper.readValue(routeXML, RoutesDAO.class);
        Coordinates coordinates = Coordinates.builder().y(routeDAO.getCoordinates().getY())
                .x(routeDAO.getCoordinates().getX()).build();
        Location to = locationService.fromDAO(routeDAO.getTo());
        Location from = locationService.fromDAO(routeDAO.getFrom());
        Route route = Route.builder().name(routeDAO.getName()).coordinates(coordinates)
                .creationDate(ZonedDateTime.of(2025, 9, 28, 12, 0, 0, 0, ZoneId.systemDefault()))
                .to(to).from(from).distance(routeDAO.getDistance()).build();
        routeService.save(route);
        return Response.status(Response.Status.CREATED).entity(route).build();
    }

    @GET
    @Path("/{id}")
    public Response getRouteById(
            @PathParam("id") Integer id) {
        Route route = routeService.getById(id);
        return Response.ok(route).build();
    }

    @PUT
    @Path("/{id}")
    public Response updateRoute(@PathParam("id") Integer id, InputStream routeXML) throws IOException {
        RoutesDAO routeDAO = xmlMapper.readValue(routeXML, RoutesDAO.class);
        Coordinates coordinates = Coordinates.builder().y(routeDAO.getCoordinates().getY())
                .x(routeDAO.getCoordinates().getX()).build();
        Location to = locationService.fromDAO(routeDAO.getTo());
        Location from = locationService.fromDAO(routeDAO.getFrom());
        Route route = Route.builder().id(id).name(routeDAO.getName()).coordinates(coordinates)
                .creationDate(ZonedDateTime.of(2025, 9, 28, 12, 0, 0, 0, ZoneId.systemDefault()))
                .to(to).from(from).distance(routeDAO.getDistance()).build();
        routeService.save(route);
        return Response.status(Response.Status.CREATED).entity(route).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteRoute(@PathParam("id") Integer id) {
        return Response.status(Response.Status.CREATED).entity(String.valueOf(routeService.deleteById(id))).build();
    }
}
