package ru.itmo.service2.soap;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebResult;
import jakarta.jws.WebService;
import ru.itmo.service2.domain.Route;
import ru.itmo.service2.domain.RouteList;

@WebService(serviceName = "WebService1")
public interface WebService1 {

    @WebMethod
    @WebResult(name = "routes")
    RouteList getAllRoutes(
            @WebParam(name = "page") int page,
            @WebParam(name = "size") int size,
            @WebParam(name = "sort") String sort
    );

    @WebMethod
    @WebResult(name = "route")
    Route createRoute(
            @WebParam(name = "route") Route route
    );
}
