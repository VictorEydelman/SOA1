package ru.itmo.service2.soap;

import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import jakarta.jws.WebService;
import jakarta.jws.soap.SOAPBinding;
import ru.itmo.service2.domain.Route;
import ru.itmo.service2.domain.RouteList;
import ru.itmo.service2.remote.RouteService;

@Stateless
@WebService(name = "RouteWebService")
@SOAPBinding
public class RouteWebServiceImpl implements RouteWebService {

    @EJB(mappedName = "java:global/Service2-EJB-1.0/RouteServiceBean!ru.itmo.service2.remote.RouteService")
    private RouteService routeService;

    @Override
    public RouteList findRoutes(long idFrom, long idTo, String orderBy) {
        return routeService.findRoutes(idFrom, idTo, orderBy);
    }

    @Override
    public Route addRoute(long idFrom, long idTo, int distance, String name, int coordinatesX, float coordinatesY) {
        return routeService.addRoute(idFrom, idTo, distance, name, coordinatesX, coordinatesY);
    }
}
