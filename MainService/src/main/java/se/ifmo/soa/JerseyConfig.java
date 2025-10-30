package se.ifmo.soa;

import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;
import se.ifmo.soa.service.*;

import java.util.HashSet;
import java.util.Set;

@ApplicationPath("/api/v1")
public class JaxRsConfig extends Application {
}
