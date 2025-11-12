package se.ifmo.soa.service;


import jakarta.persistence.criteria.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import se.ifmo.soa.entites.Coordinates;
import se.ifmo.soa.entites.Location;
import se.ifmo.soa.entites.Route;
import se.ifmo.soa.repository.RouteRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RouteService {

    private final RouteRepository routeRepository;

    public Route save(Route route) {
        return routeRepository.save(route);
    }

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
            Integer fromZ) {

        // Создаем спецификацию для фильтрации
        Specification<Route> spec = createSpecification(
                id, name, distance, coordinatesX, coordinatesY,
                toName, toX, toY, toZ, fromName, fromX, fromY, fromZ
        );

        // Создаем объект для пагинации и сортировки
        Pageable pageable = createPageable(page, size, sort);

        // Выполняем запрос
        Page<Route> routePage = routeRepository.findAll(spec, pageable);

        return routePage.getContent();
    }

    public Specification<Route> createSpecification(
            Integer id, String name, Integer distance,
            Integer coordinatesX, Float coordinatesY,
            String toName, Integer toX, String toY, Integer toZ,
            String fromName, Integer fromX, String fromY, Integer fromZ) {

        return (Root<Route> root, CriteriaQuery<?> query, CriteriaBuilder cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            // Join с связанными сущностями
            Join<Route, Coordinates> coordinatesJoin = root.join("coordinates", JoinType.LEFT);
            Join<Route, Location> toJoin = root.join("to", JoinType.LEFT);
            Join<Route, Location> fromJoin = root.join("from", JoinType.LEFT);

            // Предикаты для Route
            addPredicate(id, predicates, "id", cb, root);
            addPredicate(name, predicates, "name", cb, root);
            addPredicate(distance, predicates, "distance", cb, root);

            // Предикаты для Coordinates
            addPredicate(coordinatesX, predicates, "x", cb, coordinatesJoin);
            addPredicate(coordinatesY, predicates, "y", cb, coordinatesJoin);

            // Предикаты для Location "to"
            addPredicate(toName, predicates, "name", cb, toJoin);
            addPredicate(toX, predicates, "x", cb, toJoin);
            addPredicate(toY, predicates, "y", cb, toJoin);
            addPredicate(toZ, predicates, "z", cb, toJoin);

            // Предикаты для Location "from"
            addPredicate(fromName, predicates, "name", cb, fromJoin);
            addPredicate(fromX, predicates, "x", cb, fromJoin);
            addPredicate(fromY, predicates, "y", cb, fromJoin);
            addPredicate(fromZ, predicates, "z", cb, fromJoin);

            if (predicates.isEmpty()) {
                return cb.conjunction(); // WHERE TRUE
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }

    public void addPredicate(Object param, List<Predicate> predicates,
                             String paramName, CriteriaBuilder cb, Path<?> path) {
        if (param != null) {
            predicates.add(cb.equal(path.get(paramName), param));
        }
    }

    public Pageable createPageable(Integer page, Integer size, String sort) {
        // Пагинация
        if (page == null) page = 0;
        if (size == null) size = 10; // или другое значение по умолчанию

        // Сортировка
        if (sort != null) {
            List<Sort.Order> orders = new ArrayList<>();
            String[] sorts = sort.split(",");

            for (String s : sorts) {
                String[] ord = s.split(":");
                Sort.Direction direction = (ord.length < 2 || ord[1].equals("asc"))
                        ? Sort.Direction.ASC
                        : Sort.Direction.DESC;
                orders.add(new Sort.Order(direction, ord[0]));
            }

            return PageRequest.of(page, size, Sort.by(orders));
        }

        return PageRequest.of(page, size);
    }

    public Route getById(Long id) {
        return routeRepository.findById(id).orElse(null);
    }

    public void deleteById(Long id) {
        routeRepository.deleteById(id);
    }

    public Integer getDistance() {
        return routeRepository.sumDistance();
    }

    public Long CountDistanceLessThan(Integer threshold) {
        return routeRepository.countDistanceLessThan(threshold);
    }

    public List<Route> getAllbyStartName(String prefix) {
        return routeRepository.findAllByNameLike(prefix + "%");
    }
}
