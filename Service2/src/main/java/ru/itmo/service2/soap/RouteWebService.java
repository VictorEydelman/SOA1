package ru.itmo.service2.soap;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebResult;
import jakarta.jws.WebService;
import ru.itmo.service2.domain.Route;
import ru.itmo.service2.domain.RouteList;

@WebService
public interface RouteWebService {

    @WebMethod
    @WebResult(name = "routes")
    RouteList findRoutes(
            @WebParam(name = "idFrom") long idFrom,
            @WebParam(name = "idTo") long idTo,
            @WebParam(name = "orderBy") String orderBy
    );

    @WebMethod
    @WebResult(name = "route")
    Route addRoute(
            @WebParam(name = "idFrom") long idFrom,
            @WebParam(name = "idTo") long idTo,
            @WebParam(name = "distance") int distance,
            @WebParam(name = "name") String name,
            @WebParam(name = "coordinatesX") int coordinatesX,
            @WebParam(name = "coordinatesY") float coordinatesY
    );
}
