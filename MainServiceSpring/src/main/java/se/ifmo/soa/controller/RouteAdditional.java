package se.ifmo.soa.controller;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import se.ifmo.soa.DAO.DistanceCount;
import se.ifmo.soa.DAO.DistanceSum;
import se.ifmo.soa.DAO.RouteList;
import se.ifmo.soa.service.RouteService;

@Path("/routes")
@Produces(MediaType.APPLICATION_XML)
@Consumes(MediaType.APPLICATION_XML)
@Component
@NoArgsConstructor
public class RouteAdditional {

    @Autowired
    private RouteService routeService;

    @GET
    @Path("/distance/sum")
    public Response distanceSum(){
        return Response.ok(new DistanceSum(routeService.getDistance())).build();
    }
    @GET
    @Path("/distance/count-less")
    public Response distanceCountLess(@QueryParam("threshold") Integer threshold){
        return Response.ok(new DistanceCount(routeService.CountDistanceLessThan(threshold))).build();
    }
    @GET
    @Path("/name/starts-with")
    public Response nameStartWith(@QueryParam("prefix") String prefix){
        return Response.ok(new RouteList(routeService.getAllbyStartName(prefix))).build();
    }
}
