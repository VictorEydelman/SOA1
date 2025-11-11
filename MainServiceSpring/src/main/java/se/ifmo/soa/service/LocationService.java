package se.ifmo.soa.service;

import org.springframework.stereotype.Service;
import se.ifmo.soa.DAO.LocationDAO;
import se.ifmo.soa.entites.Location;
import se.ifmo.soa.repository.LocationRepository;

import java.util.Optional;

@Service
public class LocationService{
    private final LocationRepository locationRepository;

    public LocationService(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    public Location save(Location location) {
        locationRepository.save(location);
        return location;
    }

    public Location fromDAO(LocationDAO locationDAO) {
        if (locationDAO.getId() == null) {
            return Location.builder()
                    .x(locationDAO.getX())
                    .y(locationDAO.getY())
                    .z(locationDAO.getZ())
                    .build();
        }

        Optional<Location> optional= locationRepository.findById(locationDAO.getId());
        return optional.get();
    }
}
