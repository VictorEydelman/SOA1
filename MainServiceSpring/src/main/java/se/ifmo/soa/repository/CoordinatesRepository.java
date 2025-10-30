package se.ifmo.soa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se.ifmo.soa.entites.Coordinates;

public interface CoordinatesRepository extends JpaRepository<Coordinates, Long> {
}
