package se.ifmo.soa;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;
import se.ifmo.soa.DAO.RouteList;
import se.ifmo.soa.config.CorsFilter;
import se.ifmo.soa.controller.RouteAdditional;
import se.ifmo.soa.controller.RoutesBase;
import se.ifmo.soa.entites.Route;

@Configuration
public class JerseyConfig extends ResourceConfig {

    public JerseyConfig() {
        register(RoutesBase.class);
        register(CorsFilter.class);
        register(RouteAdditional.class);
    }
}