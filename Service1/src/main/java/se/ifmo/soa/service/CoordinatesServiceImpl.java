package se.ifmo.soa.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import se.ifmo.soa.entites.Coordinates;
import se.ifmo.soa.interfaces.CoordinatesService;


public class CoordinatesServiceImpl implements CoordinatesService {
    @PersistenceContext(unitName = "route")
    private EntityManager entityManager;

    @Transactional
    @Override
    public Coordinates save(Coordinates coordinates) {
        entityManager.persist(coordinates);
        return coordinates;
    }
}
