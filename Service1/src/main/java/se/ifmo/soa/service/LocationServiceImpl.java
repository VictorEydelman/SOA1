package se.ifmo.soa.service;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import se.ifmo.soa.DAO.LocationDAO;
import se.ifmo.soa.entites.Location;
import se.ifmo.soa.interfaces.LocationService;

@Stateless
public class LocationServiceImpl implements LocationService {
    @PersistenceContext(unitName = "route")
    private EntityManager entityManager;

    @Transactional
    @Override
    public Location save(Location location) {
        entityManager.persist(location);
        return location;
    }

    @Override
    public Location fromDAO(LocationDAO locationDAO) {
        if (locationDAO.getId() == null) {
            return Location.builder()
                    .x(locationDAO.getX())
                    .y(locationDAO.getY())
                    .z(locationDAO.getZ())
                    .build();
        }

        return entityManager.find(Location.class, locationDAO.getId());
    }
}
