package se.ifmo.soa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import se.ifmo.soa.entites.Location;

import java.util.Optional;
@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {
    Optional<Location> findById(Long id);
}
