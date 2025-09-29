package ru.itmo.service2.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.ClientErrorException;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.Entity;

import java.util.Map;

@ApplicationScoped
public class RequestService {

    public final static String S1_BASE_URL = "http://localhost:8080/api/v1";

    @Inject
    private Client client;

    public <T> T makeRequest(String method, String url, Entity<?> entity, Class<T> responseType, Map<String, Object> queryParams) {
        var target = client.target(S1_BASE_URL + url);
        if (queryParams != null) {
            queryParams.forEach(target::queryParam);
        }

        try (var response = target.request()
                .method(method, entity)) {
            if (response.getStatus() / 100 != 2) {
                throw new WebApplicationException(response);
            }
            return response.readEntity(responseType);
        }

    }

    public <T> T get(String url, Class<T> responseType, Map<String, Object> queryParams) {
        return makeRequest("GET", url, null, responseType, queryParams);
    }

    public <T> T post(String url, Entity<?> entity, Class<T> responseType) {
        return makeRequest("POST", url, entity, responseType, null);
    }

}
