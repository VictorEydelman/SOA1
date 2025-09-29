package se.ifmo.soa.interfaces;

import jakarta.persistence.criteria.*;
import se.ifmo.soa.DAO.PaginatedResult;
import se.ifmo.soa.entites.Coordinates;
import se.ifmo.soa.entites.Location;
import se.ifmo.soa.entites.Route;

import java.util.List;

public interface RouteService {
    public Route save(Route route);

    public List<Route> getByParameters(
            Integer page,
            Integer size,
            String sort,
            Integer id,
            String name,
            Integer distance,
            Integer coordinatesX,
            Float coordinatesY,
            String toName,
            Integer toX,
            String toY,
            Integer toZ,
            String fromName,
            Integer fromX,
            String fromY,
            Integer fromZ);

    public void addPredicates(Object param, List<Predicate> predicates, String paramName, CriteriaBuilder cb, Root<Route> root);

    public Route getById(Integer id);

    public void addPredicatesCoordinates(Object param, List<Predicate> predicates, String paramName, CriteriaBuilder cb, Join<Route, Coordinates> root);
    public void addPredicatesLocation(Object param, List<Predicate> predicates, String paramName, CriteriaBuilder cb, Join<Route, Location> root);
    public boolean deleteById(Integer id);
    public Integer getDistance();
    public Long CountDistanceLessThan(Integer threshold);
    public List<Route> getAllbyStartName(String prefix);
}