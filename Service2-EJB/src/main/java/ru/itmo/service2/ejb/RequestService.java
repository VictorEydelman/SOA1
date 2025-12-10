package ru.itmo.service2.ejb;

import ru.itmo.service2.domain.Route;
import ru.itmo.service2.domain.RouteList;

public interface RequestService {
    RouteList getRoutes(int page, int size, String sort);
    Route postRoute(Route route);
}
