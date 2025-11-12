package se.ifmo.soa;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;
import se.ifmo.soa.config.CorsFilter;
import se.ifmo.soa.controller.RouteAdditional;
import se.ifmo.soa.controller.RoutesBase;

@Configuration
public class JerseyConfig extends ResourceConfig {

    public JerseyConfig() {
        register(CorsFilter.class);
        register(RoutesBase.class);
        register(RouteAdditional.class);

        packages("org.glassfish.jersey.jaxb.internal");
        packages("se.ifmo.soa.DAO");
        packages("se.ifmo.soa.entites");
        packages("se.ifmo.soa.exception");
    }
}