package ru.itmo.service2.ejb;

import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import ru.itmo.service2.domain.Coordinates;
import ru.itmo.service2.domain.Location;
import ru.itmo.service2.domain.Route;
import ru.itmo.service2.domain.RouteList;
import ru.itmo.service2.remote.RouteService;

@Stateless
public class RouteServiceBean implements RouteService {

    @EJB
    private RequestService requestService;

    public RouteList findRoutes(long idFrom, long idTo, String orderBy) {
        RouteList routes = requestService.getRoutes(0, 10000, orderBy);

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
        return requestService.postRoute(route);
    }

}
