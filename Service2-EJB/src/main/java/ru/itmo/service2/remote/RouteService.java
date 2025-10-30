package ru.itmo.service2.remote;

import jakarta.ejb.Remote;
import ru.itmo.service2.domain.Route;
import ru.itmo.service2.domain.RouteList;

@Remote
public interface RouteService {

    RouteList findRoutes(long idFrom, long idTo, String orderBy);

    Route addRoute(long idFrom, long idTo, int distance, String name, int x, float y);
}
