package se.ifmo.soa.interfaces;

import se.ifmo.soa.DAO.LocationDAO;
import se.ifmo.soa.entites.Location;

public interface LocationService {
    Location save(Location location);

    Location fromDAO(LocationDAO locationDAO);

}
