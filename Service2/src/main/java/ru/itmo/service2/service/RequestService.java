package ru.itmo.service2.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.Entity;
import org.glassfish.jersey.client.JerseyClientBuilder;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.security.cert.X509Certificate;
import java.util.Map;

@ApplicationScoped
public class RequestService {

    public static Client createSelfTrustedClient() {
        try {
            TrustManager[] trustAllCerts = new TrustManager[]{
                    new X509TrustManager() {
                        @Override
                        public void checkClientTrusted(X509Certificate[] chain, String authType) {}
                        @Override
                        public void checkServerTrusted(X509Certificate[] chain, String authType) {}
                        @Override
                        public X509Certificate[] getAcceptedIssuers() {
                            return new X509Certificate[]{};
                        }
                    }
            };
            SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
            return JerseyClientBuilder.newBuilder()
                    .sslContext(sslContext)
                    .hostnameVerifier((hostname, session) -> true) // Отключаем проверку hostname
                    .build();
        } catch (Exception e) {
            throw new RuntimeException("Failed to create unsafe client", e);
        }
    }

    public final static String S1_BASE_URL = "https://localhost:5666/api/v1";

    private final Client client = createSelfTrustedClient();

    public <T> T makeRequest(String method, String url, Entity<?> entity, Class<T> responseType, Map<String, Object> queryParams) {
        var target = client.target(S1_BASE_URL + url);
        if (queryParams != null) {
            for (Map.Entry<String, Object> entry : queryParams.entrySet()) {
                target = target.queryParam(entry.getKey(), entry.getValue());
            }
        }

        try (var response = target.request()
                .method(method, entity)) {
            if (response.getStatus() / 100 != 2) {
                throw new WebApplicationException(response.readEntity(String.class), response);
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
