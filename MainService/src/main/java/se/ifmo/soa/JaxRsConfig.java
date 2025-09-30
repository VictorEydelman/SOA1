package se.ifmo.soa;

import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;
import se.ifmo.soa.config.CorsFilter;

import java.util.HashSet;
import java.util.Set;

@ApplicationPath("/api/v1")
public class JaxRsConfig extends Application {
    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> classes = new HashSet<>();
        classes.add(RoutesBase.class);
        classes.add(HelloResource.class);
        classes.add(RouteAdditional.class);
        classes.add(CorsFilter.class);
        return classes;
    }
}