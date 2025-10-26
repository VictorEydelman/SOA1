package ru.itmo.service2.ejb;

import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import jakarta.ws.rs.client.Entity;
import ru.itmo.service2.domain.Coordinates;
import ru.itmo.service2.domain.Location;
import ru.itmo.service2.domain.Route;
import ru.itmo.service2.domain.RouteList;
import ru.itmo.service2.remote.RouteService;

import java.util.Map;

@Stateless
public class RouteServiceBean implements RouteService {

    @EJB
    private RequestServiceBean requestService;

    public RouteList findRoutes(long idFrom, long idTo, String orderBy) {
        RouteList routes = requestService.get("/routes", RouteList.class, Map.of(
                "page", 0,
                "size", 10000,
                "sort", orderBy
        ));

        var filtered = routes.getRoutes().stream()
                .filter(route -> route.getFrom() != null &&
                                 route.getFrom().getId() == idFrom &&
                                 route.getTo() != null &&
                                 route.getTo().getId() == idTo)
                .toList();

        return new RouteList(filtered);
    }

    public Route addRoute(long idFrom, long idTo, int distance, String name, int x, float y) {
        var route = Route.builder()
                .name(name)
                .coordinates(Coordinates.builder()
                        .x(x)
                        .y(y)
                        .build())
                .from(Location.builder()
                        .id(idFrom)
                        .build())
                .to(Location.builder()
                        .id(idTo)
                        .build())
                .distance(distance)
                .build();
        return requestService.post("/routes", Entity.xml(route), Route.class);
    }

}
