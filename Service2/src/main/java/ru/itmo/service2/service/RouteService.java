package ru.itmo.service2.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.client.Client;

@ApplicationScoped
public class RouteService {

    @Inject
    private Client client;

    public void a() {
        client.target("http://localhost:24770/Service2-1.0/api/v1/navigator/route/add/1/242/100").request().post(null);
    }

}
