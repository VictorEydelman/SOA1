package se.ifmo.soa.service;

import jakarta.annotation.PostConstruct;
import jakarta.ejb.Stateless;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.*;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.QueryParam;
import se.ifmo.soa.DAO.PaginatedResult;
import se.ifmo.soa.entites.Coordinates;
import se.ifmo.soa.entites.Location;
import se.ifmo.soa.entites.Route;
import se.ifmo.soa.interfaces.RouteService;

import javax.swing.*;
import java.security.spec.RSAOtherPrimeInfo;
import java.util.ArrayList;
import java.util.List;

@Stateless
@Transactional
public class RouteServiceImpl implements RouteService {
    @PersistenceContext(unitName = "route")
    private EntityManager entityManager;

    @Transactional
    @Override
    public Route save(Route route) {
        entityManager.merge(route);
        return route;
    }
    @Transactional
    @Override
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
            Integer fromZ){
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Route> query = cb.createQuery(Route.class);
        Root<Route> root = query.from(Route.class);
        Join<Route, Coordinates> coordinatesJoin = root.join("coordinates",JoinType.LEFT);
        Join<Route, Location> toJoin = root.join("to",JoinType.LEFT);
        Join<Route, Location> fromJoin = root.join("from",JoinType.LEFT);
        List<Predicate> predicates = new ArrayList<>();
        addPredicates(id,predicates,"id", cb,root);
        addPredicates(name,predicates,"name", cb,root);
        addPredicates(distance,predicates,"distance", cb,root);
        addPredicatesCoordinates(coordinatesX,predicates,"x",cb,coordinatesJoin);
        addPredicatesCoordinates(coordinatesY,predicates,"y",cb,coordinatesJoin);
        addPredicatesLocation(toName,predicates,"name",cb,toJoin);
        addPredicatesLocation(toX,predicates,"x",cb,toJoin);
        addPredicatesLocation(toY,predicates,"y",cb,toJoin);
        addPredicatesLocation(toZ,predicates,"z",cb,toJoin);
        addPredicatesLocation(fromName,predicates,"name",cb,fromJoin);
        addPredicatesLocation(fromX,predicates,"x",cb,fromJoin);
        addPredicatesLocation(fromY,predicates,"y",cb,fromJoin);
        addPredicatesLocation(fromZ,predicates,"z",cb,fromJoin);
        if (!predicates.isEmpty()) {
            query.where(cb.and(predicates.toArray(new Predicate[0])));
        }
        if(sort != null) {
            String[] sorts = sort.split(",");
            List<Order> orders = new ArrayList<>();
            for (var s : sorts) {
                String[] ord = s.split(":");
                Order order = ord[1].equals("asc") ?
                        cb.asc(root.get(ord[0])) :
                        cb.desc(root.get(ord[0]));
                orders.add(order);
            }
            if (!orders.isEmpty()) {
                query.orderBy(orders);
            }
        }
        List<Route> routes = entityManager.createQuery(query).setFirstResult(page*size).setMaxResults(size).getResultList();

        return routes;
    }
    @Override
    public void addPredicates(Object param, List<Predicate> predicates, String paramName, CriteriaBuilder cb, Root<Route> root){
        if(param != null){
            predicates.add(cb.equal(root.get(paramName),param));
        }
    }
    @Override
    public void addPredicatesCoordinates(Object param, List<Predicate> predicates, String paramName, CriteriaBuilder cb, Join<Route, Coordinates> root){
        if(param != null){
            predicates.add(cb.equal(root.get(paramName),param));
        }
    }
    @Override
    public void addPredicatesLocation(Object param, List<Predicate> predicates, String paramName, CriteriaBuilder cb, Join<Route, Location> root){
        if(param != null){
            predicates.add(cb.equal(root.get(paramName),param));
        }
    }
    @Transactional
    @Override
    public Route getById(Integer id){
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Route> query = cb.createQuery(Route.class);
        Root<Route> root = query.from(Route.class);
        query.where(cb.equal(root.get("id"),id));
        return entityManager.createQuery(query).getSingleResult();
    }
    @Transactional
    @Override
    public boolean deleteById(Integer id){
        try {
            Route route = getById(id);
            entityManager.remove(route);
            return true;
        } catch (NoResultException e){
            return false;
        }
    }
    @Transactional
    @Override
    public Integer getDistance(){
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Integer> query = cb.createQuery(Integer.class);
        Root<Route> root = query.from(Route.class);
        query.select(cb.sum(root.get("distance")));
        return entityManager.createQuery(query).getSingleResult();
    }
    @Transactional
    @Override
    public Long CountDistanceLessThan(Integer threshold) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> query = cb.createQuery(Long.class);
        Root<Route> root = query.from(Route.class);
        query.select(cb.count(root.get("distance")))
                .where(cb.lessThan(root.get("distance"), threshold));

        return entityManager.createQuery(query).getSingleResult();
    }
    @Transactional
    @Override
    public List<Route> getAllbyStartName(String prefix) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Route> query = cb.createQuery(Route.class);
        Root<Route> root = query.from(Route.class);
        query.where(cb.like(root.get("name"),prefix));
        return entityManager.createQuery(query).getResultList();
    }
}
