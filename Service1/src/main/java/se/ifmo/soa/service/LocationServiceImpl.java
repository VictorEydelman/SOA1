package se.ifmo.soa.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import se.ifmo.soa.entites.Location;
import se.ifmo.soa.interfaces.LocationService;


public class LocationServiceImpl implements LocationService {
    @PersistenceContext(unitName = "route")
    private EntityManager entityManager;

    @Transactional
    @Override
    public Location save(Location location) {
        entityManager.persist(location);
        return location;
    }
}
